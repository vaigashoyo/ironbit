package com.ironbit.invex;

import com.ironbit.invex.entities.Empleado;
import com.ironbit.invex.exception.ApplicationException;
import com.ironbit.invex.repositories.EmpleadoRepository;
import com.ironbit.invex.services.EmpleadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setup(){
        empleado = Empleado.builder()
                .id(1L)
                .primerNombre("Javier")
                .segundoNombre("Luis")
                .apPaterno("Mendez")
                .apMaterno("Gomez")
                .edad(31)
                .sexo("Masculino")
                .fechaNac(new Date())
                .puesto("Developer")
                .build();
    }

    @DisplayName("Test to save employee")
    @Test
    void testSaveEmployee(){

        //when
        List<Empleado> employeeSaved = empleadoService.save( Arrays.asList(empleado));

        //then
        assertThat(employeeSaved).isNotNull();
    }

    @DisplayName("Test to get all employees")
    @Test
    void testGetAllEmployee(){
        Empleado empleado1 = Empleado.builder()
                .primerNombre("Daniel")
                .segundoNombre("Ulises")
                .apPaterno("Gutierrez")
                .apMaterno("Ramirez")
                .edad(19)
                .sexo("Masculino")
                .fechaNac(new Date())
                .puesto("Director")
                .build();
        given(empleadoRepository.findAll()).willReturn(Arrays.asList(empleado,empleado1));

        List<Empleado> empleados = empleadoService.getAll();

        assertThat(empleados).isNotNull();
        assertThat(empleados.size()).isEqualTo(2);
    }


    @DisplayName("Test to get employee by ID")
    @Test
    void testGetEmployeeByID(){
        given(empleadoRepository.findById(empleado.getId())).willReturn(Optional.of(empleado));

        Empleado empleadoGuardado = empleadoService.getById(empleado.getId()).get();

        assertThat(empleadoGuardado).isNotNull();
    }

    @DisplayName("Test to update employeee")
    @Test
    void testUpdateEmployee(){
        given(empleadoRepository.save(empleado)).willReturn(empleado);
        empleado.setPuesto("Coordinador");
        empleado.setApPaterno("Laurrabaquio");

        Empleado empleadoActualizado  = empleadoService.update(empleado);

        assertThat(empleadoActualizado.getPuesto()).isEqualTo("Coordinador");
        assertThat(empleadoActualizado.getApPaterno()).isEqualTo("Laurrabaquio");
    }

    @DisplayName("Test to delete employee")
    @Test
    void testDeleteEmployee(){
        assertThrows(ApplicationException.class,() -> {
            empleadoService.delete(empleado.getId());
        });

    }
}
