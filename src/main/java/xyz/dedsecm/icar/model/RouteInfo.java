package xyz.dedsecm.icar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfo {
    private double distanceKm;
    private int durationMinutes;
    private double durationHours;
    private String geometry;

    public RouteInfo(double distanceKm, int durationMinutes) {
        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
        this.durationHours = durationMinutes / 60.0;
    }
}
