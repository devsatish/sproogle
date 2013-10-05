package com.mattwhipple.sproogle.closure.config;

import com.mattwhipple.sproogle.closure.config.SoyDataMapper
import com.mattwhipple.sproogle.closure.config.ClosureTemplateConfiguration.BasicSoyDataMapper
import spock.lang.Specification

class ClosureTemplateConfigurationTest extends Specification {

	def 'provides SoyDataMapper'() {
		given:
		SoyDataMapper soyDataMapperMock = Mock()
		
		expect:
		new ClosureTemplateConfiguration(soyDataMapper: soyDataMapperMock).soyDataMapper == soyDataMapperMock
	}	
	
	def 'throws exception if SoyDataMapper is null'() {
		when:
		new ClosureTemplateConfiguration(soyDataMapper:null)
		
		then:
		thrown(IllegalArgumentException)
	}
	
	def 'BasicSoyDataMapper is default and calls standard SoyData static methods'() {
		when:
		SoyDataMapper soyDataMapper = new ClosureTemplateConfiguration().getSoyDataMapper()
		
		then:
		soyDataMapper instanceof BasicSoyDataMapper
		soyDataMapper instanceof AbstractSoyDataMapper
		soyDataMapper.mapObject(23).integerValue() == 23
		soyDataMapper.mapObject(null)
	}
}
