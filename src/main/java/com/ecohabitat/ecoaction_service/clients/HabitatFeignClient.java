package com.ecohabitat.ecoaction_service.clients;

import com.ecohabitat.ecoaction_service.dto.EcoHabitatDTO;
import com.ecohabitat.ecoaction_service.dto.EcoUserDTO;
import com.ecohabitat.ecoaction_service.dto.HabitatResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "habitat-service")
public interface HabitatFeignClient {

    @GetMapping("/api/habitat/{id}")
    HabitatResponseDTO getHabitatById(@PathVariable ("id") long id);

    @PostMapping("/api/habitat")
    EcoHabitatDTO createHabitat(@RequestBody EcoHabitatDTO habitat);
}
