package fr.agoero.repository;

import fr.agoero.entity.Contrat;
import fr.agoero.repository.custom.ContratCustomRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface pour les methodes non custom du repository Contrat
 */
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long>, JpaSpecificationExecutor<Contrat>,
        ContratCustomRepository {

    List<Contrat> findBySocieteId(long societeId);

    <T> List<T> findBySocieteId(long societeId, Class<T> type);

    List<Contrat> findBySocieteNomStartsWith(String prefixSociete);
}
