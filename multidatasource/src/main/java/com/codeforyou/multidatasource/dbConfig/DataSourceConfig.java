// package com.codeforyou.multidatasource.dbConfig;


// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// import jakarta.persistence.EntityManagerFactory;


// @Configuration
// @EnableTransactionManagement
// public class DataSourceConfig {

//     @Primary
//     @Bean(name = "firstDataSource")
//     @ConfigurationProperties(prefix = "spring.datasource.first")
//     public DataSource firstDataSource() {
//         return DataSourceBuilder.create().build();
//     }

//     @Bean(name = "secondDataSource")
//     @ConfigurationProperties(prefix = "spring.datasource.second")
//     public DataSource secondDataSource() {
//         return DataSourceBuilder.create().build();
//     }

//     @Bean(name = "thirdDataSource")
//     @ConfigurationProperties(prefix = "spring.datasource.third")
//     public DataSource thirdDataSource() {
//         return DataSourceBuilder.create().build();
//     }



//     @Primary
//     @Bean(name = "firstEntityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
//             EntityManagerFactoryBuilder builder,
//             @Qualifier("firstDataSource") DataSource dataSource) {
//         return builder
//                 .dataSource(dataSource)
//                 .packages("com.yourpackage.model.first")
//                 .persistenceUnit("first")
//                 .build();
//     }

//     @Bean(name = "secondEntityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
//             EntityManagerFactoryBuilder builder,
//             @Qualifier("secondDataSource") DataSource dataSource) {
//         return builder
//                 .dataSource(dataSource)
//                 .packages("com.yourpackage.model.second")
//                 .persistenceUnit("second")
//                 .build();
//     }

//         @Bean(name = "thirdEntityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(
//             EntityManagerFactoryBuilder builder,
//             @Qualifier("thirdDataSource") DataSource dataSource) {
//         return builder
//                 .dataSource(dataSource)
//                 .packages("com.yourpackage.model.second")
//                 .persistenceUnit("third")
//                 .build();
//     }

//     @Primary
//     @Bean(name = "firstTransactionManager")
//     public PlatformTransactionManager firstTransactionManager(
//             @Qualifier("firstEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//         return new JpaTransactionManager(entityManagerFactory);
//     }

//     @Bean(name = "secondTransactionManager")
//     public PlatformTransactionManager secondTransactionManager(
//             @Qualifier("secondEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//         return new JpaTransactionManager(entityManagerFactory);
//     }

//         @Bean(name = "thirdTransactionManager")
//     public PlatformTransactionManager thirdTransactionManager(
//             @Qualifier("thirdEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//         return new JpaTransactionManager(entityManagerFactory);
//     }
// }