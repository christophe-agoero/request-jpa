package fr.agoero.repository.custom;

import fr.agoero.criteria.ContratCriteria;
import fr.agoero.entity.Contrat;
import fr.agoero.repository.util.SubGraphUtil;
import fr.agoero.result.ContratProjectionResult;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

/**
 * Interface pour les methodes custom du repository Contrat
 */
public interface ContratCustomRepository {

    List<Contrat> findAllWithGraphAndSpecification(List<SubGraphUtil> subGraphUtilList,
                                                   Specification<Contrat> specification);

    List<Contrat> findCriteriaGraph();

    List<Contrat> findCriteriaGraphWhere(List<Long> contratIdList, boolean contratVersionActif,
                                         String suffixMail);

    List<ContratProjectionResult> findCriteriaGraphWhereProjection(List<Long> contratIdList,
                                                                   boolean contratVersionActif,
                                                                   String suffixMail);

    List<Contrat> findCriteriaGraphWhereDynamic(ContratCriteria contratCriteria);

    List<Contrat> findJPQLGraph();

    List<Contrat> findJPQLGraphWhere(List<Long> contratIdList, boolean contratVersionActif,
                                     String suffixMail);

    List<ContratProjectionResult> findJPQLGraphWhereProjection(List<Long> contratIdList,
                                                               boolean contratVersionActif,
                                                               String suffixMail);

    List<ContratProjectionResult> findNativeGraphWhereProjection(List<Long> contratIdList,
                                                                 boolean contratVersionActif,
                                                                 String suffixMail);
}
