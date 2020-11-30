package fr.agoero.repository;

import static fr.agoero.specification.util.SpecificationUtil.initOrAndSpecification;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.jupiter.api.Assertions.*;

import fr.agoero.criteria.ContratCriteria;
import fr.agoero.entity.*;
import fr.agoero.graph.ContratGraph;
import fr.agoero.metamodel.Contrat_;
import fr.agoero.metamodel.Personne_;
import fr.agoero.repository.util.SubGraphUtil;
import fr.agoero.result.ContratIdNameResult;
import fr.agoero.result.ContratProjectionResult;
import fr.agoero.result.IdResult;
import fr.agoero.specification.ContratSpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Classe de test du repository ContratRepository
 */
@SpringBootTest
@DisplayName("Test repository ContratRepository")
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ContratRepositoryTest {

    public static final List<Long> CONTRAT_ID_LIST = Arrays.asList(1L, 2L);
    public static final boolean CONTRAT_VERSION_ACTIF = true;
    public static final String SUFFIX_MAIL = ".COM";
    public static final List<SubGraphUtil> GRAPH = Arrays.asList(ContratGraph.getContratVersionGraph(),
            ContratGraph.getSocPdtAvoMailGraph());

    @Autowired
    private ContratRepository contratRepository;

    @Test
    @DisplayName("test findById -> JpaRepository query")
    void testFindById() {
        Contrat contrat = contratRepository.findById(1L).orElse(null);
        assertNotNull(contrat);
        assertNoGraph(contrat);
    }

    @Test
    @DisplayName("test findAll -> JpaRepository query")
    void testFindAll() {
        List<Contrat> contratList = contratRepository.findAll();
        assertNoGraph(contratList);
    }

    @Test
    @DisplayName("test findBySocieteId -> Derived query")
    void testFindBySocieteId() {
        List<Contrat> contratList = contratRepository.findBySocieteId(1L);
        assertNoGraph(contratList);
    }

    @Test
    @DisplayName("test findBySocieteNomStartsWith -> Derived query")
    void testFindBySocieteNomStartsWith() {
        List<Contrat> contratList = contratRepository.findBySocieteNomStartsWith("soc");
        assertNoGraph(contratList);
    }

    @Test
    @DisplayName("test findBySocieteIdContratIdNameResult -> Derived query projection (id,nom)")
    void testFindBySocieteIdContratIdNameResult() {
        List<ContratIdNameResult> contratIdNameResultList = contratRepository.findBySocieteId(1L,
                ContratIdNameResult.class);
        assertTrue(isNotEmpty(contratIdNameResultList));
    }

    @Test
    @DisplayName("test findBySocieteIdContratIdResult -> Derived query projection id")
    void testFindBySocieteIdContratIdResult() {
        List<IdResult> idResultList = contratRepository.findBySocieteId(1L, IdResult.class);
        assertTrue(isNotEmpty(idResultList));
    }

    @Test
    @DisplayName("test findJPQL -> JPQL query with graph")
    void testFindJPQLGraph() {
        List<Contrat> contratList = contratRepository.findJPQLGraph();
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }

    @Test
    @DisplayName("test findJPQLWhere -> JPQL query with graph and clause where")
    void testFindJPQLGraphWhere() {
        List<Contrat> contratList = contratRepository.findJPQLGraphWhere(CONTRAT_ID_LIST,
                CONTRAT_VERSION_ACTIF,
                SUFFIX_MAIL);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }

    @Test
    @DisplayName("test findJPQLWhereProjection -> JPQL query with graph, clause where and projection")
    void testFindJPQLGraphWhereProjection() {
        List<ContratProjectionResult> contratProjectionResultList =
                contratRepository.findJPQLGraphWhereProjection(CONTRAT_ID_LIST, CONTRAT_VERSION_ACTIF,
                        SUFFIX_MAIL);
        assertTrue(isNotEmpty(contratProjectionResultList));
    }

    @Test
    @DisplayName("test testFindCustom -> AbstractCustomRepository query with graph")
    void testFindCustomGraph() {
        List<Contrat> contratList = contratRepository.findAllWithGraphAndSpecification(GRAPH, null);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }

    @Test
    @DisplayName("test findCustomWhere -> AbstractCustomRepository query with graph and clause where")
    @SuppressWarnings("ConstantConditions")
    void testFindCustomGraphWhere() {
        Specification<Contrat> contratSpecification =
                ContratSpecification.idIn(CONTRAT_ID_LIST)
                        .and(ContratSpecification.contratVersionActifEqual(CONTRAT_VERSION_ACTIF))
                        .and(ContratSpecification.adresseMailLibelleEndWithIgnoreCase(Personne_.AVOCAT,
                                SUFFIX_MAIL));
        List<Contrat> contratList = contratRepository.findAllWithGraphAndSpecification(GRAPH,
                contratSpecification);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }

    @Test
    @DisplayName("test testFindCustomWhereDynamic -> AbstractCustomRepository query graph and dynamic " +
            "clause where")
    void testFindCustomGraphWhereDynamic() {
        // sans critere
        ContratCriteria contratCriteria = ContratCriteria.builder().build();
        callRepositoryAndAssert(contratCriteria);
        // critere partiel
        contratCriteria = ContratCriteria.builder()
                .contratVersionActif(CONTRAT_VERSION_ACTIF)
                .suffixAvocatMail(SUFFIX_MAIL)
                .build();
        callRepositoryAndAssert(contratCriteria);
        // tous les criteres
        contratCriteria = ContratCriteria.builder()
                .contratIdList(CONTRAT_ID_LIST)
                .contratVersionActif(CONTRAT_VERSION_ACTIF)
                .suffixAvocatMail(SUFFIX_MAIL)
                .build();
        callRepositoryAndAssert(contratCriteria);
    }

    @Test
    @DisplayName("test findAllCriteria -> Criteria query with graph")
    void testFindCriteriaGraph() {
        List<Contrat> contratList = contratRepository.findCriteriaGraph();
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }

    @Test
    @DisplayName("test findCriteriaWhere -> Criteria query with graph and clause where")
    void testFindCriteriaGraphWhere() {
        List<Contrat> contratList = contratRepository.findCriteriaGraphWhere(CONTRAT_ID_LIST,
                CONTRAT_VERSION_ACTIF,
                SUFFIX_MAIL);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }

    @Test
    @DisplayName("test findAllCriteria -> Criteria query with graph and dynamic clause where")
    void testFindCriteriaGraphWhereDynamic() {
        // sans critere
        ContratCriteria contratCriteria = ContratCriteria.builder().build();
        List<Contrat> contratList = contratRepository.findCriteriaGraphWhereDynamic(contratCriteria);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
        // critere partiel
        contratCriteria = ContratCriteria.builder()
                .contratVersionActif(CONTRAT_VERSION_ACTIF)
                .suffixAvocatMail(SUFFIX_MAIL)
                .build();
        contratList = contratRepository.findCriteriaGraphWhereDynamic(contratCriteria);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
        // tous les criteres
        contratCriteria = ContratCriteria.builder()
                .contratIdList(CONTRAT_ID_LIST)
                .contratVersionActif(CONTRAT_VERSION_ACTIF)
                .suffixAvocatMail(SUFFIX_MAIL)
                .build();
        contratList = contratRepository.findCriteriaGraphWhereDynamic(contratCriteria);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }

    @Test
    @DisplayName("test findProjectionCriteria -> Criteria query with graph, clause where and projection")
    void testFindCriteriaGraphWhereProjection() {
        List<ContratProjectionResult> contratProjectionResultList =
                contratRepository.findCriteriaGraphWhereProjection(CONTRAT_ID_LIST,
                        CONTRAT_VERSION_ACTIF, SUFFIX_MAIL);
        assertTrue(isNotEmpty(contratProjectionResultList));
    }

    @Test
    @DisplayName("test findNativeWhereProjection -> Native query with with graph, clause where and " +
            "projection")
    void testFindNativeGraphWhereProjection() {
        List<ContratProjectionResult> contratProjectionResultList =
                contratRepository.findNativeGraphWhereProjection(CONTRAT_ID_LIST,
                        CONTRAT_VERSION_ACTIF, SUFFIX_MAIL);
        assertTrue(isNotEmpty(contratProjectionResultList));
    }

    @Test
    @DisplayName("test findByExemple -> QueryByExampleExecutor query")
    void testFindByExemple() {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "societeId");
        Contrat contratExample = new Contrat();
        // matcher exact
        contratExample.setNom("contrat1");
        matcher = matcher.withMatcher(Contrat_.NOM, GenericPropertyMatcher::exact);
        List<Contrat> contratList = contratRepository.findAll(Example.of(contratExample, matcher));
        assertTrue(isNotEmpty(contratList));
        assertEquals(1, contratList.size());
        // matcher startWith
        contratExample.setNom("contrat");
        matcher = matcher.withMatcher(Contrat_.NOM, GenericPropertyMatcher::startsWith);
        contratList = contratRepository.findAll(Example.of(contratExample, matcher));
        assertTrue(isNotEmpty(contratList));
        assertEquals(3, contratList.size());
    }

    private void assertNoGraph(Contrat contrat) {
        assertSocieteNotLoaded(contrat.getSociete());
        assertContratVersionNotLoaded(contrat.getContratVersionSet());
        assertContratStatutNotLoaded(contrat.getContratStatutSet());
    }

    private void assertNoGraph(List<Contrat> contratList) {
        assertTrue(isNotEmpty(contratList));
        contratList.forEach(this::assertNoGraph);
    }

    private void assertGraph(List<Contrat> contratList) {
        contratList.forEach(contrat -> {
                    assertSocieteLoaded(contrat.getSociete());
                    assertPersonneLoaded(contrat.getSociete().getAvocat());
                    assertAdresseMailLoaded(contrat.getSociete().getAvocat().getAdresseMailSet());
                    assertPersonneLoaded(contrat.getSociete().getPresident());
                    assertAdresseMailNotLoaded(contrat.getSociete().getPresident().getAdresseMailSet());
                    assertContratVersionLoaded(contrat.getContratVersionSet());
                    assertContratStatutNotLoaded(contrat.getContratStatutSet());
                }
        );
    }

    private void assertSocieteNotLoaded(Societe societe) {
        assertThrows(LazyInitializationException.class, () -> societe.setId(1L), "graph societe is loaded");
    }

    private void assertSocieteLoaded(Societe societe) {
        try {
            societe.setId(1L);
        } catch (LazyInitializationException e) {
            fail("graph societe not loaded");
        }
    }

    private void assertPersonneLoaded(Personne personne) {
        try {
            personne.setId(1L);
        } catch (LazyInitializationException e) {
            fail("graph personne not loaded");
        }
    }

    private void assertContratVersionNotLoaded(Set<ContratVersion> contratVersionSet) {
        assertThrows(LazyInitializationException.class, contratVersionSet::iterator, "graph contratVersion " +
                "is loaded");
    }

    private void assertContratVersionLoaded(Set<ContratVersion> contratVersionSet) {
        try {
            contratVersionSet.forEach(contratVersion -> contratVersion.setId(1L));
        } catch (LazyInitializationException e) {
            fail("graph contratVersion not loaded");
        }
    }

    private void assertContratStatutNotLoaded(Set<ContratStatut> contratStatuts) {
        assertThrows(LazyInitializationException.class, contratStatuts::iterator, "graph contratStatut is " +
                "loaded");
    }

    private void assertAdresseMailNotLoaded(Set<AdresseMail> adresseMailSet) {
        assertThrows(LazyInitializationException.class, adresseMailSet::iterator, "graph adresseMail is " +
                "loaded");
    }

    private void assertAdresseMailLoaded(Set<AdresseMail> adresseMailSet) {
        try {
            adresseMailSet.forEach(adresseMail -> adresseMail.setId(1L));
        } catch (LazyInitializationException e) {
            fail("graph adresseMail not loaded");
        }
    }

    private void callRepositoryAndAssert(ContratCriteria contratCriteria) {
        Specification<Contrat> contratSpecification = null;
        if (isNotEmpty(contratCriteria.getContratIdList())) {
            contratSpecification = initOrAndSpecification(null,
                    ContratSpecification.idIn(contratCriteria.getContratIdList()));
        }
        if (contratCriteria.getContratVersionActif() != null) {
            contratSpecification = initOrAndSpecification(contratSpecification,
                    ContratSpecification.contratVersionActifEqual(
                            contratCriteria.getContratVersionActif()));
        }
        if (isNotBlank(contratCriteria.getSuffixAvocatMail())) {
            contratSpecification = initOrAndSpecification(contratSpecification,
                    ContratSpecification.adresseMailLibelleEndWithIgnoreCase(Personne_.AVOCAT,
                            contratCriteria.getSuffixAvocatMail()));
        }
        List<Contrat> contratList = contratRepository.findAllWithGraphAndSpecification(GRAPH,
                contratSpecification);
        assertTrue(isNotEmpty(contratList));
        assertGraph(contratList);
    }
}
