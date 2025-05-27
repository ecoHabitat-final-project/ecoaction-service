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




    // get all ecoactions
    @GetMapping("")
    public ResponseEntity<?> getEcoactions() {
        List<Ecoaction> ecoactions = ecoactionService.getEcoactions();
        return new ResponseEntity<>(ecoactions, HttpStatus.OK);
    }

    //ecoaction by id
    @GetMapping("/{id}")
    ResponseEntity<?> getEcoactionById(@PathVariable("id") long id) {
        Ecoaction ecoactionFound = ecoactionService.getEcoactionById(id);
        return new ResponseEntity<>(ecoactionFound, HttpStatus.OK);
    }

    @GetMapping("/habitat/{habitatId}")
    ResponseEntity <?> getEcoactionByHabitat(@PathVariable("habitatId") long habitatId){

    HabitatResponseDTO habitatDTO= habitatFeignClient.getHabitatById(habitatId);
//    ResponseEntity<?> getEcoactionByHabitat(@PathVariable("habitatId") long habitatId) {
//        Ecoaction ecoactionFound = ecoactionService.getEcoactionByHabitatId(habitatId);
//        return new ResponseEntity<>(ecoactionFound, HttpStatus.OK);
        return new ResponseEntity<>(habitatDTO, HttpStatus.OK);
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

    @GetMapping("/full/{ecoactionId}")
    public ResponseEntity<?> getFullEcoactionInfo(@PathVariable long ecoactionId) {
        Ecoaction ecoaction = ecoactionService.getEcoactionById(ecoactionId);

        HabitatResponseDTO habitat = habitatFeignClient.getHabitatById(ecoaction.getHabitatId());
        UserResponseDTO user = userFeignClient.getUserById(ecoaction.getUserId());

        EcoactionResponseDTO response = new EcoactionResponseDTO();

        response.setId(ecoaction.getId());
        response.setDate(ecoaction.getDate());
        response.setDescription(ecoaction.getDescription());
        response.setLocation(habitat.getLocation());
        response.setType(habitat.getType());
        response.setUserName(user.getName());
        response.setEmail(user.getEmail());


        return ResponseEntity.ok(response);
    }

}
