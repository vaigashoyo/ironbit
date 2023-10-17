package com.ironbit.invex.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Employee entity
 * @author: David Loyo
 * @version: 1.0
 */
@Entity
@Table(name="empleado")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Schema(description = "Employee indentifier", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Schema(description = "Employee's name", example = "Luis")
    @Column(name="primer_nombre")
    private String primerNombre;

    @Schema(description = "Employee's middle name", example = "Alfonso")
    @Column(name="segundo_nombre")
    private String segundoNombre;

    @Schema(description = "Employee's last name", example = "Lopez")
    @Column(name="apellido_paterno")
    private String apPaterno;

    @Schema(description = "Employee's second last name", example = "Mendez")
    @Column(name="apellido_materno")
    private String apMaterno;

    @Schema(description = "Employee's age", example = "31")
    @Column(name = "edad")
    private Integer edad;

    @Schema(description = "Employee's genre", example = "Masculino")
    @Column(name="sexo")
    private String sexo;

    @Schema(description = "Employee's birthdate", example = "24-12-1992")
    @Column(name="fecha_nacimiento")
    @JsonFormat(pattern="dd-mm-yyyy")
    private Date fechaNac;

    @Schema(description = "Employee's work position", example = "Ingeniero de software")
    @Column(name="puesto")
    private String puesto;

}
