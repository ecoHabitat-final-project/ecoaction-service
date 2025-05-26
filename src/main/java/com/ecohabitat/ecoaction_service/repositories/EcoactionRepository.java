package com.ecohabitat.ecoaction_service.repositories;

import com.ecohabitat.ecoaction_service.models.Ecoaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcoactionRepository extends JpaRepository <Ecoaction, Long> {

    Optional<Ecoaction> findEcoactionByHabitatId(long habitatId);
}
