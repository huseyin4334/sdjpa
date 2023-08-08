package guru.springframework.sdjpa.config;

import com.zaxxer.hikari.HikariDataSource;
import guru.springframework.sdjpa.model.CreditCardHolder;
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

@EnableJpaRepositories(basePackages = "guru.springframework.sdjpa.repositories.cardholder",
        entityManagerFactoryRef = "cardHolderEntityManagerFactory", transactionManagerRef = "cardHolderTransactionManager")
@Configuration
public class CardHolderDatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.cardholder.datasource")
    public DataSourceProperties cardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource cardHolderDataSource(@Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardDatasourceProperties) {
        return cardDatasourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory(@Qualifier("cardHolderDataSource") DataSource dataSource,
                                                                                 EntityManagerFactoryBuilder builder) { // inject from spring boot
        return builder.dataSource(dataSource)
                .packages(CreditCardHolder.class)
                .persistenceUnit("cardHolder")
                .build();
    }
    @Bean
    public PlatformTransactionManager cardHolderTransactionManager(
            @Qualifier("cardHolderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory) {
        return new JpaTransactionManager(cardHolderEntityManagerFactory.getObject());
    }
}
