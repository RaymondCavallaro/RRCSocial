package rrc.social.auth.common.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import rrc.social.auth.common.interceptor.LoggerInterceptor;
import rrc.social.auth.common.interceptor.TimestamptzHeaderInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
		registry.addInterceptor(new TimestamptzHeaderInterceptor());
	}
}