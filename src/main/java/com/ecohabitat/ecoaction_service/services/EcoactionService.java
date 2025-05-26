package com.ecohabitat.ecoaction_service.services;

import com.ecohabitat.ecoaction_service.dto.HabitatDTO;
import com.ecohabitat.ecoaction_service.exceptions.EcoactionNotFound;
import com.ecohabitat.ecoaction_service.exceptions.EcoactionsNotFound;
import com.ecohabitat.ecoaction_service.models.Ecoaction;
import com.ecohabitat.ecoaction_service.repositories.EcoactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcoactionService {

    @Autowired
    EcoactionRepository ecoactionRepository;


    public Ecoaction getEcoactionById(long id){
        return ecoactionRepository.findById(id).
                orElseThrow( ()-> new EcoactionNotFound("Ecoaction id :" + id + " not found"));
    }

    public List<Ecoaction> getEcoactions(){
        if (ecoactionRepository.findAll().isEmpty()) {
            throw new EcoactionsNotFound("There are not Ecoactions into database");
        }
        return ecoactionRepository.findAll();
    }


    public Ecoaction getEcoactionByHabitatId(long habitatId){
        return ecoactionRepository.findEcoactionByHabitatId(habitatId).
                orElseThrow(()-> new EcoactionNotFound("habitat id :" + habitatId + " not found"));
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
}
