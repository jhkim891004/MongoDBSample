package com.sample.mongodb.model.document.items;

import com.sample.mongodb.model.document.sequences.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemsListener extends AbstractMongoEventListener<Items> {
	private final SequenceGeneratorService sequenceGenerator;

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Items> event) {
		if(event.getSource().getId() == null) {
			event.getSource().setSequence(sequenceGenerator.generateSequence(Items.UNIQUE_CODE));
		}
	}
}
