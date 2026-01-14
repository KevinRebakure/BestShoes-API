package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.*;
import com.rebakure.bestshoes.services.CollectionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/collections")
@AllArgsConstructor
@Validated
@Tag(name = "Collections", description = "You can use collections to categories products by for example seasons in the year")
public class CollectionsController {
    private final CollectionsService collectionsService;

    @PostMapping
    public ResponseEntity<CollectionDto> addCollection(
            @Valid @RequestBody CollectionRequest request,
            UriComponentsBuilder uriBuilder) {
        var dto = collectionsService.addCollection(request);

        var uri = uriBuilder.path("/collections/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

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
    public ResponseEntity<Void> deleteCollection(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        collectionsService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }

    // Collection items

    @Operation(
            summary = "Add a product to a collection",
            description = "The same product can be added to multiple collections"
    )
    @PostMapping("/{id}/add-item")
    public ResponseEntity<CollectionItemDto> addCollectionItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @Valid @RequestBody CollectionItemRequest request) {
        var dto = collectionsService.addItemToCollection(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
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
    public ResponseEntity<Void> deleteCollectionItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        collectionsService.deleteCollectionItem(id);
        return ResponseEntity.noContent().build();

    }
}
