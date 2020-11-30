package fr.agoero.metamodel;

import fr.agoero.entity.Contrat;
import fr.agoero.entity.Personne;
import fr.agoero.entity.Societe;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Societe.class)
public abstract class Societe_ {

    public static final String CONTRAT_SET = "contratSet";
    public static final String NUMERO = "numero";
    public static final String AVOCAT = "avocat";
    public static final String ID = "id";
    public static final String NOM = "nom";
    public static final String PRESIDENT = "president";
    public static volatile SetAttribute<Societe, Contrat> contratSet;
    public static volatile SingularAttribute<Societe, String> numero;
    public static volatile SingularAttribute<Societe, Personne> avocat;
    public static volatile SingularAttribute<Societe, Long> id;
    public static volatile SingularAttribute<Societe, String> nom;
    public static volatile SingularAttribute<Societe, Personne> president;

}

