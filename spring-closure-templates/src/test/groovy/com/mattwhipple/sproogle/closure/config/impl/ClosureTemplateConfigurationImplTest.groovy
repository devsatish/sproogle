package com.mattwhipple.sproogle.closure.config.impl;

import com.mattwhipple.sproogle.closure.config.AbstractSoyDataMapper;
import com.mattwhipple.sproogle.closure.config.SoyDataMapper
import com.mattwhipple.sproogle.closure.config.impl.ClosureTemplateConfigurationImpl.BasicSoyDataMapper

import spock.lang.Specification

class ClosureTemplateConfigurationImplTest extends Specification {

	def 'provides SoyDataMapper'() {
		given:
		SoyDataMapper soyDataMapperMock = Mock()
		
		expect:
		new ClosureTemplateConfigurationImpl(soyDataMapper: soyDataMapperMock).soyDataMapper == soyDataMapperMock
	}	
	
	def 'throws exception if SoyDataMapper is null'() {
		when:
		new ClosureTemplateConfigurationImpl(soyDataMapper:null)
		
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
}
