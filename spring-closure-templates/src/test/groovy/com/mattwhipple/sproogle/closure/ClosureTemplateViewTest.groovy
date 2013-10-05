package com.mattwhipple.sproogle.closure;

import com.google.template.soy.data.SoyMapData
import com.google.template.soy.tofu.SoyTofu
import com.google.template.soy.tofu.SoyTofu.Renderer
import com.mattwhipple.sproogle.closure.config.ClosureTemplateConfiguration;
import com.mattwhipple.sproogle.closure.config.SoyDataMapper
import com.mattwhipple.sproogle.closure.config.impl.ClosureTemplateConfigurationImpl
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import spock.lang.Specification

class ClosureTemplateViewTest extends Specification {

	ClosureTemplateConfiguration closureTemplateConfigurationTest
	SoyDataMapper soyDataMapperMock = Mock()
	SoyTofu soyTofuMock = Mock()
	HttpServletResponse responseMock = Mock()
	HttpServletRequest requestMock = Mock()
	Writer responseWriterMock = Mock() 
	PrintWriter responseWriterTest = new PrintWriter(responseWriterMock)
	
	ClosureTemplateView closureTemplateView = new ClosureTemplateView()
	
	def setup() {
		closureTemplateConfigurationTest = new ClosureTemplateConfigurationImpl(
			soyDataMapper: soyDataMapperMock)
		
		closureTemplateView.setClosureTemplateConfiguration(closureTemplateConfigurationTest)
		closureTemplateView.setSoyTofu(soyTofuMock)
		
		responseMock.getWriter() >> responseWriterTest
	}
	
	def 'accepts ClosureTemplateConfiguration'() {
		ClosureTemplateConfiguration closureTemplateConfigurationMock = Mock()
		
		expect:
		new ClosureTemplateView(closureTemplateConfiguration: closureTemplateConfigurationMock)
			.closureTemplateConfiguration == closureTemplateConfigurationMock
	}
	
	def 'renderMergedTemplateModel calls render on passed SoyTofu with mapped model'() {
		given:
		Map<String, Object> modelTest = [:]
		SoyMapData soyModel = new SoyMapData()
		closureTemplateView.setTemplateName("Test")
		Renderer rendererMock = Mock()
		
		when:
		closureTemplateView.renderMergedTemplateModel(modelTest, requestMock, responseMock)
		
		then:
		1 * soyDataMapperMock.mapObjectMap(modelTest) >> soyModel 
		1 * soyTofuMock.newRenderer("Test") >> rendererMock
		1 * rendererMock.setData(soyModel) >> rendererMock
		1 * rendererMock.render() >> "Rendition"
		1 * responseWriterMock.write("Rendition", _, _)
	}

}
