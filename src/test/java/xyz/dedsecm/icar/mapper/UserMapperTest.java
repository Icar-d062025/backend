package xyz.dedsecm.icar.mapper;

import org.junit.jupiter.api.Test;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.model.Role;
import xyz.dedsecm.icar.model.User;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour UserMapper.
 * <p>
 * Vérifie la conversion entre l'entité User et le DTO UserDTO.
 * </p>
 */
class UserMapperTest {

    /**
     * Teste la conversion d'une entité User en DTO UserDTO.
     */
    @Test
    void testToDTO() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@mail.com");
        user.setNom("Dupont");
        user.setPrenom("Jean");
        user.setUsername("jdupont");
        user.setPassword("secret");
        user.setAdresse("1 rue de Paris");
        user.setRole(Role.USER);
        user.setBanni(false);
        user.setRaisonBanni(null);
        user.setDureeBanni(LocalTime.of(0,0));
        user.setVehiculePerso(true);
        user.setVehiculeId(10L);

        UserDTO dto = UserMapper.toDTO(user);
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getNom(), dto.getNom());
        assertEquals(user.getPrenom(), dto.getPrenom());
        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getAdresse(), dto.getAdresse());
        assertEquals(user.getRole(), dto.getRole());
        assertEquals(user.getBanni(), dto.getBanni());
        assertEquals(user.getRaisonBanni(), dto.getRaisonBanni());
        assertEquals(user.getDureeBanni(), dto.getDureeBanni());
        assertEquals(user.getVehiculePerso(), dto.getVehiculePerso());
        assertEquals(user.getVehiculeId(), dto.getVehiculeId());
    }

    /**
     * Teste la conversion d'un DTO UserDTO en entité User.
     */
    @Test
    void testToEntity() {
        UserDTO dto = new UserDTO(2L, "user2@mail.com", "Martin", "Paul", "pmartin", "pass2", "2 rue de Lyon", Role.ADMIN, true, "fraude", LocalTime.of(1,30), false, null);
        User user = UserMapper.toEntity(dto);
        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getNom(), user.getNom());
        assertEquals(dto.getPrenom(), user.getPrenom());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getAdresse(), user.getAdresse());
        assertEquals(dto.getRole(), user.getRole());
        assertEquals(dto.getBanni(), user.getBanni());
        assertEquals(dto.getRaisonBanni(), user.getRaisonBanni());
        assertEquals(dto.getDureeBanni(), user.getDureeBanni());
        assertEquals(dto.getVehiculePerso(), user.getVehiculePerso());
        assertEquals(dto.getVehiculeId(), user.getVehiculeId());
    }
}

