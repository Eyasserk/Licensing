package es.etsit.silcam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    
	/**
	@Autowired
	private TypeResolver typeResolver;

	@Value("${app.version}")
	private String appVersion;
	*/
	
	/**
	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("es.etsit.silcam"))
				.paths(PathSelectors.any()).build().pathMapping("/").genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
								.responseModel(new ModelRef("Error")).build()))
				.enableUrlTemplating(false)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		String title = "Silcam Rest";
		String description = "Silcam Backend Rest API";
		String termsOfServiceUrl = "urn:tos";
		Contact contact = new Contact("Yasser Kantour","https://github.com/yasskant","ykantour20@gmail.com");
		String licence = "Apache 2.0";
		String url = "http://www.apache.org/licenses/LICENSE-2.0";
		return new ApiInfo(title, description, appVersion, termsOfServiceUrl, contact, licence, url, Collections.emptyList());
	}
	*/
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("es.etsit.silcam"))
                .paths(PathSelectors.any())
                .build();
             
    }
	
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
