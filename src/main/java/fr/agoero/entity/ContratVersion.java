package fr.agoero.entity;

import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contrat_version")
@Getter
@Setter
@ToString(exclude = {"contrat"})
public class ContratVersion {

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Basic
    @Column(name = "numero_version", nullable = false)
    private int numeroVersion;
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
        ContratVersion that = (ContratVersion) o;
        return id == that.id &&
                numeroVersion == that.numeroVersion &&
                actif == that.actif &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, numeroVersion, actif);
    }

}
