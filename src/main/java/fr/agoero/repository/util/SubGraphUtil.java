package fr.agoero.repository.util;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe utilitaire pour creer les graphes
 */
@Getter
@Setter
public final class SubGraphUtil {

    private String name;
    private List<SubGraphUtil> subGraphUtilList;

    public SubGraphUtil(String name) {
        this.name = name;
    }
}
