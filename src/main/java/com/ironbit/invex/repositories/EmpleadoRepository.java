package com.ironbit.invex.repositories;

import com.ironbit.invex.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Interface
 * @author: David Loyo
 * @version: 1.0
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
