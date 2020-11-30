package fr.agoero.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@ToString(exclude = {"societe", "contratStatutSet", "contratVersionSet"})
public class Contrat {

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    // FK FIELD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_societe_id", nullable = false)
    private Societe societe;

    // JPA FIELD
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
    private Set<ContratStatut> contratStatutSet;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
    private Set<ContratVersion> contratVersionSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contrat contrat = (Contrat) o;
        return id == contrat.id &&
                Objects.equals(nom, contrat.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

}
