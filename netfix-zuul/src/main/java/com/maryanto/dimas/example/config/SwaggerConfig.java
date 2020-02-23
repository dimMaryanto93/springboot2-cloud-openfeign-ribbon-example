package com.maryanto.dimas.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.application.version}")
    private String applicationVersion;
    @Value("${spring.application.fullname}")
    private String applicationFullname;
    @Autowired
    private RouteLocator routeLocator;


    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(new ApiInfo(
                        applicationName,
                        applicationFullname,
                        applicationVersion,
                        "TERMS OF SERVICE URL",
                        new Contact("NAME", "URL", "EMAIL"),
                        "LICENSE",
                        "LICENSE URL",
                        new ArrayList<>()
                ));
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource(applicationName, "/v2/api-docs", applicationVersion));
        routeLocator.getRoutes().forEach(route -> {
            resources.add(swaggerResource(
                    route.getId(),
                    route.getFullPath().replace("**", "v2/api-docs"),
                    "1.0")
            );
        });
        return resources;
    }
}

