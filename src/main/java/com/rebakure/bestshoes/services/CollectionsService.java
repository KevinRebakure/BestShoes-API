package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.*;
import com.rebakure.bestshoes.entities.Collection;
import com.rebakure.bestshoes.entities.CollectionItem;
import com.rebakure.bestshoes.exceptions.ConflictException;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.CollectionItemMapper;
import com.rebakure.bestshoes.mappers.CollectionMapper;
import com.rebakure.bestshoes.repositories.CollectionItemRepository;
import com.rebakure.bestshoes.repositories.CollectionRepository;
import com.rebakure.bestshoes.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CollectionsService {
    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;
    private final CollectionItemRepository collectionItemRepository;
    private final ProductRepository productRepository;
    private final CollectionItemMapper collectionItemMapper;

    @Transactional
    public CollectionDto addCollection(CollectionRequest request) {
        var existingCollection = collectionRepository.findCollectionByName(request.getName());

        if (existingCollection != null) {
            throw new ConflictException(request.getName() + " collection already exists");
        }

        Collection collection = new Collection();
        collection.setName(request.getName());

        collectionRepository.save(collection);
        return collectionMapper.entityToDto(collection);
    }

    @Transactional
    public CollectionDto updateCollection(Long id, @Valid CollectionRequest request) {
        var collection = collectionRepository.findById(id).orElse(null);

        if (collection == null) {
            throw new NotFoundException("Collection not found");
        }

        collection.setName(request.getName());
        collectionRepository.save(collection);
        return collectionMapper.entityToDto(collection);
    }

    public CollectionDto findCollectionById(Long id) {
        var collection = collectionRepository.findCollectionById((id));

        if (collection == null) {
            throw new NotFoundException("Collection not found");
        }

        return collectionMapper.entityToDto(collection);
    }

    public List<CollectionDto> findAllCollections() {
        return collectionRepository.findAll().stream().map(collectionMapper::entityToDto).toList();
    }

    public void deleteCollection(Long id) {
        var collection = collectionRepository.findById(id).orElse(null);

        if (collection == null) {
            throw new NotFoundException("Collection not found");
        }

        collectionRepository.delete(collection);
    }

    // Collection items

    public CollectionItemDto addItemToCollection(Long id, CollectionItemRequest request) {
        var collection = collectionRepository.findCollectionById(id);
        if (collection == null) {
            throw new NotFoundException("Collection does not exist");
        }

        var product = productRepository.findProductById(request.getProductId());
        if (product == null) {
            throw new NotFoundException("Product does not exist");
        }

        var existingCollectionItem = collectionItemRepository.findUnique(collection, product);
        if (existingCollectionItem != null) {
            throw new ConflictException(product.getName() + " already exists in this collection");
        }


        CollectionItem item = new CollectionItem();
        item.setCollection(collection);
        item.setProduct(product);

        collectionItemRepository.save(item);
        return collectionItemMapper.entityToDto(item);
    }

    public CollectionItemDto updateCollectionItem(
            Long id,
            Long itemId,
            UpdateCollectionItemRequest request
    ) {
        var collection = collectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Collection does not exist"));

        var item = collectionItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item does not exist"));

        if (request.getCollectionId() == null && request.getProductId() == null) {
            return collectionItemMapper.entityToDto(item);
        }

        if (request.getCollectionId() != null) {
            var newCollection = collectionRepository.findCollectionById(request.getCollectionId());
            if (newCollection == null) {
                throw new NotFoundException("Collection does not exist");
            }
            item.setCollection(newCollection);
        }

        if (request.getProductId() != null) {
            var newProduct = productRepository.findProductById(request.getProductId());
            if (newProduct == null) {
                throw new NotFoundException("Product does not exist");
            }
            item.setProduct(newProduct);
        }

        collectionItemRepository.save(item);
        return collectionItemMapper.entityToDto(item);
    }


    public CollectionItemDto findCollectionItemById(Long id) {
        var item = collectionItemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new NotFoundException("Item does not exist");
        }

        return collectionItemMapper.entityToDto(item);
    }

    public void deleteCollectionItem(Long id) {
        var item = collectionItemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new NotFoundException("Item does not exist");
        }

        collectionItemRepository.delete(item);
    }

    public List<CollectionItemDto> findAllCollectionItems(Long id) {
        var collection = collectionRepository.findCollectionById(id);
        if (collection == null) {
            throw new NotFoundException("Collection does not exist");
        }

        var items = collectionItemRepository.findCollectionItemsByCollection(collection);
        return items.stream().map(collectionItemMapper::entityToDto).toList();
    }
}
