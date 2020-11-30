package fr.agoero.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de log des proprietes de JPA
 */
@Configuration
@ConfigurationProperties("spring.jpa")
@Getter
@Setter
@ToString
public class JPAProperties {

    private boolean showSql;
    private Boolean openInView;
    private Boolean generateDdl;
}
