package fr.agoero.repository.custom.impl;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import fr.agoero.criteria.ContratCriteria;
import fr.agoero.entity.*;
import fr.agoero.metamodel.AdresseMail_;
import fr.agoero.metamodel.ContratVersion_;
import fr.agoero.metamodel.Contrat_;
import fr.agoero.metamodel.Personne_;
import fr.agoero.metamodel.Societe_;
import fr.agoero.repository.custom.AbstractCustomRepository;
import fr.agoero.repository.custom.ContratCustomRepository;
import fr.agoero.result.ContratProjectionResult;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

/**
 * Classe d implementation des methodes custom du repository contrat
 */
@SuppressWarnings({"unused", "squid:S1192"})
public class ContratCustomRepositoryImpl extends AbstractCustomRepository<Contrat>
        implements ContratCustomRepository {

    public ContratCustomRepositoryImpl() {
        super();
        setEntityClass(Contrat.class);
    }

    @Override
    public List<Contrat> findCriteriaGraph() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contrat> criteriaQuery = criteriaBuilder.createQuery(Contrat.class);
        Root<Contrat> contratRoot = criteriaQuery.from(Contrat.class);
        contratRoot.fetch(Contrat_.CONTRAT_VERSION_SET);
        Fetch<Contrat, Societe> societeFetch = contratRoot.fetch(Contrat_.SOCIETE);
        Fetch<Societe, Personne> avocatFetch = societeFetch.fetch(Societe_.AVOCAT);
        avocatFetch.fetch(Personne_.ADRESSE_MAIL_SET);
        societeFetch.fetch(Societe_.PRESIDENT);
        criteriaQuery.distinct(true);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Contrat> findCriteriaGraphWhere(List<Long> contratIdList, boolean contratVersionActif,
                                                String suffixMail) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contrat> criteriaQuery = criteriaBuilder.createQuery(Contrat.class);
        criteriaQuery.distinct(true);
        Root<Contrat> contratRoot = criteriaQuery.from(Contrat.class);
        Fetch<Contrat, ContratVersion> contratVersionFetch = contratRoot.fetch(Contrat_.CONTRAT_VERSION_SET);
        Join<Contrat, ContratVersion> contratVersionJoin =
                (Join<Contrat, ContratVersion>) contratVersionFetch;
        Fetch<Contrat, Societe> societeFetch = contratRoot.fetch(Contrat_.SOCIETE);
        Fetch<Societe, Personne> avocatFetch = societeFetch.fetch(Societe_.AVOCAT);
        Fetch<Personne, AdresseMail> adresseMailFetch = avocatFetch.fetch(Personne_.ADRESSE_MAIL_SET);
        Join<Personne, AdresseMail> adresseMailJoin = (Join<Personne, AdresseMail>) adresseMailFetch;
        societeFetch.fetch(Societe_.PRESIDENT);
        // predicate
        Predicate contratIdIn = contratRoot.get(Contrat_.ID).in(contratIdList);
        Predicate contratVersionActifEqual =
                criteriaBuilder.equal(contratVersionJoin.get(ContratVersion_.ACTIF),
                        contratVersionActif);
        Predicate adresseMailEndWithIgnoreCase =
                criteriaBuilder.like(criteriaBuilder.upper(adresseMailJoin.get(AdresseMail_.LIBELLE)),
                        "%" + suffixMail.toUpperCase());
        criteriaQuery.where(criteriaBuilder.and(contratIdIn, contratVersionActifEqual,
                adresseMailEndWithIgnoreCase));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<ContratProjectionResult> findCriteriaGraphWhereProjection(List<Long> contratIdList,
                                                                          boolean contratVersionActif,
                                                                          String suffixMail) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ContratProjectionResult> criteriaQuery =
                criteriaBuilder.createQuery(ContratProjectionResult.class);
        Root<Contrat> contratRoot = criteriaQuery.from(Contrat.class);
        Join<Contrat, ContratVersion> contratVersionJoin = contratRoot.join(Contrat_.CONTRAT_VERSION_SET);
        Join<Contrat, Societe> societeJoin = contratRoot.join(Contrat_.SOCIETE);
        Join<Societe, Personne> avocatJoin = societeJoin.join(Societe_.AVOCAT);
        Join<Personne, AdresseMail> adresseMailJoin = avocatJoin.join(Personne_.ADRESSE_MAIL_SET);
        Join<Societe, Personne> presidentJoin = societeJoin.join(Societe_.PRESIDENT);
        criteriaQuery.select(criteriaBuilder.construct(ContratProjectionResult.class,
                contratRoot.get(Contrat_.ID),
                contratRoot.get(Contrat_.NOM),
                contratVersionJoin.get(ContratVersion_.ID),
                contratVersionJoin.get(ContratVersion_.NUMERO_VERSION),
                societeJoin.get(Societe_.ID),
                societeJoin.get(Societe_.NOM),
                avocatJoin.get(Personne_.NOM),
                presidentJoin.get(Personne_.NOM),
                adresseMailJoin.get(AdresseMail_.LIBELLE)
                )
        );
        // predicate
        Predicate contratIdIn = contratRoot.get(Contrat_.ID).in(contratIdList);
        Predicate contratVersionActifEqual =
                criteriaBuilder.equal(contratVersionJoin.get(ContratVersion_.ACTIF),
                        contratVersionActif);
        Predicate adresseMailEndWithIgnoreCase =
                criteriaBuilder.like(criteriaBuilder.upper(adresseMailJoin.get(AdresseMail_.LIBELLE)),
                        "%" + suffixMail.toUpperCase());

        criteriaQuery.where(criteriaBuilder.and(contratIdIn, contratVersionActifEqual,
                adresseMailEndWithIgnoreCase));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contrat> findCriteriaGraphWhereDynamic(ContratCriteria contratCriteria) {
        List<Predicate> predicateList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contrat> criteriaQuery = criteriaBuilder.createQuery(Contrat.class);
        criteriaQuery.distinct(true);
        Root<Contrat> contratRoot = criteriaQuery.from(Contrat.class);
        Fetch<Contrat, ContratVersion> contratVersionFetch = contratRoot.fetch(Contrat_.CONTRAT_VERSION_SET);
        Fetch<Contrat, Societe> societeFetch = contratRoot.fetch(Contrat_.SOCIETE);
        Fetch<Societe, Personne> avocatFetch = societeFetch.fetch(Societe_.AVOCAT);
        Fetch<Personne, AdresseMail> adresseMailFetch = avocatFetch.fetch(Personne_.ADRESSE_MAIL_SET);
        societeFetch.fetch(Societe_.PRESIDENT);
        // predicate
        if (isNotEmpty(contratCriteria.getContratIdList())) {
            Predicate contratIdIn = contratRoot.get(Contrat_.ID).in(contratCriteria.getContratIdList());
            predicateList.add(contratIdIn);
        }
        if (contratCriteria.getContratVersionActif() != null) {
            Join<Contrat, ContratVersion> contratVersionJoin =
                    (Join<Contrat, ContratVersion>) contratVersionFetch;
            Predicate contratVersionActifEqual =
                    criteriaBuilder.equal(contratVersionJoin.get(ContratVersion_.ACTIF),
                            contratCriteria.getContratVersionActif());
            predicateList.add(contratVersionActifEqual);
        }
        if (isNotBlank(contratCriteria.getSuffixAvocatMail())) {
            Join<Personne, AdresseMail> adresseMailJoin = (Join<Personne, AdresseMail>) adresseMailFetch;
            Predicate adresseMailEndWithIgnoreCase =
                    criteriaBuilder.like(criteriaBuilder.upper(adresseMailJoin.get(AdresseMail_.LIBELLE)),
                            "%" + contratCriteria.getSuffixAvocatMail().toUpperCase());
            predicateList.add(adresseMailEndWithIgnoreCase);
        }
        criteriaQuery.where(predicateList.toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Contrat> findJPQLGraph() {
        TypedQuery<Contrat> typedQuery = entityManager.createQuery("SELECT DISTINCT c FROM Contrat c "
                        + "JOIN FETCH c.contratVersionSet cv "
                        + "JOIN FETCH c.societe s "
                        + "JOIN FETCH s.avocat a "
                        + "JOIN FETCH a.adresseMailSet am "
                        + "JOIN FETCH s.president p ",
                Contrat.class
        );
        return typedQuery.getResultList();
    }

    @Override
    public List<Contrat> findJPQLGraphWhere(List<Long> contratIdList, boolean contratVersionActif,
                                            String suffixMail) {
        TypedQuery<Contrat> typedQuery = entityManager.createQuery("SELECT DISTINCT c FROM Contrat c "
                        + "JOIN FETCH c.contratVersionSet cv "
                        + "JOIN FETCH c.societe s "
                        + "JOIN FETCH s.avocat a "
                        + "JOIN FETCH a.adresseMailSet am "
                        + "JOIN FETCH s.president p "
                        + "WHERE c.id IN (:contratIdList) "
                        + "AND cv.actif = :contratVersionActif "
                        + "AND UPPER(am.libelle)  LIKE :suffixMail ",
                Contrat.class
        );
        typedQuery.setParameter("contratIdList", contratIdList);
        typedQuery.setParameter("contratVersionActif", contratVersionActif);
        typedQuery.setParameter("suffixMail", "%" + suffixMail.toUpperCase());
        return typedQuery.getResultList();
    }

    @Override
    public List<ContratProjectionResult> findJPQLGraphWhereProjection(List<Long> contratIdList,
                                                                      boolean contratVersionActif,
                                                                      String suffixMail) {
        TypedQuery<ContratProjectionResult> typedQuery = entityManager.createQuery("SELECT NEW fr.agoero" +
                        ".result" +
                        ".ContratProjectionResult("
                        + "c.id,"
                        + "c.nom,"
                        + "cv.id,"
                        + "cv.numeroVersion,"
                        + "s.id,"
                        + "s.nom,"
                        + "a.nom,"
                        + "p.nom,"
                        + "am.libelle)"
                        + "FROM Contrat c "
                        + "JOIN  c.contratVersionSet cv "
                        + "JOIN  c.societe s "
                        + "JOIN  s.avocat a "
                        + "JOIN  a.adresseMailSet am "
                        + "JOIN  s.president p "
                        + "WHERE c.id IN (:contratIdList) "
                        + "AND cv.actif = :contratVersionActif "
                        + "AND UPPER(am.libelle)  LIKE :suffixMail ",
                ContratProjectionResult.class
        );
        typedQuery.setParameter("contratIdList", contratIdList);
        typedQuery.setParameter("contratVersionActif", contratVersionActif);
        typedQuery.setParameter("suffixMail", "%" + suffixMail.toUpperCase());
        return typedQuery.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ContratProjectionResult> findNativeGraphWhereProjection(List<Long> contratIdList,
                                                                        boolean contratVersionActif,
                                                                        String suffixMail) {
        Query query = entityManager.createNativeQuery("SELECT "
                        + "c.id AS contratId, "
                        + "c.nom AS contratNom, "
                        + "cv.id AS contratVersionId, "
                        + "cv.numero_version AS contratVersionNumero, "
                        + "s.id AS societeId, "
                        + "s.nom AS societeNom, "
                        + "a.nom AS avocatNom, "
                        + "p.nom AS presidentNom, "
                        + "am.libelle AS avocatMail "
                        // from
                        + "FROM contrat c "
                        + "JOIN contrat_version cv ON c.id = cv.fk_contrat_id "
                        + "JOIN societe s ON c.fk_societe_id = s.id  "
                        + "JOIN personne p ON s.fk_personne_president_id = p.id "
                        + "JOIN personne a ON s.fk_personne_avocat_id = a.id "
                        + "JOIN adresse_mail am ON a.id = am.fk_personne_id "
                        // where
                        + "WHERE c.id IN (:contratIdList) AND cv.actif IS TRUE "
                        + "AND cv.actif = :contratVersionActif "
                        + "AND UPPER(am.libelle)  LIKE :suffixMail",
                "ContratProjectionMapping");
        query.setParameter("contratIdList", contratIdList);
        query.setParameter("contratVersionActif", contratVersionActif);
        query.setParameter("suffixMail", "%" + suffixMail.toUpperCase());
        return query.getResultList();
    }

}
