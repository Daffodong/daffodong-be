package com.daffodong.inventory.server;

import com.daffodong.inventory.model.FindItemsRequest;
import com.daffodong.inventory.model.GenerateDummyRequest;
import com.daffodong.inventory.model.InsertItemRequest;
import com.daffodong.inventory.model.Item;
import com.daffodong.inventory.repository.ItemRepository;
import com.daffodong.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication(scanBasePackages = {"com.daffodong.inventory.repository", "com.daffodong.inventory.service"})
@EnableMongoRepositories("com.daffodong.inventory.repository")
@Controller
public class DemoApplication {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemService itemService;

	@GetMapping("/")
	@ResponseBody
	String home() {
		return "Merry Christmas milabs!";
	}

	@PostMapping("/generateDummy")
	@ResponseBody
	Boolean generateDummyies(@RequestBody GenerateDummyRequest request){
		itemRepository.insert(createDummyData(request.dummyCnt));
		return true;
	}

	@DeleteMapping("/wipeDummies")
	@ResponseBody
	Boolean wipe(){
		itemRepository.deleteAll();
		return true;
	}

	@PostMapping("/addItem")
	@ResponseBody
	Item addItem(@RequestBody InsertItemRequest request){
		return itemService.insert(request);
	}

	@GetMapping("/items")
	@ResponseBody
	List<Item> getItems(@RequestParam(required=false) String name, @RequestParam(required=false) Double price){

		FindItemsRequest request = new FindItemsRequest();
		request.name = name;
		request.price = price;

		return itemService.findByParams(request);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	private List<Item> createDummyData(int cnt){
		List<Item> dummyList = new ArrayList<>();
		for(int i=0; i<cnt; i++){
			Item item = new Item();
			item.name = "dummy"+i;
			item.price = new Random().nextDouble() * 1000;
			dummyList.add(item);
		}

		return dummyList;
	}
}
