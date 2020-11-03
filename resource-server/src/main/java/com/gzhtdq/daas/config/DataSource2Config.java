package com.gzhtdq.daas.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.gzhtdq.daas.api.mapper.ds2", sqlSessionTemplateRef = "dataSource2SqlSessionTemplate")
public class DataSource2Config {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory dataSource2SqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource2());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/ds2/*Mapper.xml"));
        factoryBean.setTypeAliasesPackage("com.gzhtdq.daas.api.domain.ds2");
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager dataSource2DataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource2());
    }

    @Bean
    public SqlSessionTemplate dataSource2SqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(dataSource2SqlSessionFactory());
    }
}
