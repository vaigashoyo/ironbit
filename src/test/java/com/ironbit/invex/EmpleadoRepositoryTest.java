package com.ironbit.invex;

import com.ironbit.invex.entities.Empleado;
import com.ironbit.invex.repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    private Empleado empleado;
    @BeforeEach
    void setup(){
        empleado = Empleado.builder()
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
    public void testSaveEmployee(){
        //given
        Empleado emp = Empleado.builder()
                .primerNombre("Guillermo")
                .segundoNombre("David")
                .apPaterno("Loyo")
                .apMaterno("Gomez")
                .edad(31)
                .sexo("Masculino")
                .fechaNac(new Date())
                .puesto("Developer")
                .build();

        //when
        Empleado empSaved = empleadoRepository.save(emp);

        //then
        assertThat(empSaved).isNotNull();
        assertThat(empSaved.getId()).isGreaterThan(0);
    }

    @DisplayName("Test to get all employees")
    @Test
    public void testGetAllEmployee(){
        Empleado emp = Empleado.builder()
                .primerNombre("Daniel")
                .segundoNombre("Ulises")
                .apPaterno("Gutierrez")
                .apMaterno("Ramirez")
                .edad(19)
                .sexo("Masculino")
                .fechaNac(new Date())
                .puesto("Director")
                .build();

        empleadoRepository.save(emp);
        empleadoRepository.save(empleado);
        List<Empleado> employeeList = empleadoRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isGreaterThan(1);
    }

    @DisplayName("Test to get employee by ID")
    @Test
    void testGetEmployeeByID(){
        empleadoRepository.save(empleado);

        Empleado employeeBD = empleadoRepository.findById(empleado.getId()).get();

        assertThat(employeeBD).isNotNull();
    }

    @DisplayName("Test to update employeee")
    @Test
    void testUpdateEmployee(){
        empleadoRepository.save(empleado);

        Empleado employeeFound = empleadoRepository.findById(empleado.getId()).get();
        employeeFound.setPuesto("Coordinador");
        employeeFound.setApPaterno("Laurrabaquio");
        Empleado employeeUpdated = empleadoRepository.save(employeeFound);

        assertThat(employeeUpdated.getPuesto()).isEqualTo("Coordinador");
        assertThat(employeeUpdated.getApPaterno()).isEqualTo("Laurrabaquio");
    }

    @DisplayName("Test to delete employee")
    @Test
    void testDeleteEmployee(){
        empleadoRepository.save(empleado);

        empleadoRepository.deleteById(empleado.getId());
        Optional<Empleado> employeeDeleted = empleadoRepository.findById(empleado.getId());

        assertThat(employeeDeleted).isEmpty();
    }
}
