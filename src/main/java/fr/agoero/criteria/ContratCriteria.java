package fr.agoero.criteria;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContratCriteria {

    // contrat
    private List<Long> contratIdList;
    // contratVersion
    private Boolean contratVersionActif;
    // societe
    // adresseMail
    private String suffixAvocatMail;
}
