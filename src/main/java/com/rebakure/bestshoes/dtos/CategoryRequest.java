package com.rebakure.bestshoes.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class CategoryRequest {
    private String name;
    private Integer parentId;
}
