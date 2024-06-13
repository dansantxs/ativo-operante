package br.unoeste.fipp.ativooperante2024.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<AccessFilter> registrationBean(){

        FilterRegistrationBean<AccessFilter> register = new FilterRegistrationBean<>();
        register.setFilter(new AccessFilter());
        // definindo as URLs para aplicar o filtro
        register.addUrlPatterns("/apis/adm/*");
        register.addUrlPatterns("/apis/cidadao/*");
        return register;
    }
}

