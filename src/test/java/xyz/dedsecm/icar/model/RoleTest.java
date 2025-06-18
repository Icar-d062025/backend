package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour l'énumération Role.
 */
class RoleTest {

    @Test
    void testValues() {
        Role[] roles = Role.values();
        assertEquals(2, roles.length, "Il doit y avoir 2 rôles");
        assertArrayEquals(new Role[]{Role.ADMIN, Role.USER}, roles);
    }

    @Test
    void testValueOfAdmin() {
        assertEquals(Role.ADMIN, Role.valueOf("ADMIN"));
    }

    @Test
    void testValueOfUser() {
        assertEquals(Role.USER, Role.valueOf("USER"));
    }

    @Test
    void testToString() {
        assertEquals("ADMIN", Role.ADMIN.toString());
        assertEquals("USER", Role.USER.toString());
    }

    @Test
    void testValueOfInvalidRole() {
        assertThrows(IllegalArgumentException.class, () -> Role.valueOf("SUPERADMIN"));
    }
}
