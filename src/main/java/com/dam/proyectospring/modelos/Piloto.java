package com.dam.proyectospring;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document("pilotos")
public class Piloto {
    @Id
    private String id;
    private String nombre;
    private String abreviatura;
    private int numero;
    private String equipo;
    private String pais;
    private LocalDate fechaNacimiento;
}
