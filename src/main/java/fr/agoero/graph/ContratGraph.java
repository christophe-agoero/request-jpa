package fr.agoero.graph;


import fr.agoero.metamodel.Contrat_;
import fr.agoero.repository.util.SubGraphUtil;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Classe de graphes pour Contrat
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContratGraph {

    /**
     * Permet de construire le sousgraphe ContratVersion
     *
     * @return le sousgraphe ContratVersion
     */
    public static SubGraphUtil getContratVersionGraph() {
        return new SubGraphUtil(Contrat_.CONTRAT_VERSION_SET);
    }

    /**
     * Permet de construire le sousgraphe Societe
     *
     * @return le sousgraphe Societe
     */
    public static SubGraphUtil getSocieteGraph() {
        return new SubGraphUtil(Contrat_.SOCIETE);
    }

    /**
     * Permet de construire le sousgraphe Societe President Avocat AdresseMail
     *
     * @return le sousgraphe Avocat
     */
    public static SubGraphUtil getSocPdtAvoMailGraph() {
        SubGraphUtil subGraphSocPdtAvoMail = getSocieteGraph();
        subGraphSocPdtAvoMail.setSubGraphUtilList(Arrays.asList(SocieteGraph.getPresidentGraph(),
                SocieteGraph.getAvocatMailGraph()));
        return subGraphSocPdtAvoMail;
    }

}
