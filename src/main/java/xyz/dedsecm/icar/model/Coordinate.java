package xyz.dedsecm.icar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Représente une paire de coordonnées géographiques.
 */
@Getter
@AllArgsConstructor
public class Coordinate {

    private final double latitude;
    private final double longitude;
}
