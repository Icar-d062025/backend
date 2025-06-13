package xyz.dedsecm.icar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.dedsecm.icar.dto.CovoiturageDTO;
import xyz.dedsecm.icar.service.CovoiturageService;

import java.util.List;

@RestController
@RequestMapping("/api/covoiturages")
public class CovoiturageController {

    @Autowired
    private CovoiturageService covoiturageService;

    @GetMapping
    public List<CovoiturageDTO> getCovoiturages() {
        return covoiturageService.getAllCovoiturages();
    }

    @PostMapping
    public CovoiturageDTO createCovoiturage(@RequestBody CovoiturageDTO covoiturageDTO) {
        return covoiturageService.createCovoiturage(covoiturageDTO);
    }
}
