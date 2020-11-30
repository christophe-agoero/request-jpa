package fr.agoero.metamodel;

import fr.agoero.entity.AdresseMail;
import fr.agoero.entity.Personne;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AdresseMail.class)
public abstract class AdresseMail_ {

    public static final String PERSONNE = "personne";
    public static final String LIBELLE = "libelle";
    public static final String ID = "id";
    public static volatile SingularAttribute<AdresseMail, Personne> personne;
    public static volatile SingularAttribute<AdresseMail, String> libelle;
    public static volatile SingularAttribute<AdresseMail, Long> id;

}

