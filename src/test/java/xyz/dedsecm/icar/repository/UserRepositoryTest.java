package xyz.dedsecm.icar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import xyz.dedsecm.icar.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("findByEmail doit retourner l'utilisateur correspondant")
    void testFindByEmail() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setUsername("testuser");
        user.setPassword("testpass");
        userRepository.save(user);
        Optional<User> found = userRepository.findByEmail("test@email.com");
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("test@email.com");
    }

    @Test
    @DisplayName("findByUsername doit retourner l'utilisateur correspondant")
    void testFindByUsername() {
        User user = new User();
        user.setEmail("user2@email.com");
        user.setUsername("user2");
        user.setPassword("testpass");
        userRepository.save(user);
        Optional<User> found = userRepository.findByUsername("user2");
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("user2");
    }

    @Test
    @DisplayName("existsByEmail doit retourner true si l'email existe")
    void testExistsByEmail() {
        User user = new User();
        user.setEmail("exist@email.com");
        user.setUsername("existuser");
        user.setPassword("testpass");
        userRepository.save(user);
        assertThat(userRepository.existsByEmail("exist@email.com")).isTrue();
        assertThat(userRepository.existsByEmail("notfound@email.com")).isFalse();
    }

    @Test
    @DisplayName("existsByUsername doit retourner true si le username existe")
    void testExistsByUsername() {
        User user = new User();
        user.setEmail("exist2@email.com");
        user.setUsername("existuser2");
        user.setPassword("testpass");
        userRepository.save(user);
        assertThat(userRepository.existsByUsername("existuser2")).isTrue();
        assertThat(userRepository.existsByUsername("notfounduser")).isFalse();
    }
}
