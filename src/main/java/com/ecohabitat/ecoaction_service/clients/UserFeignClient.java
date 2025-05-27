package com.ecohabitat.ecoaction_service.clients;


import com.ecohabitat.ecoaction_service.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
public interface UserFeignClient {
    @GetMapping("/api/user/{id}")
    UserResponseDTO getUserById (@PathVariable long id);
}
