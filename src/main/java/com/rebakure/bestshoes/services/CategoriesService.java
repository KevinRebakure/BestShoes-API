package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.dtos.CategoryRequest;
import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.UpdateCategoryRequest;
import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.CategoryMapper;
import com.rebakure.bestshoes.mappers.CustomProductMapper;
import com.rebakure.bestshoes.repositories.CategoryRepository;
import com.rebakure.bestshoes.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final CustomProductMapper customProductMapper;

    public CategoryDto addCategory(CategoryRequest request) {
        Category parentCategory = null;

        if (request.getParentId() != null) {
            parentCategory = categoryRepository
                    .findCategoryById(request.getParentId());
            if (parentCategory == null) {
                throw new NotFoundException(
                        "Category with id " + request.getParentId() + " not found"
                );
            }
        }

        var category = new Category();
        category.setName(request.getName());
        category.setParent(parentCategory);

        categoryRepository.save(category);
        return categoryMapper.entityToDto(category);
    }

    @Transactional
    public CategoryDto updateCategory(Integer id, UpdateCategoryRequest request) {
        Category category =  categoryRepository.findCategoryById(id);

        if (category == null) {
            throw new NotFoundException("Category with id " + id + " not found.");
        }

        if (request.getParentId() != null) {
            Category parentCategory = categoryRepository.findCategoryById(request.getParentId());
            if (parentCategory == null) {
                throw new NotFoundException("Parent Category with id " + request.getParentId() + " not found.");
            }
            category.setParent(parentCategory);
        }

        categoryMapper.update(request, category);
        categoryRepository.save(category);
        return categoryMapper.entityToDto(category);
    }

    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::entityToDto).toList();
    }

    public void deleteCategory(Integer id) {
        Category category =  categoryRepository.findCategoryById(id);

        if (category == null) {
            throw new NotFoundException("Category with id " + id + " not found.");
        }

        categoryRepository.delete(category);
    }

    public List<ProductDto> findAllCategoryProducts(Integer id) {
        Category category = categoryRepository.findCategoryById(id);

        if (category == null) {
            throw new NotFoundException("Category with id " + id + " not found.");
        }

        return productRepository.findProductsByCategory(category).stream().map(
                product -> customProductMapper.entityToDto(product, product.getVariant(), product.getCategory())
        ).toList();
    }
}
