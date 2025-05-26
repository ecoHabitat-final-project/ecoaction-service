package com.ecohabitat.ecoaction_service.controllers;

import com.ecohabitat.ecoaction_service.dto.HabitatDTO;
import com.ecohabitat.ecoaction_service.models.Ecoaction;
import com.ecohabitat.ecoaction_service.services.EcoactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ecoaction") //todo hacer los tests no los he hecho en service

public class EcoactionController {
    @Autowired
    private EcoactionService ecoactionService;


    @GetMapping("")
    public ResponseEntity<?> getEcoactions() {
        List<Ecoaction> ecoactions = ecoactionService.getEcoactions();
        return new ResponseEntity<>(ecoactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getEcoactionById(@PathVariable("id") long id) {
        Ecoaction ecoactionFound = ecoactionService.getEcoactionById(id);
        return new ResponseEntity<>(ecoactionFound, HttpStatus.OK);
    }

    @GetMapping("/habitat/{habitatId}")
    ResponseEntity<?> getEcoactionByHabitat(@PathVariable("habitatId") long habitatId) {
        Ecoaction ecoactionFound = ecoactionService.getEcoactionByHabitatId(habitatId);
        return new ResponseEntity<>(ecoactionFound, HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<?> createEcoaction(@RequestBody Ecoaction ecoaction) {
        createEcoaction(ecoaction);
        return new ResponseEntity<>(ecoaction, HttpStatus.OK);
    }

    @PatchMapping("/update/{ecoactionId}")
    ResponseEntity<?> updateEcoaction(@PathVariable ("ecoactionId") Long ecoactionId,
                                           @RequestBody HabitatDTO habitatDTO) {
        Ecoaction updateEcoaction = ecoactionService.updateEcoaction(ecoactionId, habitatDTO);
        updateEcoaction.setHabitatId(habitatDTO.getHabitatId());
        return new ResponseEntity<>(updateEcoaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{ecoactionId}")
    ResponseEntity<?> deleteEcoaction (@PathVariable ("ecoactionId") Long ecoactionId) {
        ecoactionService.deleteEcoactionById(ecoactionId);
        return new ResponseEntity<>("Deleted Habitat", HttpStatus.OK);
    }

}
