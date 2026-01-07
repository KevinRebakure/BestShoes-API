package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.dtos.CategoryRequest;
import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.CategoryMapper;
import com.rebakure.bestshoes.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriesService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto addCategory(CategoryRequest request) {
        Category parentCategory = categoryRepository.findCategoryById(request.getParentId());

        if (parentCategory == null) {
            throw new NotFoundException("Category with id " + request.getParentId() + " not found. Don't pass the parentId if you want to create a top level category.");
        }

        var category = new Category();
        category.setName(request.getName());
        category.setParent(parentCategory);

        categoryRepository.save(category);
        return categoryMapper.entityToDto(category);
    }
}
