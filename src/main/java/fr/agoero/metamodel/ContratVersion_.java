package fr.agoero.metamodel;

import fr.agoero.entity.Contrat;
import fr.agoero.entity.ContratVersion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContratVersion.class)
public abstract class ContratVersion_ {

    public static final String CONTRAT = "contrat";
    public static final String ACTIF = "actif";
    public static final String ID = "id";
    public static final String NUMERO_VERSION = "numeroVersion";
    public static final String NOM = "nom";
    public static volatile SingularAttribute<ContratVersion, Contrat> contrat;
    public static volatile SingularAttribute<ContratVersion, Boolean> actif;
    public static volatile SingularAttribute<ContratVersion, Long> id;
    public static volatile SingularAttribute<ContratVersion, Integer> numeroVersion;
    public static volatile SingularAttribute<ContratVersion, String> nom;

}

