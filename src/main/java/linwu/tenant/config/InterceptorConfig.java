//package linwu.tenant.config;
//
//import linwu.tenant.interceptor.TanentDataSourceInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @author Harvey
// * @title: InterceptorConfig
// * @package com.homedo.microservice.odin.wy.config
// * @date 2018/8/16 16:50
// */
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//  @Bean
//  public TanentDataSourceInterceptor getTanentDataSourceInterceptor() {
//    return new TanentDataSourceInterceptor();
//  }
//
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(getTanentDataSourceInterceptor());
//  }
//}
