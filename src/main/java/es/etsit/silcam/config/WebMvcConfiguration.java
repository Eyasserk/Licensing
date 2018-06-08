package es.etsit.silcam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfiguration {

	@Value("${silcam.portal.ciudadano.url}")
	private String urlPortalCiudadano;
	
	@Value("${silcam.portal.bpm.url}")
	private String urlPortalBpm;
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins(urlPortalBpm,urlPortalCiudadano);
            }
        };
    }
}
