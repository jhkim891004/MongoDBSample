package com.sample.mongodb.api.repository.items;

import com.sample.mongodb.model.document.items.Items;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemsRepository extends MongoRepository<Items, Long> {
	Items findItemsByCode(String code);
}
