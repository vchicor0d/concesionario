/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.configuracion;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author vchico
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.atsistemas.concesionario.persistencia.data")
public class ConfiguracionCore {
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:derby://localhost:1527/Concesionario;create=true");
        bds.setUsername("admin");
        bds.setPassword("admin");
        bds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        return bds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(ds);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.atsistemas.concesionario.entidades");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(adapter);
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
        jpaProperties.setProperty("hibernate.show.sql", "true");
        jpaProperties.setProperty("hibernate.format.sql", "true");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
        return localContainerEntityManagerFactoryBean;
    }
}
