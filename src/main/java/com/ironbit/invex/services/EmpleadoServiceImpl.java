package com.ironbit.invex.services;

import com.ironbit.invex.entities.Empleado;
import com.ironbit.invex.exception.ApplicationException;
import com.ironbit.invex.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * In this Service we define all CRUD methods
 * @author: David Loyo
 * @version: 1.0
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    @Autowired
    EmpleadoRepository empleadoRepository;

    /**
     * <p>Service method to save one or many new employees</p>
     * @param emp array of Employee object
     * @return List of employees created
     * @since 1.0
     */
    @Override
    public List<Empleado> save(List<Empleado> emp) {
        return empleadoRepository.saveAll(emp);
    }

    /**
     * <p>Service method to get all employees</p>
     * @return List of employees
     * @since 1.0
     */
    @Override
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    /**
     * <p>Service method to get a employee</p>
     * @param id employee indentifier
     * @return Employee object found
     * @since 1.0
     */
    @Override
    public Optional<Empleado> getById(Long id) {
        return empleadoRepository.findById(id);
    }

    /**
     * <p>Service method to delete a employees</p>
     * @param id employee indentifier
     * @since 1.0
     */
    @Override
    public void delete(Long id) {
        Optional<Empleado> emp = empleadoRepository.findById(id);
        if(!emp.isPresent()){
            throw new ApplicationException(
                    "Empleado no encontrado",
                    String.format("Empleado id=%d no encontrado", id),
                    HttpStatus.NOT_FOUND
            );
        }
        empleadoRepository.deleteById(id);
    }

    /**
     * <p>Service method to update a employee</p>
     * @param emp employee object
     * @return Employee updated
     * @since 1.0
     */
    @Override
    public Empleado update(Empleado emp) {
        return empleadoRepository.save(emp);
    }
}
