package com.hilook.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration // 설정 관련 클래스
@RequiredArgsConstructor
@Slf4j
@MapperScan("com.hilook.mappers")
public class MyBatisConfig {
	private final ApplicationContext applicationContext;
	
	/*
	 * @Bean
	 * @Configuration 또는 @Component가 작성된 클래스의 메서드에 사용함.
	 * 메서드의 리턴 객체를 Spring 컨테이너에 등록
	 * 객체 명은 메서드의 이름으로 자동 설정된다.
	 * 직접 설정 시 (@Bean(name="객체명"))으로 설정하면 된다.
	 */
	
	// 1. Property 가져오기
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	// 2. DataSource 설정
	@Bean
	public DataSource dataSource() {
		HikariDataSource hds = new HikariDataSource(hikariConfig());
		return hds;
	}
	
	// 3. SQLSessionFactory 만들기
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource());
		
		// SQL query를 작성할 xml 경로 설정
		sfb.setMapperLocations(applicationContext.getResources("classpath*:/mappers/*.xml"));
		sfb.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));
		
		try {
			SqlSessionFactory factory = sfb.getObject();
			factory.getConfiguration().setMapUnderscoreToCamelCase(false);
			return factory;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}