package com.sample.mongodb.api.controller.items;

import com.sample.mongodb.api.service.items.ItemsService;
import com.sample.mongodb.model.dto.items.ItemsModifyDto;
import com.sample.mongodb.model.dto.items.ItemsResponseDto;
import com.sample.mongodb.model.dto.items.ItemsSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemsController {
	private final ItemsService itemsService;

	@GetMapping("/api/v1/items")
	public List<ItemsResponseDto> getAllItems() {
		return itemsService.getAllItems();
	}

	@GetMapping("/api/v1/items/{id}")
	public ItemsResponseDto getOneItems(@PathVariable Long id) {
		return itemsService.getOneItems(id);
	}

	@PostMapping("/api/v1/items")
	public void saveItems(@RequestBody @Valid ItemsSaveDto dto) {
		itemsService.saveItems(dto);
	}

	@PutMapping("/api/v1/items")
	public void modifyItems(@RequestBody @Valid ItemsModifyDto dto) {
		itemsService.modifyItems(dto);
	}

	@DeleteMapping("/api/v1/items/{id}")
	public void removeItems(@PathVariable Long id) {
		itemsService.removeItems(id);
	}
}
