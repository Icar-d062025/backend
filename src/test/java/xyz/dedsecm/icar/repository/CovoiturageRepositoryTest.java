package xyz.dedsecm.icar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CovoiturageRepositoryTest {
    @Autowired
    private CovoiturageRepository covoiturageRepository;

    @Test
    void contextLoads() {
        assertThat(covoiturageRepository).isNotNull();
    }
}

