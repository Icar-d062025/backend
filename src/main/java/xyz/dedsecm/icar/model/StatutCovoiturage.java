package xyz.dedsecm.icar.model;

/**
 * Enumération des statuts possibles pour un covoiturage dans l'application.
 * <p>
 * Permet de suivre l'état d'un trajet de covoiturage :
 * <ul>
 *   <li>{@link #RESERVABLE} : Le covoiturage est disponible à la réservation.</li>
 *   <li>{@link #EN_COURS} : Le covoiturage est en cours de réalisation.</li>
 *   <li>{@link #ANNULE} : Le covoiturage a été annulé.</li>
 * </ul>
 * </p>
 */
public enum StatutCovoiturage {
    /** Le covoiturage est disponible à la réservation. */
    RESERVABLE,
    /** Le covoiturage est en cours de réalisation. */
    EN_COURS,
    /** Le covoiturage a été annulé. */
    ANNULE
}
