package com.sample.mongodb.api.service.items;

import com.sample.mongodb.api.repository.items.ItemsRepository;
import com.sample.mongodb.model.document.items.Items;
import com.sample.mongodb.model.dto.items.ItemsModifyDto;
import com.sample.mongodb.model.dto.items.ItemsResponseDto;
import com.sample.mongodb.model.dto.items.ItemsSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemsService {
	private final ItemsRepository itemsRepository;

	public List<ItemsResponseDto> getAllItems() {
		List<Items> items = itemsRepository.findAll();
		List<ItemsResponseDto> responseDtos = items.stream()
				.map(document ->
						ItemsResponseDto.builder()
								.code(document.getCode())
								.name(document.getName())
								.description(document.getDescription())
								.classifies(document.getClassifies())
								.build())
				.collect(Collectors.toList());

		return responseDtos;
	}

	public ItemsResponseDto getOneItems(Long id) {
		Items items = itemsRepository.findById(id)
				.orElseThrow(NoSuchElementException::new);

		return ItemsResponseDto.builder()
				.code(items.getCode())
				.name(items.getName())
				.description(items.getDescription())
				.classifies(items.getClassifies())
				.build();
	}

	@Transactional
	public ItemsResponseDto saveItems(ItemsSaveDto dto) {
		Items document = dto.toEntity();
		itemsRepository.save(document);
		return ItemsResponseDto.builder()
				.code(document.getCode())
				.name(document.getName())
				.description(document.getDescription())
				.classifies(document.getClassifies())
				.build();
	}

	@Transactional
	public ItemsResponseDto modifyItems(ItemsModifyDto dto) {
		Items document = itemsRepository.findItemsByCode(dto.getCode())
						.orElseThrow(NoSuchElementException::new);
		document.update(dto.getName(), dto.getDescription(), dto.getClassifies());
		itemsRepository.save(document);
		return ItemsResponseDto.builder()
				.code(document.getCode())
				.name(document.getName())
				.description(document.getDescription())
				.classifies(document.getClassifies())
				.build();
	}

	@Transactional
	public void removeItems(Long id) {
		Items document = itemsRepository.findById(id)
				.orElseThrow(NoSuchElementException::new);

		itemsRepository.delete(document);
	}
}
