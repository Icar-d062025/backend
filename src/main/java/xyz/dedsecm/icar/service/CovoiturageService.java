package xyz.dedsecm.icar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.mapper.CovoiturageMapper;
import xyz.dedsecm.icar.model.Covoiturage;
import xyz.dedsecm.icar.repository.CovoiturageRepository;

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
        Covoiturage covoiturage = CovoiturageMapper.toEntity(covoiturageDTO);

        covoiturage.setDateHeureDepart(covoiturageDTO.getDateHeureDepart());
        covoiturage.setDateHeureArrivee(covoiturageDTO.getDateHeureArrivee());
        covoiturage.setAdresseDepart(covoiturageDTO.getAdresseDepart());
        covoiturage.setAdresseArrivee(covoiturageDTO.getAdresseArrivee());
        covoiturage.setNbPlaces(covoiturageDTO.getNbPlaces());
        covoiturage.setNbRestant(covoiturageDTO.getNbRestant());
        covoiturage.setDistance(covoiturageDTO.getDistance());
        covoiturage.setStatut(covoiturageDTO.getStatut());

        Covoiturage savedCovoiturage = covoiturageRepository.save(covoiturage);

        return CovoiturageMapper.toDTO(savedCovoiturage);
    }
}
