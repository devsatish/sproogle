package com.mattwhipple.sproogle.closure.config.impl;

import com.mattwhipple.sproogle.closure.config.AbstractSoyDataMapper;
import com.mattwhipple.sproogle.closure.config.NamespacingStrategy;
import com.mattwhipple.sproogle.closure.config.SoyDataMapper
import com.mattwhipple.sproogle.closure.config.impl.ClosureTemplateConfigurationImpl.BasicSoyDataMapper
import com.mattwhipple.sproogle.closure.config.impl.ClosureTemplateConfigurationImpl.NoopNamespacingStrategy

import spock.lang.Specification

class ClosureTemplateConfigurationImplTest extends Specification {

	def 'provides SoyDataMapper, NamespacingStrategy'() {
		given:
		SoyDataMapper soyDataMapperMock = Mock()
		NamespacingStrategy namespacingStrategyMock = Mock()
		
		when:
		def configuration = new ClosureTemplateConfigurationImpl(
			soyDataMapper: soyDataMapperMock,
			namespacingStrategy: namespacingStrategyMock)
		
		then:
		configuration.soyDataMapper == soyDataMapperMock
		configuration.namespacingStrategy == namespacingStrategyMock
	}	
	
	def 'throws exception if provided SoyDataMapper is null'() {
		when:
		new ClosureTemplateConfigurationImpl(soyDataMapper:null)
		
		then:
		thrown(IllegalArgumentException)
	}

	def 'throws exception if provided NamespacingStrategy is null'() {
		when:
		new ClosureTemplateConfigurationImpl(namespacingStrategy:null)
		
		then:
		thrown(IllegalArgumentException)
	}

	
	def 'BasicSoyDataMapper is default and calls standard SoyData static methods'() {
		when:
		SoyDataMapper soyDataMapper = new ClosureTemplateConfigurationImpl().getSoyDataMapper()
		
		then:
		soyDataMapper instanceof BasicSoyDataMapper
		soyDataMapper instanceof AbstractSoyDataMapper
		soyDataMapper.mapObject(23).integerValue() == 23
		soyDataMapper.mapObject(null)
	}
	
	def 'NoopNamespacingStrategy is default and returns argument untouched'() {
		when:
		NamespacingStrategy namespacingStrategy = new ClosureTemplateConfigurationImpl().getNamespacingStrategy()
		
		then:
		namespacingStrategy instanceof NoopNamespacingStrategy
		namespacingStrategy.applyNamespace("Anything") == "Anything"
	}
}
