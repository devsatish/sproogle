package com.mattwhipple.sproogle.closure.config;

import java.util.Map;

import javax.annotation.Nonnull;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

/**
 * Used to map provided objects to SoyData objects usable by Closure
 */
public interface SoyDataMapper {
	@Nonnull SoyData mapObject(@Nonnull Object object);
	@Nonnull SoyMapData mapObjectMap(@Nonnull Map<String, ? extends Object> map);
}
