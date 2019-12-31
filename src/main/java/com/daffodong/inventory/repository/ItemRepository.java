package com.daffodong.inventory.repository;

import com.daffodong.inventory.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String>, ItemCustomRepository{
    Optional<Item> findById(String id);
    Item insert(Item item);
    Optional<Item> findByName(String name);
}
