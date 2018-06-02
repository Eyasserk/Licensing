package es.etsit.silcam.config;
/**
 * Spring MVC URL negotiation
 * Enables dot-ended routes to be mapped AS-IS
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class ContentNegotiationConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}
}

