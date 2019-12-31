package com.daffodong.inventory.repository.impl;

import com.daffodong.inventory.model.FindItemsRequest;
import com.daffodong.inventory.model.Item;
import com.daffodong.inventory.repository.ItemCustomRepository;
import com.daffodong.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class ItemRepositoryImpl implements ItemCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Item> findByParams(FindItemsRequest request){
        Query query = new Query();
        if(!StringUtils.isEmpty(request.name))
            query.addCriteria(where("name").is(request.name));

        if(request.price != null)
            query.addCriteria(where("price").is(request.price));

        return mongoTemplate.find(query, Item.class);
    }

}
