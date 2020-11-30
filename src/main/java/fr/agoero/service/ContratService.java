package fr.agoero.service;

import fr.agoero.entity.Contrat;
import fr.agoero.repository.ContratRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de service du Contrat
 */
@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("squid:S4144")
public class ContratService {

    private final ContratRepository contratRepository;

    @Transactional(readOnly = true)
    public void showNPlusOneProblem() {
        List<Contrat> contratList = contratRepository.findAll();
        contratList.forEach(this::printAdressMail);
    }

    public void showLazyInitializationException() {
        List<Contrat> contratList = contratRepository.findAll();
        contratList.forEach(this::printAdressMail);
    }

    private void printAdressMail(Contrat contrat) {
        contrat.getSociete().getAvocat().getAdresseMailSet().forEach(
            adresseMail -> log.info(adresseMail.toString())
        );
    }
}
