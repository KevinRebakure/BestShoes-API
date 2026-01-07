package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.dtos.UpdateProductRequest;
import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.entities.Product;
import com.rebakure.bestshoes.entities.Variant;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.ProductMapper;
import com.rebakure.bestshoes.repositories.CategoryRepository;
import com.rebakure.bestshoes.repositories.ProductRepository;
import com.rebakure.bestshoes.repositories.VariantRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VariantRepository variantRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto addProduct(ProductRequest request) {
        Product product = new Product();

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Variant variant = variantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new NotFoundException("Check the store for available variants."));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBasePrice(request.getBasePrice());
        product.setCategory(category);
        product.setVariant(variant);

        productRepository.save(product);

        variant.addProduct(product);

        return productMapper.entityToDto(product);
    }

    @Transactional
    public ProductDto updateProduct(Long id, @Valid UpdateProductRequest request) {
        var product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        if (request.getCategoryId() != null) {
            var category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found"));
            product.setCategory(category);
        }

        if (request.getVariantId() != null) {
            var variant = variantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new NotFoundException("Variant not found"));
            product.setVariant(variant);
        }

        productMapper.updateProduct(request, product);
        productRepository.save(product);
        return productMapper.entityToDto(product);
    }

    public ProductDto findProductById(Long id) {
        var product = productRepository.findProductById((id));

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        return productMapper.entityToDto(product);
    }

    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream().map(productMapper::entityToDto).toList();
    }
}
