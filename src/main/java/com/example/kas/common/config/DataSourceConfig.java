package com.example.kas.common.config;
import com.example.kas.dao.AccountDAO;
import com.example.kas.dao.UserDAO;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Bean(name="h2DataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name="h2SqlSessionFactory")
    public SqlSessionFactory h2SqlSessionFactory(@Autowired @Qualifier("h2DataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/h2-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="h2SqlSessionTemplate")
    public SqlSessionTemplate h2SqlSessionTemplate(@Autowired @Qualifier("h2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @SuppressWarnings("resource")
    public UserDAO userDao(@Autowired @Qualifier("h2SqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(UserDAO.class);
    }

    @Bean
    @SuppressWarnings("resource")
    public AccountDAO accountDao(@Autowired @Qualifier("h2SqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(AccountDAO.class);
    }

}
