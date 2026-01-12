package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.entities.Product;
import com.rebakure.bestshoes.entities.Variant;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.CustomProductMapper;
import com.rebakure.bestshoes.mappers.ProductMapper;
import com.rebakure.bestshoes.repositories.CategoryRepository;
import com.rebakure.bestshoes.repositories.ProductRepository;
import com.rebakure.bestshoes.repositories.VariantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private VariantRepository variantRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CustomProductMapper customProductMapper;

    @InjectMocks
    private ProductsService productsService;

    @Test
    void saveProduct_successfully() {
        Category category = new Category();
        category.setId(1);

        Variant variant = new Variant();
        variant.setId(1L);
        variant.setQuantity(0);

        ProductRequest request = new ProductRequest();
        request.setName("Air Max");
        request.setDescription("Running shoe");
        request.setBasePrice(BigDecimal.valueOf(150));
        request.setCategoryId(1);
        request.setVariantId(1L);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(variantRepository.findById(1L)).thenReturn(Optional.of(variant));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        productsService.addProduct(request);

        verify(productRepository).save(any(Product.class));
        verify(variantRepository).save(variant);
        verify(variantRepository, times(1)).findById(1L);
    }

    @Test
    void saveProduct_invalidVariant_rollback() {
        ProductRequest request = new ProductRequest();
        request.setName("Air Max");
        request.setBasePrice(BigDecimal.valueOf(150));
        request.setCategoryId(1);
        request.setVariantId(99L);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(new Category()));
        when(variantRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                productsService.addProduct(request)
        );

        verify(productRepository, never()).save(any());
        verify(variantRepository, never()).save(any());
    }
}
