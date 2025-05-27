package com.ecohabitat.ecoaction_service.services;

import com.ecohabitat.ecoaction_service.clients.HabitatFeignClient;
import com.ecohabitat.ecoaction_service.clients.UserFeignClient;
import com.ecohabitat.ecoaction_service.dto.EcoactionResponseDTO;
import com.ecohabitat.ecoaction_service.dto.HabitatDTO;
import com.ecohabitat.ecoaction_service.dto.HabitatResponseDTO;
import com.ecohabitat.ecoaction_service.dto.UserResponseDTO;
import com.ecohabitat.ecoaction_service.exceptions.EcoactionNotFoundException;
import com.ecohabitat.ecoaction_service.exceptions.EcoactionsNotFoundException;
import com.ecohabitat.ecoaction_service.models.Ecoaction;
import com.ecohabitat.ecoaction_service.repositories.EcoactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EcoactionService {

    @Autowired
    EcoactionRepository ecoactionRepository;

    @Autowired
    private HabitatFeignClient habitatFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;




    public Ecoaction getEcoactionById(long id){
        return ecoactionRepository.findById(id).
                orElseThrow( ()-> new EcoactionNotFoundException("Ecoaction id :" + id + " not found"));
    }

    public List<Ecoaction> getEcoactions(){
        if (ecoactionRepository.findAll().isEmpty()) {
            throw new EcoactionsNotFoundException("There are not Ecoactions into database");
        }
        return ecoactionRepository.findAll();
    }


    public Ecoaction getEcoactionByUserId(long userId){
        return ecoactionRepository.findEcoactionByUserId(userId).
                orElseThrow(()-> new EcoactionNotFoundException("user id :" + userId + " not found"));
    }

    public Ecoaction createEcoaction(Ecoaction ecoaction){
        return ecoactionRepository.save(ecoaction);
    }

    public void deleteEcoactionById(long id){
        ecoactionRepository.deleteById(id);
    }

    // por si la reforma (ecoaction) se había realizado en otro habitat
    public Ecoaction updateEcoaction(long ecoactionId, HabitatDTO habitatDTO){
        Ecoaction ecoaction = getEcoactionById(ecoactionId); //esto ya lanza una excepción
        ecoaction.setHabitatId(habitatDTO.getHabitatId());
        return ecoactionRepository.save(ecoaction);

    }

    //-----------------------------------------------------------------------------------------
    public EcoactionResponseDTO getFullEcoactionById(long ecoactionId){
        Ecoaction ecoaction = getEcoactionById(ecoactionId);

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

        return response;
    }


    public List<EcoactionResponseDTO> getFullEcoactions(){
        if (ecoactionRepository.findAll().isEmpty()) {
            throw new EcoactionsNotFoundException("There are not Ecoactions into database");
        }

        List<Ecoaction> ecoactions = ecoactionRepository.findAll();
        List <EcoactionResponseDTO> ecoactionResponses = new ArrayList<EcoactionResponseDTO>();
        for (Ecoaction ecoaction : ecoactions) {
            ecoactionResponses.add(this.getFullEcoactionById(ecoaction.getId()));
        }

        return ecoactionResponses;
    }




    public EcoactionResponseDTO getFullEcoactionByHabitat(long habitatId) {

        Ecoaction ecoaction= ecoactionRepository.findEcoactionByHabitatId(habitatId).
                orElseThrow(()-> new EcoactionNotFoundException("habitat id :" + habitatId + " not found"));

        HabitatResponseDTO habitatDTO = habitatFeignClient.getHabitatById(habitatId);
        UserResponseDTO user = userFeignClient.getUserById(ecoaction.getUserId());
        EcoactionResponseDTO response = new EcoactionResponseDTO();
        response.setId(ecoaction.getId());
        response.setDate(ecoaction.getDate());
        response.setDescription(ecoaction.getDescription());
        response.setLocation(habitatDTO.getLocation());
        response.setType(habitatDTO.getType());
        response.setUserName(user.getName());
        response.setEmail(user.getEmail());


        return response;
    }



}
