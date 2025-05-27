package com.ecohabitat.ecoaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EcoactionRequestDTO {
    private long habitatId;
    private long userId;

   // private String description;
  //  private String date;
}

