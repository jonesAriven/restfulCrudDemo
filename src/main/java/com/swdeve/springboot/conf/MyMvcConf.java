package com.swdeve.springboot.conf;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.swdeve.springboot.component.LoginHandlerInterceptor;
import com.swdeve.springboot.component.MyLocaleResolver;

@Configuration
public class MyMvcConf extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("swdeve").setViewName("success");
	}

	// 所有的WebMvcConfigurerAdapter组件都会一起起作用
	@Bean // 将组件注册在容器
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				// TODO Auto-generated method stub
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/index").setViewName("login");
				registry.addViewController("/index.html").setViewName("login");
				registry.addViewController("/main.html").setViewName("dashboard");
			}

			// 注册拦截器
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				//静态资源springboot已经做好了映射，不用管，可以直接访问静态资源
				// TODO Auto-generated method stub
				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/",
						"/index", "/index.html","/user/login");
			}

		};

		return webMvcConfigurerAdapter;
	}

	@Bean // 将自己定义的区域信息解析器放到容器中
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}

}
