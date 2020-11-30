package fr.agoero.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "societe")
@Getter
@Setter
@ToString(exclude = {"contratSet", "avocat", "president"})
public class Societe {

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Basic
    @Column(name = "numero", nullable = false, length = 5)
    private String numero;

    // FK FIELD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_personne_avocat_id", nullable = false)
    private Personne avocat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_personne_president_id", nullable = false)
    private Personne president;

    // JPA FIELD
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "societe")
    private Set<Contrat> contratSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Societe societe = (Societe) o;
        return id == societe.id &&
                Objects.equals(nom, societe.nom) &&
                Objects.equals(numero, societe.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, numero);
    }

}
