package com.sample.mongodb.api.controller.items;

import com.sample.mongodb.api.service.items.ItemsService;
import com.sample.mongodb.common.response.SuccessCode;
import com.sample.mongodb.common.response.SuccessResponse;
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
	public SuccessResponse<List<ItemsResponseDto>> getAllItems() {
		return new SuccessResponse<>(itemsService.getAllItems(), SuccessCode.OK);
	}

	@GetMapping("/api/v1/items/{id}")
	public SuccessResponse<ItemsResponseDto> getOneItems(@PathVariable Long id) {
		return new SuccessResponse<>(itemsService.getOneItems(id), SuccessCode.OK);
	}

	@PostMapping("/api/v1/items")
	public SuccessResponse<ItemsResponseDto> saveItems(@RequestBody @Valid ItemsSaveDto dto) {
		return new SuccessResponse<>(itemsService.saveItems(dto), SuccessCode.SUCCESS_CREATED);
	}

	@PatchMapping("/api/v1/items")
	public SuccessResponse<ItemsResponseDto> modifyItems(@RequestBody @Valid ItemsModifyDto dto) {
		return new SuccessResponse<>(itemsService.modifyItems(dto), SuccessCode.SUCCESS_MODIFIED);
	}

	@DeleteMapping("/api/v1/items/{id}")
	public void removeItems(@PathVariable Long id) {
		itemsService.removeItems(id);
	}
}
