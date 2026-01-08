package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.*;
import com.rebakure.bestshoes.services.CollectionsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
@AllArgsConstructor
@Validated
public class CollectionsController {
    private final CollectionsService collectionsService;

    @PostMapping
    public ResponseEntity<CollectionDto> addCollection(@Valid @RequestBody CollectionRequest request) {
        var dto = collectionsService.addCollection(request);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CollectionDto> updateCollection(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @Valid @RequestBody CollectionRequest request) {
        return ResponseEntity.ok().body(collectionsService.updateCollection(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionDto> getCollection(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(collectionsService.findCollectionById(id));
    }

    @GetMapping
    public ResponseEntity<List<CollectionDto>> getAllCollections() {
        return ResponseEntity.ok().body(collectionsService.findAllCollections());
    }

    @DeleteMapping("/{id}")
    public void deleteCollection(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        collectionsService.deleteCollection(id);
    }

    // Collection items

    @PostMapping("/{id}/add-item")
    public ResponseEntity<CollectionItemDto> addCollectionItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @Valid @RequestBody CollectionItemRequest request) {
        var dto = collectionsService.addItemToCollection(id, request);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}/items/{itemId}")
    public ResponseEntity<CollectionItemDto> updateCollectionItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long itemId,
            @Valid @RequestBody UpdateCollectionItemRequest request) {
        return ResponseEntity.ok().body(collectionsService.updateCollectionItem(id, itemId, request));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<CollectionItemDto> getCollectionItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(collectionsService.findCollectionItemById(id));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<CollectionItemDto>> getAllCollectionItems(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(collectionsService.findAllCollectionItems(id));
    }

    @DeleteMapping("/items/{id}")
    public void deleteCollectionItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        collectionsService.deleteCollectionItem(id);
    }
}
