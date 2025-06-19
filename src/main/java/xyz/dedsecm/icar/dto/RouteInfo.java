package xyz.dedsecm.icar.dto;

import lombok.Value;

/**
 * Informations sur un itinéraire, incluant distance, durée et émissions de CO2.
 */
@Value
public class RouteInfo {
    double distance; // en mètres
    double duration; // en secondes
    double co2;      // en kilogrammes
}
