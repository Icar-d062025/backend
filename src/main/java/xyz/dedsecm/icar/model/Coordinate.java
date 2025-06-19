package xyz.dedsecm.icar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return longitude + "," + latitude;
    }
}