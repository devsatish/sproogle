package com.mattwhipple.sproogle.closure.config.impl;

import javax.annotation.Nonnull;

import com.mattwhipple.sproogle.closure.config.NamespacingStrategy;

public class StandardNamespacingStrategy implements NamespacingStrategy {

	private final String namespace;
	
	public StandardNamespacingStrategy(String namespace) {
		if (!namespace.endsWith(".")) {
			namespace = namespace + ".";
		}
		this.namespace = namespace;
	}
	
	@SuppressWarnings("null")
	@Override
	@Nonnull
	public String applyNamespace(@Nonnull String templateName) {
		if (templateName.startsWith(".")) {
			templateName = templateName.substring(1);
		}
		if (templateName.contains(".")) {
			return templateName;
		}
		return this.namespace + templateName;
	}

}
