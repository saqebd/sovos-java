package com.sovos.coupa.config;

import com.sovos.coupa.utility.CoupaHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//TODO: Check if this is needed
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private CoupaHandlerInterceptor coupaHandlerInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(coupaHandlerInterceptor);
  }
}
