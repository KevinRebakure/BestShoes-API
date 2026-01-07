package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.entities.Product;
import com.rebakure.bestshoes.entities.Variant;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.ProductMapper;
import com.rebakure.bestshoes.repositories.CategoryRepository;
import com.rebakure.bestshoes.repositories.ProductRepository;
import com.rebakure.bestshoes.repositories.VariantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VariantRepository variantRepository;
    private final ProductMapper productMapper;

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
}
