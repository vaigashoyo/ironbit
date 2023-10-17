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

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> save(List<Empleado> emp) {
        return emp.stream().map(empleado -> empleadoRepository.save(empleado)).collect(Collectors.toList());
    }

    @Override
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getById(Long id) {
        return empleadoRepository.findById(id);
    }

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

    @Override
    public Empleado update(Empleado emp) {
        return empleadoRepository.save(emp);
    }
}
