package xyz.dedsecm.icar.model;

import lombok.Data;

@Data
public class NominatimResponse {
    private String display_name;
    private String lat;
    private String lon;
}
