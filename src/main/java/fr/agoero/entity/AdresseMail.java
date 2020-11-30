package fr.agoero.entity;

import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "adresse_mail")
@Getter
@Setter
@ToString(exclude = {"personne"})
public class AdresseMail {

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;

    // FK FIELD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_personne_id", nullable = false)
    private Personne personne;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdresseMail that = (AdresseMail) o;
        return id == that.id &&
                Objects.equals(libelle, that.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle);
    }

}
