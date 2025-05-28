package com.ecohabitat.ecoaction_service.models.mockTests;

import com.ecohabitat.ecoaction_service.exceptions.EcoactionNotFoundException;
import com.ecohabitat.ecoaction_service.models.Ecoaction;
import com.ecohabitat.ecoaction_service.repositories.EcoactionRepository;
import com.ecohabitat.ecoaction_service.services.EcoactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EcoactionMockTests {

    @Mock
    private EcoactionRepository ecoactionRepository;

    @InjectMocks
    private EcoactionService ecoactionService;

    private Ecoaction testEcoaction;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testEcoaction = new Ecoaction(999L, "Testing", "2025-05-28", 4L, 4L);
    }

    @Test
    @DisplayName("getEcoactionById if exist ecoaction then return it")
    void testGetEcoactionById() {
        when(ecoactionRepository.findById(999L)).thenReturn(Optional.of(testEcoaction));

        Ecoaction result = ecoactionService.getEcoactionById(999L);

        assertNotNull(result);
        assertEquals(testEcoaction.getId(), result.getId());
        verify(ecoactionRepository).findById(999L);
    }

    @Test
    @DisplayName("getEcoactionById do not exist throw exception")
    void testGetEcoactionById_NotFound() {
        when(ecoactionRepository.findById(1000L)).thenReturn(Optional.empty());

        assertThrows(EcoactionNotFoundException.class, () -> ecoactionService.getEcoactionById(1000L));
    }

}
