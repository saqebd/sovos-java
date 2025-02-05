package com.sovos.coupa;

import com.sovos.coupa.utility.CoupaRequestContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.context.WebApplicationContext;

import java.util.TimeZone;


@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
    ContextFunctionCatalogAutoConfiguration.class, RabbitAutoConfiguration.class,
    IntegrationAutoConfiguration.class})
@EnableConfigurationProperties
@EnableRetry
@ComponentScan({"com.sovos.coupa", "com.sovos.platform"})
public class CoupaApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    SpringApplication app = new SpringApplication(CoupaApplication.class);
    app.setAllowBeanDefinitionOverriding(true);
    app.run(args);
  }

  @Bean(name = "coupaRequestContext")
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public CoupaRequestContext coupaRequestContext() {
    return new CoupaRequestContext();
  }
}
