package fr.agoero.metamodel;

import fr.agoero.entity.AdresseMail;
import fr.agoero.entity.Personne;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Personne.class)
public abstract class Personne_ {

    public static final String AVOCAT = "avocat";
    public static final String ID = "id";
    public static final String ADRESSE_MAIL_SET = "adresseMailSet";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String PRESIDENT = "president";
    public static volatile SingularAttribute<Personne, Boolean> avocat;
    public static volatile SingularAttribute<Personne, Long> id;
    public static volatile SetAttribute<Personne, AdresseMail> adresseMailSet;
    public static volatile SingularAttribute<Personne, String> nom;
    public static volatile SingularAttribute<Personne, String> prenom;
    public static volatile SingularAttribute<Personne, Boolean> president;

}

