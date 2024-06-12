package com.distribuida.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.distribuida.servicios")
@EnableTransactionManagement
public class AppConfig {

    private JpaVendorAdapter jpaVendorAdapter(){
        var jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);

        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean
    public DataSource dataSource(){
        var dataSource= new HikariDataSource();
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setJdbcUrl("jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1");
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }

    Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds){

        var emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("com.distribuida.db");
        emf.setDataSource(ds);
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setJpaProperties(additionalProperties());
        return emf;

    }


}
