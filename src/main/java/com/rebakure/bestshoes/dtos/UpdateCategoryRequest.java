package com.rebakure.bestshoes.dtos;

import lombok.Data;

@Data
public class UpdateCategoryRequest {
    private String name;
    private Integer parentId;
}
