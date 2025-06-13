package xyz.dedsecm.icar.mapper;

import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.model.Covoiturage;

public class CovoiturageMapper {

    // Constructeur privé pour empêcher l'instanciation
    public CovoiturageMapper() {
    }

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
