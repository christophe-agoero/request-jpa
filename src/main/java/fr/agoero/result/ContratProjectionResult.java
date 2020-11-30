package fr.agoero.result;

import lombok.Value;

@Value
public class ContratProjectionResult {

    // contrat
    long contratId;
    String contratNom;
    // contratVersion
    long contratVersionId;
    int contratVersionNumero;
    // societe
    long societeId;
    String societeNom;
    // personne
    String avocatNom;
    String presidentNom;
    // adresseMail
    String avocatMail;
}
