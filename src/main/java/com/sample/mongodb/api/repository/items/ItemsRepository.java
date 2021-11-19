package com.sample.mongodb.api.repository.items;

import com.sample.mongodb.model.document.items.Items;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ItemsRepository extends MongoRepository<Items, Long> {
	Optional<Items> findItemsByCode(String code);
}
