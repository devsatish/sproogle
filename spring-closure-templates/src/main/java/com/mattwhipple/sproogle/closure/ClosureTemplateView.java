package com.mattwhipple.sproogle.closure;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractTemplateView;

import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu.Renderer;
import com.mattwhipple.sproogle.closure.config.ClosureTemplateConfiguration;

public class ClosureTemplateView extends AbstractTemplateView {
	
	private Renderer soyTofuRenderer;
	private String templateName;
	
	private ClosureTemplateConfiguration closureTemplateConfiguration;

	@Override
	protected void renderMergedTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String templateName = this.templateName;
		if (templateName == null) { throw new IllegalStateException("templateName must be set before rendering"); }
		SoyMapData soyModel = this.closureTemplateConfiguration.getSoyDataMapper().mapObjectMap(model);
		String rendition = this.soyTofuRenderer.setData(soyModel).render();
		response.getWriter().write(rendition);
	}
	
	public void setSoyTofuRenderer(Renderer soyTofuRenderer) {
		this.soyTofuRenderer = soyTofuRenderer;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	ClosureTemplateConfiguration getClosureTemplateConfiguration() {
		return closureTemplateConfiguration;
	}

	public void setClosureTemplateConfiguration(
			ClosureTemplateConfiguration closureTemplateConfiguration) {
		this.closureTemplateConfiguration = closureTemplateConfiguration;
	}


}
