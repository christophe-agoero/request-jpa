package fr.agoero.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de log des proprietes de JPA pour hibernate
 */
@Configuration
@ConfigurationProperties("spring.jpa.hibernate")
@Getter
@Setter
@ToString
public class JPAHibernateProperties {

    private String ddlAuto;
    private Boolean useNewIdGeneratorMappings;
}
