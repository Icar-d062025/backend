package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour l'entité Vehicule.
 * <p>
 * Vérifie la création, l'accès aux attributs, l'égalité et la méthode toString de la classe Vehicule.
 * </p>
 */
class VehiculeTest {

    /**
     * Teste la création d'un véhicule et l'accès à ses attributs principaux.
     */
    @Test
    void testCreationEtAccesseurs() {
        Vehicule vehicule = new Vehicule(1, "AB-123-CD", "Renault", "Clio", "Citadine");
        vehicule.setMotorisation(1);
        vehicule.setPhotoUrl("http://photo.com/vehicule.jpg");
        vehicule.setDateCreation(2020);
        vehicule.setRevenueAnnuelDePrevision(5000);
        assertEquals(1, vehicule.getId());
        assertEquals("AB-123-CD", vehicule.getImmatricule());
        assertEquals("Renault", vehicule.getMarque());
        assertEquals("Clio", vehicule.getModele());
        assertEquals("Citadine", vehicule.getCategorie());
        assertEquals(1, vehicule.getMotorisation());
        assertEquals("http://photo.com/vehicule.jpg", vehicule.getPhotoUrl());
        assertEquals(2020, vehicule.getDateCreation());
        assertEquals(5000, vehicule.getRevenueAnnuelDePrevision());
    }

    /**
     * Teste la méthode toString pour s'assurer qu'elle ne retourne pas null et contient des informations clés.
     */
    @Test
    void testToString() {
        Vehicule vehicule = new Vehicule(2, "CD-456-EF", "Peugeot", "208", "Citadine");
        String str = vehicule.toString();
        assertNotNull(str);
        assertTrue(str.contains("Peugeot"));
        assertTrue(str.contains("208"));
    }

    /**
     * Teste la méthode equals pour vérifier l'égalité basée sur l'identifiant.
     */
    @Test
    void testEquals() {
        Vehicule v1 = new Vehicule(3, "EF-789-GH", "Citroën", "C3", "Citadine");
        Vehicule v2 = new Vehicule(3, "EF-789-GH", "Citroën", "C3", "Citadine");
        Vehicule v3 = new Vehicule(4, "GH-012-IJ", "Toyota", "Yaris", "Citadine");
        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }

    /**
     * Teste la méthode hashCode pour cohérence avec equals.
     */
    @Test
    void testHashCode() {
        Vehicule v1 = new Vehicule(5, "IJ-345-KL", "Ford", "Fiesta", "Citadine");
        Vehicule v2 = new Vehicule(5, "IJ-345-KL", "Ford", "Fiesta", "Citadine");
        assertEquals(v1.hashCode(), v2.hashCode());
    }
}

