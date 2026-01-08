package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Collection findCollectionById(Long id);
    Collection findCollectionByName(String name);
}
