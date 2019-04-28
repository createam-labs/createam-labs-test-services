package io.github.createam.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicates;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URL;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Createam demo application")
                        .description("Api documentation of dummy app.\n" + fetchHerokuSpringStarterReadmeAsString())
                        .version("0.1")
                        .license("MIT").licenseUrl("https://raw.githubusercontent.com/createam-labs/spring-boot-starter-heroku/master/LICENSE")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.createam.web"))
                .paths(PathSelectors.any())
                .build();
    }

    @SneakyThrows
    private String fetchHerokuSpringStarterReadmeAsString() {
        URL DescriptionUrl = new URL("https://raw.githubusercontent.com/createam-labs/spring-boot-starter-heroku/master/README.md");

        String herokuReadme = IOUtils.toString(DescriptionUrl);

        return herokuReadme;
    }
}