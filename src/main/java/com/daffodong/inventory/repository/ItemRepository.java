package com.daffodong.inventory.repository;

import com.daffodong.inventory.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {
    public Optional<Item> findById(String id);
}
