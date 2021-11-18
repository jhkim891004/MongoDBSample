package com.sample.mongodb.model.document.sequences;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "db_sequences")
public class Sequences {
	@Id
	private String id;
	private String seqName;
	private Long seq;
}
