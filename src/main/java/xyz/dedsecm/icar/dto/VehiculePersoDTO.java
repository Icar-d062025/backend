package xyz.dedsecm.icar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculePersoDTO {
    private Long userId;
    private String userNom;
    private String userPrenom;
    private Integer vehiculeId;
    private String immatricule;
    private String marque;
    private String modele;
    private String categorie;
    private Integer motorisation;
    private String photoUrl;
    private Integer dateCreation;
}

