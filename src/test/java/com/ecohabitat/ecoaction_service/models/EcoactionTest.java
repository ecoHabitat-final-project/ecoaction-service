package com.ecohabitat.ecoaction_service.models;

import com.ecohabitat.ecoaction_service.dto.HabitatDTO;
import com.ecohabitat.ecoaction_service.services.EcoactionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EcoactionTest {
    @Autowired
    EcoactionService ecoactionService;
    Ecoaction ecoaction;


    @BeforeEach
    void setUp() {
        ecoaction = new Ecoaction();
        ecoaction.setDate("2024-12-31");
        ecoaction.setDescription("test description");
        ecoaction.setHabitatId(99);
        ecoactionService.createEcoaction(ecoaction);
    }

//    @AfterEach
//    void tearDown() {
//
//        Ecoaction ecoTest = ecoactionService.getEcoactionByHabitatId(33L);
//        ecoactionService.deleteEcoactionById(ecoTest.getId());
//    }


    @Test
    @DisplayName("Test find ecoaction by id")
    void testFindEcoactionById() {
        Ecoaction eco = ecoactionService.getEcoactionById(1L);
        assertEquals(1L, eco.getId());

    }

    @Test
    @DisplayName("Test all ecoactions")
    void testGetEcoactions() {
        List<Ecoaction> ecoactions = ecoactionService.getEcoactions();
        assertNotNull(ecoactions);
    }


}