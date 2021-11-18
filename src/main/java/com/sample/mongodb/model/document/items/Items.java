package com.sample.mongodb.model.document.items;

import com.sample.mongodb.common.utils.StringUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "items")
public class Items {
	@Transient
	static final String UNIQUE_CODE = "ITM";

	@Id
	private Long id;
	private String code;
	public void setSequence(Long seq) {
		this.id = seq;
		this.code = this.UNIQUE_CODE + StringUtil.setLPad(Long.toString(seq), 8, "0");
	}
	private String name;
	private String description;
	private List<ItemsClassify> classifies = new ArrayList<>();

	@Builder
	public Items(String name, String description, List<ItemsClassify> classifies) {
		this.name = name;
		this.description = description;
		this.classifies = classifies;
	}

	public void update(String name, String description, List<ItemsClassify> classifies) {
		this.name = name;
		this.description = description;
		this.classifies = classifies;
	}
}
