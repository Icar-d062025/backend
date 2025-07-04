package xyz.dedsecm.icar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.dedsecm.icar.dto.ReservationVehiculeDTO;
import xyz.dedsecm.icar.mapper.ReservationVehiculeMapper;
import xyz.dedsecm.icar.model.ReservationVehicule;
import xyz.dedsecm.icar.repository.ReservationVehiculeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vs")
@RequiredArgsConstructor
public class ReservationVehiculeController {
    private final ReservationVehiculeRepository repository;
    private final ReservationVehiculeMapper mapper;

    @GetMapping
    public List<ReservationVehiculeDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationVehiculeDTO> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReservationVehiculeDTO create(@RequestBody ReservationVehiculeDTO dto) {
        ReservationVehicule entity = mapper.toEntity(dto);
        entity.setDateCreation(LocalDate.now());
        return mapper.toDTO(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationVehiculeDTO> update(@PathVariable Integer id, @RequestBody ReservationVehiculeDTO dto) {
        return repository.findById(id).map(existing -> {
            ReservationVehicule entity = mapper.toEntity(dto);
            entity.setId(id);
            entity.setDateCreation(existing.getDateCreation());
            return ResponseEntity.ok(mapper.toDTO(repository.save(entity)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<ReservationVehiculeDTO> getByUtilisateur(@PathVariable Integer utilisateurId) {
        return repository.findByUtilisateurId(utilisateurId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/vehicule/{vehiculeId}")
    public List<ReservationVehiculeDTO> getByVehicule(@PathVariable Integer vehiculeId) {
        return repository.findByVehiculeId(vehiculeId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/active")
    public List<ReservationVehiculeDTO> getActive() {
        return repository.findActiveReservations(LocalDate.now()).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/futures")
    public List<ReservationVehiculeDTO> getFutures() {
        return repository.findByDateDebutAfter(LocalDate.now()).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/passees")
    public List<ReservationVehiculeDTO> getPassees() {
        return repository.findByDateFinBefore(LocalDate.now()).stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}

