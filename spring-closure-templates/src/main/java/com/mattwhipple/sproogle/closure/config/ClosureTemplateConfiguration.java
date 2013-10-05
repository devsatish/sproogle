package com.mattwhipple.sproogle.closure.config;

import javax.annotation.Nonnull;

public interface ClosureTemplateConfiguration {
	@Nonnull SoyDataMapper getSoyDataMapper();
}
