package com.ecohabitat.ecoaction_service.repositories;

import com.ecohabitat.ecoaction_service.models.EcoAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ecoactionRepository extends JpaRepository <EcoAction, Long> {

}
