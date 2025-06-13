package xyz.dedsecm.vroomvroomcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.dedsecm.vroomvroomcar.dto.UserDTO;
import xyz.dedsecm.vroomvroomcar.mapper.UserMapper;
import xyz.dedsecm.vroomvroomcar.model.User;
import xyz.dedsecm.vroomvroomcar.repository.UserRepository;

import java.time.LocalTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserDTO createUser(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id: " + id));
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email: " + email));
    }

    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec le username: " + username));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDTO.getEmail());
                    user.setNom(userDTO.getNom());
                    user.setPrenom(userDTO.getPrenom());
                    user.setUsername(userDTO.getUsername());
                    user.setAdresse(userDTO.getAdresse());
                    user.setRole(userDTO.getRole());
                    user.setBanni(userDTO.getBanni());
                    user.setRaisonBanni(userDTO.getRaisonBanni());
                    user.setDureeBanni(userDTO.getDureeBanni());
                    user.setVehiculePerso(userDTO.getVehiculePerso());
                    user.setVehiculeId(userDTO.getVehiculeId());

                    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    }

                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO banUser(Long id, String raison, LocalTime duree) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setBanni(true);
                    user.setRaisonBanni(raison);
                    user.setDureeBanni(duree);
                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id: " + id));
    }

    public UserDTO unbanUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setBanni(false);
                    user.setRaisonBanni(null);
                    user.setDureeBanni(null);
                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id: " + id));
    }

    public UserDTO assignVehicle(Long userId, Long vehicleId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setVehiculePerso(true);
                    user.setVehiculeId(vehicleId);
                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id: " + userId));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}