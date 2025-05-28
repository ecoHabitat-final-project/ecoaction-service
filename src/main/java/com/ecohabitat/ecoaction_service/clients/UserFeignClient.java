package com.ecohabitat.ecoaction_service.clients;


import com.ecohabitat.ecoaction_service.dto.EcoUserDTO;
import com.ecohabitat.ecoaction_service.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service")
public interface UserFeignClient {

    @GetMapping("/api/user/{id}")
    UserResponseDTO getUserById (@PathVariable long id);

    @PostMapping("/api/user")
    EcoUserDTO createUser(@RequestBody EcoUserDTO user);
}
