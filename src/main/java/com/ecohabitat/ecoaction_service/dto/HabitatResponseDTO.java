package com.ecohabitat.ecoaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitatResponseDTO {
    private long id;
    private String location;
    private String type;
}
