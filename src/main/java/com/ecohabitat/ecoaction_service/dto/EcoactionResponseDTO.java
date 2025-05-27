package com.ecohabitat.ecoaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcoactionResponseDTO {

        //ecoaction
        private long id;
        private String date;
        private String description;

        //habitat
        private String location;
        private String type;

        // User
        private String userName;
        private String email;




    }

