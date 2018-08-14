package com.gara.lock_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @description: Config 类
 * @author: GaraYing
 * @create: 2018-08-14 18:05
 **/

@Configuration
public class TemplateConfig {

    @Bean
    RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(100000);
        requestFactory.setReadTimeout(100000);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
