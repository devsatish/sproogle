package com.mattwhipple.sproogle.closure.config;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

/**
 * Provides mapObjectMap which calls mapObject so only mapObject need be implemented
 */
public abstract class AbstractSoyDataMapper implements SoyDataMapper {

	@Override
	@Nonnull 
	public abstract SoyData mapObject(@Nonnull Object object);

	@Override
	@Nonnull
	public SoyMapData mapObjectMap(@Nullable Map<String, ? extends Object> map) {
		SoyMapData soyMapData = new SoyMapData();
		if (map == null) { return soyMapData; }
		for (Entry<String, ? extends Object> entry: map.entrySet()) {
			Object value = entry.getValue();
			if (value != null) {
				value = this.mapObject(value);
			}
			soyMapData.put(entry.getKey(), value);
		}
		return soyMapData; 
	}

}
