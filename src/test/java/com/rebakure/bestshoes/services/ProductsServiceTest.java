package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.entities.Product;
import com.rebakure.bestshoes.entities.Variant;
import com.rebakure.bestshoes.exceptions.CategoryNotFoundException;
import com.rebakure.bestshoes.mappers.ProductMapper;
import com.rebakure.bestshoes.repositories.CategoryRepository;
import com.rebakure.bestshoes.repositories.ProductRepository;
import com.rebakure.bestshoes.repositories.VariantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @InjectMocks
    private ProductsService productsService;

    private ProductRequest request;
    private Category category;
    private Variant variant;
    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1);
        category.setName("Men's shoes");

        variant = new Variant();
        variant.setId(1L);
        variant.setBrand("New Balance");
        variant.setColor("brown");
        variant.setSize("45 EU");
        variant.setPrice(BigDecimal.valueOf(49.99));
        variant.setMaterial("leather");
        variant.setQuantity(10);
        variant.setStockKeepingUnit("NBS-001");

        request = new ProductRequest();
        request.setCategoryId(1);
        request.setName("New Balance 580");
        request.setDescription("Good brown shoes that can go with brown pants for office");
        request.setBasePrice(BigDecimal.valueOf(49.99));
        request.setVariantId(1L);

        product =  new Product();
        product.setCategory(category);
        product.setVariant(variant);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBasePrice(BigDecimal.valueOf(49.99));
        product.setId(1L);

        productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setBasePrice(product.getBasePrice());
        productDto.setBrand(variant.getBrand());
        productDto.setColor(variant.getColor());
        productDto.setSize(variant.getSize());
        productDto.setMaterial(variant.getMaterial());
        productDto.setCategory(product.getCategory().getName());
    }

    @Test
    void addProduct_shouldSaveProductAndReturnProductDto() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(variantRepository.findById(1L)).thenReturn(Optional.of(variant));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.entityToDto(any(Product.class))).thenReturn(productDto);

        var result =  productsService.addProduct(request);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(request.getName());
        assertThat(result.getDescription()).isEqualTo(request.getDescription());
        assertThat(result.getBasePrice()).isEqualByComparingTo(request.getBasePrice());
        assertThat(result.getCategory()).isEqualTo(category.getName());
        assertThat(result.getBrand()).isEqualTo(variant.getBrand());

        verify(categoryRepository).findById(1);
        verify(variantRepository).findById(1L);
        verify(productRepository).save(any(Product.class));
        verify(productMapper).entityToDto(any(Product.class));
    }

    @Test
    void addProduct_WithInvalidCategoryId_ShouldThrowCategoryNotFoundException() {
        // Arrange
        when(categoryRepository.findById((int) anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> productsService.addProduct(request))
                .isInstanceOf(CategoryNotFoundException.class)
                .hasMessage("Category not found");

        verify(categoryRepository).findById(1);
        verify(variantRepository, never()).findById(anyLong());
        verify(productRepository, never()).save(any(Product.class));
    }
}