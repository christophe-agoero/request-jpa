package fr.agoero.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Classe de test du service ContratService
 */
@SpringBootTest
@DisplayName("Test contrat ContratService")
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ContratServiceTest {

    @Autowired
    ContratService contratService;

    @Test
    @DisplayName("showNPlusOneProblem")
    void showNPlusOneProblem() {
        contratService.showNPlusOneProblem();
        // Pas de plantage juste genere plusieurs requetes
        assertNull(null);
    }

    @Test
    @DisplayName("showLazyInitializationException")
    void showLazyInitializationException() {
        assertThrows(LazyInitializationException.class,
                () -> contratService.showLazyInitializationException(),
                "graph is loaded");
    }

}
