package com.ironbit.invex.controllers;

import com.ironbit.invex.entities.Empleado;
import com.ironbit.invex.exception.ApiErrorResponse;
import com.ironbit.invex.services.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * In this class we define all endpoints of our services
 * @author: David Loyo
 * @version: 1.0
 */

@RestController
@RequestMapping("/api/v1/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    /**
     * <p>Endpoint for create a new employee</p>
     * @param emp employee object
     * @return A new employee created
     * @since 1.0
     */
    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class)) }),
            @ApiResponse(responseCode = "400", description = "There was an error on the request",
                    content = { @Content(mediaType = "application/json", schema= @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "There was an server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Empleado>> save(@RequestBody List<Empleado> emp){
        logger.trace("Call save empleado service");
        return new ResponseEntity<>(empleadoService.save(emp), HttpStatus.CREATED);
    }

    /**
     * <p>Endpoint for get all employees</p>
     * @return Array of employees
     * @since 1.0
     */
    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all the employees",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Empleado.class))) }),
            @ApiResponse(responseCode = "400", description = "There was an error on the request",
                    content = { @Content(mediaType = "application/json", schema= @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "There was an server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Empleado>> getAll(){
        logger.trace("Call get all empleado service");
        return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
    }

    /**
     * <p>Endpoint for update employee data</p>
     * @param id employee identifier
     * @param empleado employee object
     * @return Employee updated
     * @since 1.0
     */
    @Operation(summary = "Update employee data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class)) }),
            @ApiResponse(responseCode = "400", description = "There was an error on the request",
                    content = { @Content(mediaType = "application/json", schema= @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "There was an server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
    })
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

    /**
     * <p>Endpoint for delete a employee</p>
     * @param id employee indentifier
     * @return Successful message
     * @since 1.0
     */
    @Operation(summary = "Delete employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class)) }),
            @ApiResponse(responseCode = "400", description = "There was an error on the request",
                    content = { @Content(mediaType = "application/json", schema= @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "There was an server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
    })
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable("id") Long id){
        logger.trace("Call delete empleado service");
        empleadoService.delete(id);
        return new ResponseEntity<String>("Empleado eliminado exitosamente",HttpStatus.OK);
    }
}
