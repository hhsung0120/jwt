package site.heeseong.jwt.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import site.heeseong.jwt.interceptor.TokenCheckInterceptor;


@Configuration
public class CustomWebMvcConfigurer extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/invalidToken")
        ;
    }
}
