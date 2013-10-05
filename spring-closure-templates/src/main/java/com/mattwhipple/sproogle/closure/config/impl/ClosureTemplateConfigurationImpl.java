package com.mattwhipple.sproogle.closure.config.impl;

import javax.annotation.Nonnull;

import com.google.template.soy.data.SoyData;
import com.mattwhipple.sproogle.closure.config.AbstractSoyDataMapper;
import com.mattwhipple.sproogle.closure.config.ClosureTemplateConfiguration;
import com.mattwhipple.sproogle.closure.config.NamespacingStrategy;
import com.mattwhipple.sproogle.closure.config.SoyDataMapper;

/**
 * This primarily serves as a Parameter Object to allow the configuration to be passed to the resolver
 * and then to each View
 */
public class ClosureTemplateConfigurationImpl implements ClosureTemplateConfiguration {

	private @Nonnull SoyDataMapper soyDataMapper = new BasicSoyDataMapper();
	private @Nonnull NamespacingStrategy namespacingStrategy = new NoopNamespacingStrategy();
	
	public @Nonnull SoyDataMapper getSoyDataMapper() {
		return soyDataMapper;
	}

	public void setSoyDataMapper(SoyDataMapper soyDataMapper) {
		if (soyDataMapper == null) throw new IllegalArgumentException("SoyDataMapper cannot be null");
		this.soyDataMapper = soyDataMapper;
	}
	
	public @Nonnull NamespacingStrategy getNamespacingStrategy() {
		return namespacingStrategy;
	}

	public void setNamespacingStrategy(NamespacingStrategy namespacingStrategy) {
		if (namespacingStrategy == null) { throw new IllegalArgumentException("NamespacingStrategy cannot be null"); }
		this.namespacingStrategy = namespacingStrategy;
	}

	class BasicSoyDataMapper extends AbstractSoyDataMapper {

		@SuppressWarnings("null") //static method doesn't allow for this at a glance
		@Override
		@Nonnull
		public SoyData mapObject(@Nonnull Object object) {
			return SoyData.createFromExistingData(object);
		}
	}
	
	class NoopNamespacingStrategy implements NamespacingStrategy {
		@Override
		@Nonnull
		public String applyNamespace(@Nonnull String templateName) {
			return templateName;
		}
		
	}
	
}
