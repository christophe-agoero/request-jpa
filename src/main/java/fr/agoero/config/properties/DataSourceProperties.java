package fr.agoero.config.properties;

import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de log des proprietes de dataSource
 */
@Configuration
@ConfigurationProperties("spring.datasource")
@Setter
@ToString
public class DataSourceProperties {

    private String url;
    private String username;
}
