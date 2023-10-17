package com.ironbit.invex.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="empleado")
@Data
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="primer_nombre")
    private String primerNombre;
    @Column(name="segundo_nombre")
    private String segundoNombre;
    @Column(name="apellido_paterno")
    private String apPaterno;
    @Column(name="apellido_materno")
    private String apMaterno;
    @Column(name = "edad")
    private Integer edad;
    @Column(name="sexo")
    private String sexo;
    @Column(name="fecha_nacimiento")
    @JsonFormat(pattern="dd-mm-yyyy")
    private Date fechaNac;
    @Column(name="puesto")
    private String puesto;

}
