package fr.agoero.specification;


import fr.agoero.entity.Contrat;
import fr.agoero.metamodel.AdresseMail_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * Classe de specification pour AdresseMail
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings({"rawtypes", "unchecked"})
public final class AdresseMailSpecification {

    public static Specification<Contrat> libelleEndWithIgnoreCase(final String suffixMail) {
        return (root, query, criteriaBuilder) -> getLibelleEndWithIgnoreCase(suffixMail, root,
                criteriaBuilder);
    }

    static Predicate getLibelleEndWithIgnoreCase(final String suffixMail, final From from,
                                                 final CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(criteriaBuilder.upper(from.get(AdresseMail_.LIBELLE)),
                "%" + suffixMail.toUpperCase());
    }

}
