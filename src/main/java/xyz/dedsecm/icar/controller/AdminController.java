package xyz.dedsecm.icar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour les fonctionnalités d'administration.
 * Ce contrôleur gère les endpoints réservés aux administrateurs du système.
 * Tous les endpoints de ce contrôleur nécessitent le rôle ADMIN pour y accéder.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    /**
     * Endpoint de test pour vérifier l'accès administrateur.
     *
     * @return une chaîne de caractères confirmant l'accès administrateur
     */
    @GetMapping("/hello")
    public String helloAdmin() {
        return "Hello Admin!";
    }
}