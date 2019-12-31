package com.daffodong.inventory.repository;

import com.daffodong.inventory.model.FindItemsRequest;
import com.daffodong.inventory.model.Item;

import java.util.List;

public interface ItemCustomRepository {

    List<Item> findByParams(FindItemsRequest request);

}
