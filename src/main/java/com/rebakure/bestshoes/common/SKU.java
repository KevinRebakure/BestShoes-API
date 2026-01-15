package com.rebakure.bestshoes.common;

import com.rebakure.bestshoes.entities.Variant;
import lombok.Getter;

public class SKU {
    @Getter
    private final String sku;

    public SKU(Variant variant) {
        this.sku = hash(variant.getBrand()) + "-" +
                hash(variant.getColor()) + "-" +
                hash(variant.getMaterial()) + "-" +
                variant.getSize().replace(" ", "");
    }

    private String hash(String string) {
        return string.substring(0, Math.min(4, string.length())).toUpperCase();
    }
}
