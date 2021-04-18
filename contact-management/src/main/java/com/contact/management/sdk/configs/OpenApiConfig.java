package com.contact.management.sdk.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

@Configuration
public class OpenApiConfig {

  @Bean
  @ConditionalOnMissingBean(BuildProperties.class)
  public BuildProperties buildProperties() {
    var prop = new Properties();
    prop.setProperty("version", "0.0.1-SNAPSHOT");
    prop.setProperty("name", "com-contact-management");
    prop.setProperty("group", "com.contact.management");
    prop.setProperty("artifact", "com-contact-management");
    return new BuildProperties(prop);
  }

  @Bean
  @Profile("!prod")
  public GroupedOpenApi actuatorApi() {
    return GroupedOpenApi.builder().group("Actuator")
        .pathsToMatch("/actuator/**")
        .pathsToExclude("/actuator/health/*")
        .build();
  }

  @Bean
  public OpenAPI customOpenAPI(BuildProperties buildProperties) {
    return new OpenAPI()
        .components(new Components().addSecuritySchemes("basicScheme",
            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
        .info(new Info().title("Contact Management API").version(buildProperties.getVersion()).description(
            "This is the OpenAPI Swagger Documentation. You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.")
            .termsOfService("http://swagger.io/terms/")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }
}
