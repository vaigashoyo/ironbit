package com.ironbit.invex.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Global configuration of our application
 * @author: David Loyo
 * @version: 1.0
 */
@Configuration
public class AppConfiguration {

    /**
     * <p>Bean for all resquest HTTP</p>
     * @return Http headers data
     * @since 1.0
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

    /**
     * <p>Bean for OpenApi configuration</p>
     * @return A custom OpenApi instance
     * @since 1.0
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Employee API")
                        .description("Services CRUD Employee")
                        .contact(new Contact()
                                .name("David Loyo")
                                .email("david@loyo.dev")
                                .url("https://github.com/vaigashoyo/ironbit.git"))
                        .version("1.0"));
    }
}
