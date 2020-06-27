
package com.codebase.development.springinlinedb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.codebase.development.springinlinedb.constants.Constants;

/**
 * @author Bhaskar
 *
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurationSupport {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		registry.viewResolver(resolver);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(Constants.LOGIN);
		registry.addViewController("/login**").setViewName(Constants.LOGIN);
		registry.addViewController("/logout**").setViewName(Constants.LOGIN);
		registry.addViewController("/redeemDetails**").setViewName(Constants.REDEEM_DETAILS);
		registry.addViewController("/postredeemDetails**").setViewName(Constants.SUCCESS);
		registry.addViewController("/cashTransfer**").setViewName(Constants.CASHTRANSFER);
		registry.addViewController("/cashTransferSuccess**").setViewName(Constants.SUCCESS);
		registry.addViewController("/transactionHistory**").setViewName(Constants.TRANSACTION_HISTORY);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/", "classpath:/templates/",
				"classpath:/static/css/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}