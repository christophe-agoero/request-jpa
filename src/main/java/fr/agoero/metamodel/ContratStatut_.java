package fr.agoero.metamodel;

import fr.agoero.entity.Contrat;
import fr.agoero.entity.ContratStatut;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContratStatut.class)
public abstract class ContratStatut_ {

    public static final String CONTRAT = "contrat";
    public static final String ACTIF = "actif";
    public static final String ID = "id";
    public static final String STATUT = "statut";
    public static volatile SingularAttribute<ContratStatut, Contrat> contrat;
    public static volatile SingularAttribute<ContratStatut, Boolean> actif;
    public static volatile SingularAttribute<ContratStatut, Long> id;
    public static volatile SingularAttribute<ContratStatut, String> statut;

}

