package com.sample.mongodb.model.dto.items;

import com.sample.mongodb.model.document.items.Items;
import com.sample.mongodb.model.document.items.ItemsClassify;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemsSaveDto {
	private String name;
	private String description;
	private List<ItemsClassify> classifies;

	public Items toEntity() {
		return Items.builder()
				.name(this.name)
				.description(this.description)
				.classifies(this.classifies)
				.build();
	}
}
