package com.ecohabitat.ecoaction_service.services;

import com.ecohabitat.ecoaction_service.dto.HabitatDTO;
import com.ecohabitat.ecoaction_service.exceptions.EcoactionNotFoundException;
import com.ecohabitat.ecoaction_service.exceptions.EcoactionsNotFoundException;
import com.ecohabitat.ecoaction_service.models.Ecoaction;
import com.ecohabitat.ecoaction_service.repositories.EcoactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcoactionService {

    @Autowired
    EcoactionRepository ecoactionRepository;

//    private final EcoactionRepository ecoactionRepository;
//    private final HabitatRepository habitatRepository;
//    private final UserRepository userRepository;




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


    public Ecoaction getEcoactionByHabitatId(long habitatId){
        return ecoactionRepository.findEcoactionByHabitatId(habitatId).
                orElseThrow(()-> new EcoactionNotFoundException("habitat id :" + habitatId + " not found"));
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
}
