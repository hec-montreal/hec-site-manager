package org.sakaiproject.widget.tool;

import java.time.Instant;

import org.sakaiproject.widget.tool.formatter.DateTimeLocalFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.Setter;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebMvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private static final String UTF8 = "UTF-8";

    @Setter private ApplicationContext applicationContext;

    @Bean(name = "org.sakaiproject.widget.tool.Messages")
    public MessageSource messageSource() {
        ResourceLoaderMessageSource messages = new ResourceLoaderMessageSource();
        messages.setBasename("Messages");
        return messages;
    }

    @Bean(name = "dateTimeLocalFormatter")
    public Formatter dateTimeLocalFormatter() {
        return new DateTimeLocalFormatter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/icons/**").addResourceLocations("classpath:/icons/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(Instant.class, dateTimeLocalFormatter());
        super.addFormatters(registry);
    }

}