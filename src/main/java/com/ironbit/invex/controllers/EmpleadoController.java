package com.ironbit.invex.controllers;

import com.ironbit.invex.entities.Empleado;
import com.ironbit.invex.services.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Empleado>> save(@RequestBody List<Empleado> e){
        logger.trace("Call save empleado service");
        return new ResponseEntity<>(empleadoService.save(e), HttpStatus.CREATED);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Empleado>> getAll(){
        logger.trace("Call get all empleado service");
        return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
    }

    @PutMapping(value="/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Empleado> update(@PathVariable("id") Long id,@RequestBody Empleado empleado){
        logger.trace("Call update empleado service");
        return empleadoService.getById(id)
                .map(empleadoFound -> {
                    empleadoFound.setPrimerNombre(empleado.getPrimerNombre()!=null?empleado.getPrimerNombre():empleadoFound.getPrimerNombre());
                    empleadoFound.setSegundoNombre(empleado.getSegundoNombre()!=null?empleado.getSegundoNombre():empleadoFound.getSegundoNombre());
                    empleadoFound.setApMaterno(empleado.getApMaterno()!=null?empleado.getApMaterno():empleadoFound.getApMaterno());
                    empleadoFound.setApPaterno(empleado.getApPaterno()!=null?empleado.getApPaterno():empleadoFound.getApPaterno());
                    empleadoFound.setEdad(empleado.getEdad()!=null?empleado.getEdad():empleadoFound.getEdad());
                    empleadoFound.setSexo(empleado.getSexo()!=null?empleado.getSexo():empleadoFound.getSexo());
                    empleadoFound.setFechaNac(empleado.getFechaNac()!=null?empleado.getFechaNac():empleadoFound.getFechaNac());
                    empleadoFound.setPuesto(empleado.getPuesto()!=null?empleado.getPuesto():empleadoFound.getPuesto());

                    return new ResponseEntity<>(empleadoService.update(empleadoFound),HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable("id") Long id){
        logger.trace("Call delete empleado service");
        empleadoService.delete(id);
        return new ResponseEntity<String>("Empleado eliminado exitosamente",HttpStatus.OK);
    }
}
