package com.ecohabitat.ecoaction_service.models.mockMVCTest;

import com.ecohabitat.ecoaction_service.models.Ecoaction;
import com.ecohabitat.ecoaction_service.services.EcoactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jaxb.runtime.v2.runtime.output.SAXOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class EcoactionMockMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EcoactionService ecoactionService;

    @Autowired
    ObjectMapper objectMapper; //Json-Java, Java-Json

    //private Ecoaction eco;


    @Test
    @DisplayName("Test [Post]  /api/ecoaction")
    public void testPost() throws Exception {

        Ecoaction eco = new Ecoaction();
        eco.setId(999L);
        eco.setDescription("test description");
        eco.setDate("2025-05-29");
        eco.setHabitatId(5);
        eco.setUserId(5);

        when(ecoactionService.createEcoaction(any(Ecoaction.class)))
                .thenReturn(eco);

        String ecoJson = objectMapper.writeValueAsString(eco); //eco to json

        MvcResult resultPost = mockMvc.perform(post("/api/ecoaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ecoJson))


                .andExpect(status().isOk())
                .andReturn();

        System.out.println(resultPost.getResponse().getContentAsString());


    }

    @Test
    @DisplayName("Test [GET]  /api/ecoaction/{ecoactionId}")
    public void testGet() throws Exception {

        Ecoaction eco = new Ecoaction();
        eco.setId(999L);
        eco.setDescription("test description");
        eco.setDate("2025-05-29");
        eco.setHabitatId(5);
        eco.setUserId(5);

        when(ecoactionService.getEcoactionById(999L))
                .thenReturn(eco);

        MvcResult resultGet = mockMvc.perform(get("/api/ecoaction/999")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andReturn();

        System.out.println(resultGet.getResponse().getContentAsString());


    }
}
