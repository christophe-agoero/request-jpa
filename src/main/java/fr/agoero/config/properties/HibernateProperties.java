package fr.agoero.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de log des proprietes de hibernate
 */
@Configuration
@ConfigurationProperties("spring.jpa.properties.hibernate")
@Getter
@Setter
@ToString
public class HibernateProperties {

    private String dialect;
    private boolean formatSql;
    private boolean useSqlComments;
}
