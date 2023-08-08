package guru.springframework.sdjpa.config;

import com.zaxxer.hikari.HikariDataSource;
import guru.springframework.sdjpa.model.CreditCardPAN;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaRepositories(basePackages = "guru.springframework.sdjpa.repositories.pan",
        entityManagerFactoryRef = "panEntityManagerFactory", transactionManagerRef = "panTransactionManager")
@Configuration
public class PanDatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.pan.datasource")
    public DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties cardDatasourceProperties) {
        return cardDatasourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(@Qualifier("panDataSource") DataSource dataSource,
                                                                          EntityManagerFactoryBuilder builder) { // inject from spring boot
        return builder.dataSource(dataSource)
                .packages(CreditCardPAN.class)
                .persistenceUnit("pan")
                .build();
    }
    @Bean
    public PlatformTransactionManager panTransactionManager(
            @Qualifier("panEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory) {
        return new JpaTransactionManager(panEntityManagerFactory.getObject());
    }
}
