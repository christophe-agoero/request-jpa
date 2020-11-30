package fr.agoero.result;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(
        name = "ContratProjectionMapping",
        classes = @ConstructorResult(
                targetClass = ContratProjectionResult.class,
                columns = {
                        @ColumnResult(name = "contratId", type = Long.class),
                        @ColumnResult(name = "contratNom", type = String.class),
                        @ColumnResult(name = "contratVersionId", type = Long.class),
                        @ColumnResult(name = "contratVersionNumero", type = Integer.class),
                        @ColumnResult(name = "societeId", type = Long.class),
                        @ColumnResult(name = "societeNom", type = String.class),
                        @ColumnResult(name = "avocatNom", type = String.class),
                        @ColumnResult(name = "presidentNom", type = String.class),
                        @ColumnResult(name = "avocatMail", type = String.class)
                }
        )
)
public class ContratProjectionMapping {

}
