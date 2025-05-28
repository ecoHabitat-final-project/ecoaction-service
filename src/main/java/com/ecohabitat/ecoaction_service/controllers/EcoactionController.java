package com.ecohabitat.ecoaction_service.controllers;

import com.ecohabitat.ecoaction_service.clients.HabitatFeignClient;
import com.ecohabitat.ecoaction_service.clients.UserFeignClient;
import com.ecohabitat.ecoaction_service.dto.EcoactionResponseDTO;
import com.ecohabitat.ecoaction_service.dto.HabitatDTO;
import com.ecohabitat.ecoaction_service.dto.HabitatResponseDTO;
import com.ecohabitat.ecoaction_service.dto.UserResponseDTO;
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

    @Autowired
    private HabitatFeignClient habitatFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    // get Full Ecoactions
    @GetMapping("")
    public ResponseEntity<?> getAllFullEcoactions() {
        List<EcoactionResponseDTO> ecoactions = ecoactionService.getFullEcoactions();
        return new ResponseEntity<>(ecoactions, HttpStatus.OK);
    }


    //Obtener ecoacción por ecoaccion ID
    @GetMapping("/{ecoactionId}")
    public ResponseEntity<?> getFullEcoactionInfo(@PathVariable long ecoactionId) {

        EcoactionResponseDTO response = ecoactionService.getFullEcoactionById(ecoactionId);
        return ResponseEntity.ok(response);
    }

   //obtener ecoacción por habitat ID
    @GetMapping("/habitat/{habitatId}")
    ResponseEntity <?> getEcoByHabitat(@PathVariable("habitatId") long habitatId){
        EcoactionResponseDTO response = ecoactionService.getFullEcoactionByHabitatId(habitatId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Obtener Ecoacción por id de usuario
    @GetMapping("/user/{userId}")
    ResponseEntity <?> getEcoByUser(@PathVariable("userId") long userId) {
        EcoactionResponseDTO response = ecoactionService.getFullEcoactionByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<?> createEcoaction(@RequestBody Ecoaction ecoaction) {
        createEcoaction(ecoaction);
        return new ResponseEntity<>(ecoaction, HttpStatus.OK);
    }
    // por si la reforma ecológica hubiera sido en otro habitat.
    @PatchMapping("/update/{ecoactionId}")
    ResponseEntity<?> updateEcoaction(@PathVariable ("ecoactionId") Long ecoactionId,
                                           @RequestBody HabitatDTO habitatDTO) {
        Ecoaction updateEcoaction = ecoactionService.updateEcoaction(ecoactionId, habitatDTO);
        //updateEcoaction.setHabitatId(habitatDTO.getHabitatId());
        return new ResponseEntity<>(updateEcoaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{ecoactionId}")
    ResponseEntity<?> deleteEcoaction (@PathVariable ("ecoactionId") Long ecoactionId) {
        ecoactionService.deleteEcoactionById(ecoactionId);
        return new ResponseEntity<>("Deleted Habitat", HttpStatus.OK);
    }

    //2024-05-_28
    // Obtener ecoacción por usuario ID


}
