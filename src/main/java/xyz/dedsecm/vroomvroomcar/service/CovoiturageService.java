package xyz.dedsecm.vroomvroomcar.service;

import xyz.dedsecm.vroomvroomcar.model.Covoiturage;

import java.time.format.DateTimeFormatter;

public class CovoiturageService {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");

    public String formatDateHeureDepart(Covoiturage covoiturage) {
        return covoiturage.getDateHeureDepart().format(FORMATTER);
    }

    public String formatDateHeureArrivee(Covoiturage covoiturage) {
        return covoiturage.getDateHeureArrivee().format(FORMATTER);
    }
}
