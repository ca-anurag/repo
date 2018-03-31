package com.hw.asnmt.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Class to initialize java configuration class and define the Servlet mapping.
 *
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String URL_PATTERN = "/";

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { URL_PATTERN };
	}

}
