package com.hilook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry reg) {  // 리소스 핸들러 자동 추가
		reg.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/").setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/").setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/files/**").addResourceLocations("classpath:/static/files/").setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/filesup/**").addResourceLocations("classpath:/static/filesup/").setCachePeriod(60 * 60 * 24 * 365);
	}
}