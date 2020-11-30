package fr.agoero.specification;

import fr.agoero.entity.ContratVersion;
import fr.agoero.metamodel.ContratVersion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * Classe de specification pour ContratVersion
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("rawtypes")
public final class ContratVersionSpecification {

    public static Specification<ContratVersion> actifEqual(final boolean active) {
        return (root, query, criteriaBuilder) ->
                getActifEqual(active, root, criteriaBuilder);
    }

    static Predicate getActifEqual(final boolean actif, final From from,
                                   final CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(from.get(ContratVersion_.ACTIF), actif);
    }

}
