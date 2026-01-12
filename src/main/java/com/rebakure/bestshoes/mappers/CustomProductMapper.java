package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.common.QuantityStatus;
import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.entities.Category;
import com.rebakure.bestshoes.entities.Product;
import com.rebakure.bestshoes.entities.Variant;
import org.springframework.stereotype.Component;

@Component
public class CustomProductMapper {
        public ProductDto entityToDto(Product product, Variant variant, Category category) {
        ProductDto productDto = new ProductDto();
        productDto.setCategory(category.getName());
        productDto.setBrand(variant.getBrand());
        productDto.setId(product.getId());
        productDto.setColor(variant.getColor());
        productDto.setMaterial(variant.getMaterial());
        productDto.setDescription(product.getDescription());
        productDto.setBasePrice(product.getBasePrice());
        productDto.setName(product.getName());
        productDto.setSize(variant.getSize());
        productDto.setStockKeepingUnit(variant.getStockKeepingUnit());

        int quantity = variant.getQuantity();

        if (quantity == 0) {
            productDto.setQuantity(QuantityStatus.OUT_OF_STOCK.toString());
        } else if (quantity < 5) {
            productDto.setQuantity(QuantityStatus.LOW_STOCK.toString());
        } else {
            productDto.setQuantity(QuantityStatus.IN_STOCK.toString());
        }

        return productDto;
    }
}
