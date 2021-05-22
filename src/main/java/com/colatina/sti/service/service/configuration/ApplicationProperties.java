package com.colatina.sti.service.service.configuration;

import lombok.Getter;
import lombok.Setter;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.util.Properties;

@ConfigurationProperties(prefix = "application.email")
@Configuration
@Getter
@Setter
public class ApplicationProperties {
    private String enderecoRemetente;
    private String nomeRemetente;


    @Bean
    public VelocityEngineFactoryBean velocityEngine() {
        VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
        Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setVelocityProperties(props);
        return velocityEngine;
    }
}
