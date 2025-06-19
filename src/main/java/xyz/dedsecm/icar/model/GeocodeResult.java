package xyz.dedsecm.icar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeocodeResult {
    private String displayName;
    private double latitude;
    private double longitude;
}
