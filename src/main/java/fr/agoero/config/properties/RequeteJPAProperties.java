package fr.agoero.config.properties;

import java.util.StringJoiner;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de log des proprietes
 */
@Configuration
@AllArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class RequeteJPAProperties {

    private final DataSourceProperties dataSourceProperties;
    private final JPAProperties jpaProperties;
    private final JPAHibernateProperties jpaHibernateProperties;
    private final HibernateProperties hibernateProperties;


    @Override
    public String toString() {
        return new StringJoiner(
                "\n",
                "\n------------------ Log all properties begin ------------------\n\n",
                "\n\n------------------ Log all properties end ------------------")
                .add(dataSourceProperties.toString())
                .add(jpaProperties.toString())
                .add(jpaHibernateProperties.toString())
                .add(hibernateProperties.toString())
                .toString();
    }

    @PostConstruct
    private void logProperties() {
        log.info(toString());
    }
}
