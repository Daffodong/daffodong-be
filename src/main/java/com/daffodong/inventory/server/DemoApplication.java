package com.daffodong.inventory.server;

import com.daffodong.inventory.model.Item;
import com.daffodong.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@Controller
public class DemoApplication {

	@Autowired
	private ItemRepository itemRepository;

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
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	private class GenerateDummyRequest {
		public int dummyCnt;
		public GenerateDummyRequest(){}
	}

	private List<Item> createDummyData(int cnt){
		List<Item> dummyList = new ArrayList<>();
		for(int i=0; i<cnt; i++){
			Item item = new Item();
			item.name = "dummy"+i;
			item.price = new Random().nextDouble() * 1000;
			dummyList.add(item);
		}

		return dummyList
	}
}
