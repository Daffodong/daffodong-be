package com.daffodong.inventory.service;

import com.daffodong.inventory.model.FindItemsRequest;
import com.daffodong.inventory.model.InsertItemRequest;
import com.daffodong.inventory.model.Item;

import java.util.List;

public interface ItemService {
    Item insert(InsertItemRequest request);

    List<Item> findByParams(FindItemsRequest request);
}
