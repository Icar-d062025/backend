package xyz.dedsecm.icar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.service.CovoiturageService;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des covoiturages.
 * Ce contrôleur traite les requêtes HTTP liées aux opérations de covoiturage,
 * comme la consultation et la création des offres de covoiturage.
 */
@RestController
@RequestMapping("/api/covoiturages")
public class CovoiturageController {

    /**
     * Service de gestion des covoiturages.
     */
    @Autowired
    private CovoiturageService covoiturageService;

    /**
     * Récupère la liste de tous les covoiturages disponibles.
     *
     * @return une liste de DTOs représentant les covoiturages
     */
    @GetMapping
    public List<CovoiturageDTO> getCovoiturages() {
        return covoiturageService.getAllCovoiturages();
    }

    /**
     * Crée une nouvelle offre de covoiturage.
     *
     * @param covoiturageDTO le DTO contenant les informations du covoiturage à créer
     * @return le DTO du covoiturage créé avec son identifiant
     */
    @PostMapping
    public CovoiturageDTO createCovoiturage(@RequestBody CovoiturageDTO covoiturageDTO) {
        return covoiturageService.createCovoiturage(covoiturageDTO);
    }
}