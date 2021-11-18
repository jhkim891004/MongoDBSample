package com.sample.mongodb.model.dto.items;

import com.sample.mongodb.model.document.items.ItemsClassify;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemsModifyDto {
	private String code;
	private String name;
	private String description;
	private List<ItemsClassify> classifies = new ArrayList<>();
}
