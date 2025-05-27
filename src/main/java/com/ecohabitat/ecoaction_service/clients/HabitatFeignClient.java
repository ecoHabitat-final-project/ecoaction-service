package com.ecohabitat.ecoaction_service.clients;

import com.ecohabitat.ecoaction_service.dto.HabitatResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("habitat-service")
public interface HabitatFeignClient {

    @GetMapping("/api/habitat{id}")
    HabitatResponseDTO getHabitatById(@PathVariable long id);
}
