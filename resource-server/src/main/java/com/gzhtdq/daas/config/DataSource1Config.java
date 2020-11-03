package com.gzhtdq.daas.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.gzhtdq.daas.api.mapper.ds1", sqlSessionTemplateRef = "dataSource1SqlSessionTemplate")
public class DataSource1Config {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    @Primary
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory dataSource1SqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource1());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/ds1/*Mapper.xml"));
        factoryBean.setTypeAliasesPackage("com.gzhtdq.daas.api.domain.ds1");
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager dataSource1DataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource1());
    }

    @Bean
    @Primary
    public SqlSessionTemplate dataSource1SqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(dataSource1SqlSessionFactory());
    }
}
