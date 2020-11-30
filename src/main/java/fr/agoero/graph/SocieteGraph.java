package fr.agoero.graph;


import fr.agoero.metamodel.Societe_;
import fr.agoero.repository.util.SubGraphUtil;
import java.util.Collections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Classe de graphes pour Societe
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SocieteGraph {


    /**
     * Permet de construire le sousgraphe Avocat
     *
     * @return le sousgraphe Avocat
     */
    public static SubGraphUtil getAvocatGraph() {
        return new SubGraphUtil(Societe_.AVOCAT);
    }

    /**
     * Permet de construire le sousgraphe President
     *
     * @return le sousgraphe President
     */
    public static SubGraphUtil getPresidentGraph() {
        return new SubGraphUtil(Societe_.PRESIDENT);
    }

    /**
     * Permet de construire le sousgraphe AvocatMail
     *
     * @return le sousgraphe AvocatMail
     */
    public static SubGraphUtil getAvocatMailGraph() {
        SubGraphUtil subGraphAvocat = SocieteGraph.getAvocatGraph();
        SubGraphUtil subGraphAdresseMail = PersonGraph.getAdresseEmailGraph();
        subGraphAvocat.setSubGraphUtilList(Collections.singletonList(subGraphAdresseMail));
        return subGraphAvocat;
    }

}
