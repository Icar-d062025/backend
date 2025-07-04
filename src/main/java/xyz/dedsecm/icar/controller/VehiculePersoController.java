package xyz.dedsecm.icar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dedsecm.icar.dto.VehiculePersoDTO;
import xyz.dedsecm.icar.model.User;
import xyz.dedsecm.icar.model.Vehicule;
import xyz.dedsecm.icar.repository.UserRepository;
import xyz.dedsecm.icar.repository.VehiculeRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculePersoController {
    private final UserRepository userRepository;
    private final VehiculeRepository vehiculeRepository;

    @GetMapping
    public List<VehiculePersoDTO> getAllVehiculesPerso() {
        List<User> users = userRepository.findAll();
        List<VehiculePersoDTO> result = new ArrayList<>();
        for (User user : users) {
            if (Boolean.TRUE.equals(user.getVehiculePerso()) && user.getVehiculeId() != null) {
                Vehicule vehicule = vehiculeRepository.findById(user.getVehiculeId().intValue()).orElse(null);
                if (vehicule != null) {
                    result.add(new VehiculePersoDTO(
                        user.getId(),
                        user.getNom(),
                        user.getPrenom(),
                        vehicule.getId(),
                        vehicule.getImmatricule(),
                        vehicule.getMarque(),
                        vehicule.getModele(),
                        vehicule.getCategorie(),
                        vehicule.getMotorisation(),
                        vehicule.getPhotoUrl(),
                        vehicule.getDateCreation()
                    ));
                }
            }
        }
        return result;
    }
}

