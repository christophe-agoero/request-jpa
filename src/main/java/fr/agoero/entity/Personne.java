package fr.agoero.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "personne")
@Getter
@Setter
@ToString(exclude = {"adresseMailSet"})
public class Personne {

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Basic
    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;
    @Basic
    @Column(name = "avocat", nullable = false)
    private boolean avocat;
    @Basic
    @Column(name = "president", nullable = false)
    private boolean president;

    // JPA FIELD
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personne")
    private Set<AdresseMail> adresseMailSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Personne personne = (Personne) o;
        return id == personne.id &&
                avocat == personne.avocat &&
                president == personne.president &&
                Objects.equals(nom, personne.nom) &&
                Objects.equals(prenom, personne.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, avocat, president);
    }

}
