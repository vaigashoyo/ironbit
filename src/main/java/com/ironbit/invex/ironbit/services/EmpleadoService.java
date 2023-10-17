package com.ironbit.invex.ironbit.services;

import com.ironbit.invex.ironbit.entities.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    public List<Empleado> save(List<Empleado> emp);
    public List<Empleado> getAll();
    Optional<Empleado> getById(Long id);
    public void delete(Long id);
    public Empleado update(Empleado emp);
}
