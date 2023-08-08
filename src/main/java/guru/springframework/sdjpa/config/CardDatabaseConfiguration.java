package guru.springframework.sdjpa.config;

import com.zaxxer.hikari.HikariDataSource;
import guru.springframework.sdjpa.model.CreditCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaRepositories(basePackages = "guru.springframework.sdjpa.repositories.creditcard",
entityManagerFactoryRef = "cardEntityManagerFactory", transactionManagerRef = "cardTransactionManager")
@Configuration
public class CardDatabaseConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.card.datasource")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource cardDataSource(@Qualifier("cardDataSourceProperties") DataSourceProperties cardDatasourceProperties) {
        return cardDatasourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(@Qualifier("cardDataSource") DataSource dataSource,
                                                                           EntityManagerFactoryBuilder builder) { // inject from spring boot
        return builder.dataSource(dataSource)
                .packages(CreditCard.class)
                .persistenceUnit("card")
                .build();
    }
    @Bean
    public PlatformTransactionManager cardTransactionManager(
            @Qualifier("cardEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardEntityManagerFactory) {
        return new JpaTransactionManager(cardEntityManagerFactory.getObject());
    }
}
