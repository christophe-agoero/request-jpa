package fr.agoero.specification;


import static fr.agoero.specification.util.SpecificationUtil.getJoin;

import fr.agoero.entity.*;
import fr.agoero.metamodel.Contrat_;
import fr.agoero.metamodel.Personne_;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * Classe de specification pour Contrat
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings({"rawtypes", "unchecked"})
public final class ContratSpecification {

    public static Specification<Contrat> idIn(final List<Long> contratIdList) {
        return (root, query, criteriaBuilder) -> getIdIn(contratIdList, root, criteriaBuilder);
    }

    static Predicate getIdIn(final List<Long> contratIdList, final From from,
                             final CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.in(from.get(Contrat_.ID)).value(contratIdList);
    }

    public static Specification<Contrat> contratVersionActifEqual(final boolean active) {
        return (root, query, criteriaBuilder) -> {
            final Join<Contrat, ContratVersion> contratVersionJoin = getJoin(root,
                    Contrat_.CONTRAT_VERSION_SET);
            return ContratVersionSpecification.getActifEqual(active, contratVersionJoin, criteriaBuilder);
        };
    }

    public static Specification<Contrat> adresseMailLibelleEndWithIgnoreCase(final String rolePersonne,
                                                                                final String suffixMail) {
        return (root, query, criteriaBuilder) -> {
            final Join<Contrat, Societe> societeJoin = getJoin(root, Contrat_.SOCIETE);
            final Join<Societe, Personne> personneJoin = getJoin(societeJoin, rolePersonne);
            final Join<Personne, AdresseMail> adresseMAilJoin = getJoin(personneJoin,
                    Personne_.ADRESSE_MAIL_SET);
            return AdresseMailSpecification.getLibelleEndWithIgnoreCase(suffixMail, adresseMAilJoin,
                    criteriaBuilder);
        };
    }

}
