package xyz.dedsecm.icar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.dedsecm.icar.model.StatutCovoiturage;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CovoiturageDTO {
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private String adresseDepart;
    private String adresseArrivee;
    private Integer nbPlaces;
    private Integer nbRestant;
    private Integer distance;
    private StatutCovoiturage statut;
}
