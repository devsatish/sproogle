package com.mattwhipple.sproogle.closure.config.impl;

import javax.annotation.Nonnull;

import com.google.template.soy.data.SoyData;
import com.mattwhipple.sproogle.closure.config.AbstractSoyDataMapper;
import com.mattwhipple.sproogle.closure.config.ClosureTemplateConfiguration;
import com.mattwhipple.sproogle.closure.config.SoyDataMapper;

/**
 * This primarily serves as a Parameter Object to allow the configuration to be passed to the resolver
 * and then to each View
 */
public class ClosureTemplateConfigurationImpl implements ClosureTemplateConfiguration {

	private @Nonnull SoyDataMapper soyDataMapper = new BasicSoyDataMapper();

	public @Nonnull SoyDataMapper getSoyDataMapper() {
		return soyDataMapper;
	}

	public void setSoyDataMapper(SoyDataMapper soyDataMapper) {
		if (soyDataMapper == null) throw new IllegalArgumentException("SoyDataMapper cannot be null");
		this.soyDataMapper = soyDataMapper;
	}
	
	class BasicSoyDataMapper extends AbstractSoyDataMapper {

		@SuppressWarnings("null") //static method doesn't allow for this at a glance
		@Override
		@Nonnull
		public SoyData mapObject(@Nonnull Object object) {
			return SoyData.createFromExistingData(object);
		}
	}
	
}
