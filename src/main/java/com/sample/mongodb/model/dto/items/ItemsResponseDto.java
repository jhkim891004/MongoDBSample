package com.sample.mongodb.model.dto.items;

import com.sample.mongodb.model.document.items.ItemsClassify;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemsResponseDto {
	private String code;
	private String name;
	private String description;
	private List<ItemsClassify> classifies = new ArrayList<>();

	@Builder
	public ItemsResponseDto(String code, String name, String description, List<ItemsClassify> classifies) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.classifies = classifies;
	}
}
