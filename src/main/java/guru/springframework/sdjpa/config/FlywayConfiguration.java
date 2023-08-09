package guru.springframework.sdjpa.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class FlywayConfiguration {

    @ConfigurationProperties("spring.card.flyway")
    @Bean
    public DataSourceProperties cardFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardFlyway(@Qualifier("cardFlywayDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return Flyway.configure()
                .dataSource(dataSourceProperties.getUrl(),
                        dataSourceProperties.getName(),
                        dataSourceProperties.getPassword())
                .locations("classpath:/db/migration/card")
                .load();
    }

    @ConfigurationProperties("spring.cardholder.flyway")
    @Bean
    public DataSourceProperties cardHolderFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardHolderFlyway(@Qualifier("cardHolderFlywayDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return Flyway.configure()
                .dataSource(dataSourceProperties.getUrl(),
                        dataSourceProperties.getName(),
                        dataSourceProperties.getPassword())
                .locations("classpath:/db/migration/cardholder")
                .load();
    }


    @ConfigurationProperties("spring.pan.flyway")
    @Bean
    public DataSourceProperties panFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway panFlyway(@Qualifier("panFlywayDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return Flyway.configure()
                .dataSource(dataSourceProperties.getUrl(),
                        dataSourceProperties.getName(),
                        dataSourceProperties.getPassword())
                .locations("classpath:/db/migration/pan")
                .load();
    }
}
