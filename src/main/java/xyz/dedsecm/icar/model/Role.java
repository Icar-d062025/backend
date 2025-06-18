package xyz.dedsecm.icar.model;

/**
 * Enumération des rôles possibles pour un utilisateur dans l'application.
 * <p>
 * Permet de distinguer les droits et accès selon le rôle :
 * <ul>
 *   <li>{@link #ADMIN} : Administrateur, accès complet à toutes les fonctionnalités.</li>
 *   <li>{@link #USER} : Utilisateur standard, accès limité aux fonctionnalités de base.</li>
 * </ul>
 * </p>
 */
public enum Role {
    /** Rôle administrateur, accès complet à l'application. */
    ADMIN,
    /** Rôle utilisateur standard, accès limité. */
    USER
}