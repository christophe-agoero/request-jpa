package fr.agoero.graph;


import fr.agoero.metamodel.Personne_;
import fr.agoero.repository.util.SubGraphUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Classe de graphes pour Personne
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonGraph {

    /**
     * Permet de construire le sousgraphe AdresseEmail
     *
     * @return le sousgraphe AdresseEmail
     */
    public static SubGraphUtil getAdresseEmailGraph() {
        return new SubGraphUtil(Personne_.ADRESSE_MAIL_SET);
    }

}
