package com.ironbit.invex;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironbit.invex.controllers.EmpleadoController;
import com.ironbit.invex.entities.Empleado;
import com.ironbit.invex.services.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebMvcTest
public class EmpleadoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;



    @DisplayName("Test to get all employees")
    @Test
    void testGetAllEmployee() throws Exception{
        //given
        List<Empleado> list = new ArrayList<>();
        list.add(Empleado.builder().primerNombre("Christian").apPaterno("Ramirez").puesto("Director").build());
        list.add(Empleado.builder().primerNombre("Gabriel").apPaterno("Ramirez").puesto("Coordinador").build());
        list.add(Empleado.builder().primerNombre("Julen").apPaterno("Ramirez").puesto("Supervisor").build());
        list.add(Empleado.builder().primerNombre("Biaggio").apPaterno("Ramirez").puesto("Lead").build());
        list.add(Empleado.builder().primerNombre("Adrian").apPaterno("Ramirez").puesto("Developer").build());
        given(empleadoService.getAll()).willReturn(list);

        //when
        ResultActions response = mockMvc.perform(get("/api/v1/empleado"));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(list.size())));
    }
}
