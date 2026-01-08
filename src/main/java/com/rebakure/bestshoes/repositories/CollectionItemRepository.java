package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Collection;
import com.rebakure.bestshoes.entities.CollectionItem;
import com.rebakure.bestshoes.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {
    @Query("SELECT ci FROM CollectionItem ci WHERE ci.collection = :collection " +
            "AND ci.product = :product ")
    CollectionItem findUnique(
            @Param("collection") Collection collection,
            @Param("product") Product product);

    List<CollectionItem> findCollectionItemsByCollection(Collection collection);
}
