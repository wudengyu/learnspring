package holenote;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import holenote.business.BusinessConfig;
import holenote.security.SecurityConfig;
import holenote.web.WebConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { BusinessConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}