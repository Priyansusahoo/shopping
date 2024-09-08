package com.priyansu.shopping.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create(); // customization option available
        /*
         * return new Gson();
         * Default Gson instance
         */
    }
}
