package com.daffodong.inventory.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
public class Item {

    @Id
    public String id;
    public String name;
    public Double price;

    @Override
    public String toString(){
        return "Item {id:"+id+",name:"+name+",price:"+price+"}";
    }

}
