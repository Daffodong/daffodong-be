package com.daffodong.inventory.service.impl;

import com.daffodong.inventory.model.FindItemsRequest;
import com.daffodong.inventory.model.InsertItemRequest;
import com.daffodong.inventory.model.Item;
import com.daffodong.inventory.repository.ItemRepository;
import com.daffodong.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item insert(InsertItemRequest request){
        if(request == null){
            throw new RestClientException("Invalid request: request is null");
        }
        else if(StringUtils.isEmpty(request.name) || request.price == null){
            throw new RestClientException("Invalid request: name or price is null");
        }
        else if(doesNameExist(request.name)){
            throw new RestClientException("Invalid request: name already exists!");
        }

        Item item = new Item();
        item.name = request.name;
        item.price = request.price;
        return itemRepository.insert(item);
    }

    private boolean doesNameExist(String name){
        return itemRepository.findByName(name).isPresent();
    }

    @Override
    public List<Item> findByParams(FindItemsRequest request){
        return itemRepository.findByParams(request);
    }

}
