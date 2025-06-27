package xyz.dedsecm.icar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.mapper.CovoiturageMapper;
import xyz.dedsecm.icar.model.Covoiturage;
import xyz.dedsecm.icar.repository.CovoiturageRepository;
import xyz.dedsecm.icar.exception.CovoiturageException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CovoiturageService {

    @Autowired
    private CovoiturageRepository covoiturageRepository;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");

    public String formatDateHeureDepart(Covoiturage covoiturage) {
        return covoiturage.getDateHeureDepart().format(FORMATTER);
    }

    public String formatDateHeureArrivee(Covoiturage covoiturage) {
        return covoiturage.getDateHeureArrivee().format(FORMATTER);
    }

    /***
     * Permet de récupérer tous les covoiturages
     * @return
     */

    public List<CovoiturageDTO> getAllCovoiturages() {
        return covoiturageRepository.findAll().stream()
                .map(CovoiturageMapper::toDTO).toList();
    }

    /***
     * Permet d'ajouter un covoiturage
     * @param covoiturageDTO
     * @return
     */

    public CovoiturageDTO createCovoiturage(CovoiturageDTO covoiturageDTO) {
        validateCovoiturageDTO(covoiturageDTO);

        Covoiturage covoiturage = CovoiturageMapper.toEntity(covoiturageDTO);
        Covoiturage savedCovoiturage = covoiturageRepository.save(covoiturage);

        return CovoiturageMapper.toDTO(savedCovoiturage);
    }



    /**
     * Valide les données d'un objet CovoiturageDTO avant sa création ou sa mise à jour.
     * <p>
     * Vérifie notamment :
     * <ul>
     *   <li>Que la date d'arrivée est postérieure à la date de départ</li>
     *   <li>Que les adresses de départ et d'arrivée sont renseignées et différentes</li>
     *   <li>Que le nombre de places est positif et cohérent avec le nombre de places restantes</li>
     *   <li>Que la distance est positive</li>
     *   <li>Que le statut est renseigné</li>
     * </ul>
     * Lance une IllegalArgumentException en cas de règle non respectée.
     * @param dto le DTO à valider
     */
    void validateCovoiturageDTO(CovoiturageDTO dto) {

        if (dto.getDateHeureDepart() == null || dto.getDateHeureArrivee() == null) {
            throw new CovoiturageException("Les dates de départ et d'arrivée sont obligatoires");
        }

        if (dto.getDateHeureDepart().isBefore(LocalDateTime.now())) {
            throw new CovoiturageException("La date de départ ne peut pas être dans le passé");
        }

        if (dto.getDateHeureArrivee().isBefore(dto.getDateHeureDepart())) {
            throw new CovoiturageException("La date d'arrivée doit être après la date de départ");
        }

        if (dto.getAdresseDepart() == null || dto.getAdresseArrivee() == null) {
            throw new CovoiturageException("Les adresses de départ et d'arrivée sont obligatoires");
        }

        if (dto.getAdresseDepart().trim().equalsIgnoreCase(dto.getAdresseArrivee().trim())) {
            throw new CovoiturageException("L'adresse de départ doit être différente de l'adresse d'arrivée et inversement");
        }

        if (dto.getNbPlaces() == null || dto.getNbPlaces() <= 0) {
            throw new CovoiturageException("Le nombre de places doit être supérieur à 0");
        }

        if (dto.getNbRestant() > dto.getNbPlaces()) {
            throw new CovoiturageException("Le nombre de places restantes doit être inférieur ou égal au nombre de places du véhicule");
        }

        if (dto.getNbRestant() < 0) {
            throw new CovoiturageException("Le nombre de places restantes ne peut pas être négatif");
        }

        if (dto.getDistance() == null || dto.getDistance() <= 0) {
            throw new CovoiturageException("La distance doit être supérieure à 0");
        }

        if (dto.getStatut() == null) {
            throw new CovoiturageException("Le statut doit être renseigné");
        }
    }
}
