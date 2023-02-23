package com.hasan.springbootmultipledb.teacher.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "teacherEntityManagerFactoryBean",
        basePackages = {"com.hasan.springbootmultipledb.teacher.repo"},
        transactionManagerRef = "teacherTransactionManager"
)
public class TeacherDBConfig {

    // To access application.properties file need an env variable

    @Autowired
    private Environment env;

    // datasource
    @Bean(name="teacherDataSource")
    @Primary
    public DataSource teacherDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("teacher.datasource.url"));
        dataSource.setDriverClassName(env.getProperty("teacher.datasource.driver-class-name"));
        dataSource.setUsername(env.getProperty("teacher.datasource.username"));
        dataSource.setPassword(env.getProperty("teacher.datasource.password"));

        return dataSource;
    }

    // entity manager factory
    @Bean(name = "teacherEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean teacherEntityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(teacherDataSource());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);

        em.setPackagesToScan("com.hasan.springbootmultipledb.teacher.entities");

        return em;

    }

    // platformTransactionManager
    @Bean(name = "teacherTransactionManager")
    public PlatformTransactionManager teacherTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(teacherEntityManagerFactoryBean().getObject());
        return transactionManager;
    }

}
