package com.blaxsior.exhandle;

import com.blaxsior.exhandle.filter.LogFilter;
import com.blaxsior.exhandle.interceptor.LogInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "*.ico", "/error"
                        , "/error-page/**"
                ); // dispatcherType은 사용 불가. excludePattern으로 처리
    }
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new LogFilter());
        regBean.setOrder(1);
        // 모든 경로. 필터와 인터셉터는 사용되는 패턴이 다름.
        regBean.addUrlPatterns("/*");
        regBean.setDispatcherTypes(
                DispatcherType.REQUEST,
                DispatcherType.ERROR
        );
        return regBean;
    }
}
