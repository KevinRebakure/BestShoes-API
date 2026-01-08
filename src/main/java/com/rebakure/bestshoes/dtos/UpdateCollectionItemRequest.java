package com.rebakure.bestshoes.dtos;

import lombok.Data;

@Data
public class UpdateCollectionItemRequest {
    private Long productId;
    private Long collectionId;
}
