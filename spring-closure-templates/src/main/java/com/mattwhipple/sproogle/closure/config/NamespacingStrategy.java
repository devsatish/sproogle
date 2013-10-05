package com.mattwhipple.sproogle.closure.config;

import javax.annotation.Nonnull;

public interface NamespacingStrategy {
	@Nonnull String applyNamespace(@Nonnull String templateName);
}
