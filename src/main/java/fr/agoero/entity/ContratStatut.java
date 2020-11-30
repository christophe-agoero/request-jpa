package fr.agoero.entity;

import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contrat_statut")
@Getter
@Setter
@ToString(exclude = {"contrat"})
public class ContratStatut {

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "statut", nullable = false, length = 50)
    private String statut;
    @Basic
    @Column(name = "actif", nullable = false)
    private boolean actif;

    // FK FIELD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_contrat_id", nullable = false)
    private Contrat contrat;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContratStatut that = (ContratStatut) o;
        return id == that.id &&
                actif == that.actif &&
                Objects.equals(statut, that.statut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statut, actif);
    }

}
