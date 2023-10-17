package com.ironbit.invex.services;

import com.ironbit.invex.entities.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    public List<Empleado> save(List<Empleado> emp);
    public List<Empleado> getAll();
    Optional<Empleado> getById(Long id);
    public void delete(Long id);
    public Empleado update(Empleado emp);
}
