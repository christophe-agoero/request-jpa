package fr.agoero.metamodel;

import fr.agoero.entity.Contrat;
import fr.agoero.entity.ContratStatut;
import fr.agoero.entity.ContratVersion;
import fr.agoero.entity.Societe;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contrat.class)
public abstract class Contrat_ {

    public static final String CONTRAT_STATUT_SET = "contratStatutSet";
    public static final String CONTRAT_VERSION_SET = "contratVersionSet";
    public static final String ID = "id";
    public static final String NOM = "nom";
    public static final String SOCIETE = "societe";
    public static volatile SetAttribute<Contrat, ContratStatut> contratStatutSet;
    public static volatile SetAttribute<Contrat, ContratVersion> contratVersionSet;
    public static volatile SingularAttribute<Contrat, Long> id;
    public static volatile SingularAttribute<Contrat, String> nom;
    public static volatile SingularAttribute<Contrat, Societe> societe;

}

