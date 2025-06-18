package xyz.dedsecm.icar.mapper;

import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.model.Covoiturage;

/**
 * Mapper utilitaire pour convertir entre les entités Covoiturage et les DTO CovoiturageDTO.
 * <p>
 * Fournit des méthodes statiques pour transformer un objet métier (entité) en DTO et inversement.
 * Permet de séparer la logique de conversion du reste de l'application.
 * </p>
 */
public class CovoiturageMapper {

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe utilitaire.
     */
    public CovoiturageMapper() {
    }

    /**
     * Convertit une entité Covoiturage en DTO CovoiturageDTO.
     * @param covoiturage l'entité à convertir
     * @return le DTO correspondant
     */
    public static CovoiturageDTO toDTO(Covoiturage covoiturage) {
        return new CovoiturageDTO(
                covoiturage.getDateHeureDepart(),
                covoiturage.getDateHeureArrivee(),
                covoiturage.getAdresseDepart(),
                covoiturage.getAdresseArrivee(),
                covoiturage.getNbPlaces(),
                covoiturage.getNbRestant(),
                covoiturage.getDistance(),
                covoiturage.getStatut()
        );
    }

    /**
     * Convertit un DTO CovoiturageDTO en entité Covoiturage.
     * @param covoiturageDTO le DTO à convertir
     * @return l'entité correspondante
     */
    public static Covoiturage toEntity(CovoiturageDTO covoiturageDTO) {
        return new Covoiturage(
                covoiturageDTO.getDateHeureDepart(),
                covoiturageDTO.getDateHeureArrivee(),
                covoiturageDTO.getAdresseDepart(),
                covoiturageDTO.getAdresseArrivee(),
                covoiturageDTO.getNbPlaces(),
                covoiturageDTO.getNbRestant(),
                covoiturageDTO.getDistance(),
                covoiturageDTO.getStatut()
        );
    }

}
