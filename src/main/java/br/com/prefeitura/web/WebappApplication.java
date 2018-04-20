package br.com.prefeitura.web;

import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class WebappApplication extends WebMvcConfigurerAdapter {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(WebappApplication.class);
//	}
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(new Locale ("pt", "BR"));
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("lang");
	    return localeChangeInterceptor;
	}
	
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
//	@Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }
	
//	@Bean
//    public DataSource dataSource() {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setMaximumPoolSize(100);
//        ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//        ds.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/test");
//        ds.addDataSourceProperty("user", "root");
//        ds.addDataSourceProperty("password", "password");
//        ds.addDataSourceProperty("cachePrepStmts", true);
//        ds.addDataSourceProperty("prepStmtCacheSize", 250);
//        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//        ds.addDataSourceProperty("useServerPrepStmts", true);
//        return ds;
//    }
	
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }
	
	 //Add converter and configuration annotation
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ConverterFornecedor());
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}
