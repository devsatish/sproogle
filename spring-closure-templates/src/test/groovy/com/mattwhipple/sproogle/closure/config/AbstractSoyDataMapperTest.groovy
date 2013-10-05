package com.mattwhipple.sproogle.closure.config;

import javax.annotation.Nonnull;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData

import spock.lang.Specification

class AbstractSoyDataMapperTest extends Specification {
	SoyDataMapper soyDataMapperMock = Mock()
	
	def 'mapObjectMap passes all non null objeccts to mapObject and adds nulls as is (becoming NullData)'() {
		given:
		def map = [
			key1: 'value1',
			key2: null,
			key3: 3,
			]
		
		when:
		SoyMapData returnedMap = new StubSoyDataMapper(soyDataMapperMock).mapObjectMap(map)
		
		then:
		1 * soyDataMapperMock.mapObject('value1') >> SoyData.createFromExistingData('value1')
		1 * soyDataMapperMock.mapObject(3) >> SoyData.createFromExistingData(3)
		0 * soyDataMapperMock.mapObject(_)
		returnedMap.getString('key1') == 'value1'
		returnedMap.get('key2') instanceof com.google.template.soy.data.restricted.NullData
		returnedMap.getInteger('key3') == 3
	}
	
	def 'if mapObjectMap is passed null, return Null SoyMapData'() {
		expect:
		new StubSoyDataMapper(soyDataMapperMock).mapObjectMap(null).map == [:]
	}
	
	class StubSoyDataMapper extends AbstractSoyDataMapper {
		private SoyDataMapper mock;
		
		public StubSoyDataMapper(SoyDataMapper mock) {
			this.mock = mock;
		}
		
		@Override
		@Nonnull
		public SoyData mapObject(@Nonnull Object object) {
			return this.mock.mapObject(object);
		}
		
	}
}
