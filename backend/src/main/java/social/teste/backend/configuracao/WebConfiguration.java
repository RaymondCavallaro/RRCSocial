package social.teste.backend.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import social.teste.backend.interceptor.LoggerInterceptor;
import social.teste.backend.interceptor.TimestamptzHeaderInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
		registry.addInterceptor(new TimestamptzHeaderInterceptor());
	}
}