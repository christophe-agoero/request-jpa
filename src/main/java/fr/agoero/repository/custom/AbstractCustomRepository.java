package fr.agoero.repository.custom;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import fr.agoero.repository.util.SubGraphUtil;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

/**
 * Classe generique pour les methodes custom de repository
 */
@Slf4j
public abstract class AbstractCustomRepository<T> {

    private static final String FETCH_GRAPH = "javax.persistence.fetchgraph";
    @PersistenceContext
    protected EntityManager entityManager;
    @Setter
    private Class<T> entityClass;


    /**
     * Permet de recuperer une liste d entites en choisissant le graphe et la specification
     *
     * @param subGraphUtilList
     * @param specification
     * @return une liste d entites en choisissant le graphe
     */
    public List<T> findAllWithGraphAndSpecification(List<SubGraphUtil> subGraphUtilList,
                                                    @Nullable Specification<T> specification) {
        EntityGraph<T> graph = buildEntityGraphFromUtil(subGraphUtilList);
        CriteriaQuery<T> criteriaQuery = getCriteriaQuery(specification);
        return entityManager.createQuery(criteriaQuery)
                .setHint(FETCH_GRAPH, graph)
                .getResultList();
    }

    /**
     * Permet de recuperer une entite en choisissant le graphe et la specification
     *
     * @param subGraphUtilList
     * @param specification
     * @return une entite en choisissant le graphe
     */
    public Optional<T> findOneWithGraphAndSpecification(List<SubGraphUtil> subGraphUtilList,
                                                        @Nullable Specification<T> specification) {
        T result = null;
        T intermediateResult = null;
        EntityGraph<T> graph = buildEntityGraphFromUtil(subGraphUtilList);
        CriteriaQuery<T> criteriaQuery = getCriteriaQuery(specification);
        try {
            intermediateResult = entityManager.createQuery(criteriaQuery)
                    .setHint(FETCH_GRAPH, graph)
                    .getSingleResult();
        } catch (NoResultException e) {
            // intermediateResult reste a null
            // log pour code smell sonar
            log.debug("", e);
        }
        if (intermediateResult != null) {
            result = intermediateResult;
        }
        return Optional.ofNullable(result);
    }

    /**
     * Permet de creer le CriteriaQuery
     *
     * @param specification
     * @return CriteriaQuery
     */
    private CriteriaQuery<T> getCriteriaQuery(Specification<T> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        if (specification != null) {
            criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
        }
        return criteriaQuery;
    }


    /**
     * Permet de construire  EntityGraph
     *
     * @param subGraphUtilList
     * @return EntityGraph
     */
    private EntityGraph<T> buildEntityGraphFromUtil(List<SubGraphUtil> subGraphUtilList) {
        EntityGraph<T> graph = entityManager.createEntityGraph(entityClass);
        if (isNotEmpty(subGraphUtilList)) {
            subGraphUtilList.forEach(
                    subGraphUtil -> {
                        Subgraph<T> subgraph = graph.addSubgraph(subGraphUtil.getName());
                        if (isNotEmpty(subGraphUtil.getSubGraphUtilList())) {
                            subGraphUtil.getSubGraphUtilList()
                                    .forEach(
                                            childGraph -> addSubGraph(subgraph, childGraph)
                                    );
                        }
                    }
            );
        }
        return graph;
    }

    /**
     * Permet d ajouter un Subgraph
     *
     * @param parentSubgraph
     * @param childGraph
     */
    private void addSubGraph(Subgraph<T> parentSubgraph, SubGraphUtil childGraph) {
        Subgraph<T> subgraph = parentSubgraph.addSubgraph(childGraph.getName());
        if (isNotEmpty(childGraph.getSubGraphUtilList())) {
            childGraph.getSubGraphUtilList()
                    .forEach(
                            subGraphUtil -> addSubGraph(subgraph, subGraphUtil)
                    );
        }
    }


}
