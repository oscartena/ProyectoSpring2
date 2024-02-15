package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.servicios.PilotoServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class APIController {
    @Autowired
    private final PilotoServicio pilotoServicio;

    @Autowired
    public APIController(PilotoServicio pilotoServicio) {
        this.pilotoServicio = pilotoServicio;
    }

    // GET de todos los pilotos, devuelve un JSON con todos los pilotos
    @GetMapping(value = "/pilotos")
    public ResponseEntity<List<Piloto>> getAllPilotos()
    {
        List<Piloto> pilotos = pilotoServicio.findAllPilotos();
        return new ResponseEntity<>(pilotos, HttpStatus.OK);
    }

    // GET de un piloto, devuelve un JSON del piloto
    @GetMapping("/piloto/{id}")
    public ResponseEntity<Piloto> getPiloto(@PathVariable long id) {
        Piloto piloto = pilotoServicio.findPilotoById(id);
        return new ResponseEntity<>(piloto, HttpStatus.OK);
    }

    // POST de un piloto, crea un piloto
    @PostMapping("/pilotos")
    public ResponseEntity<Piloto> addPiloto(@RequestBody Piloto piloto) {
        Piloto nuevoPiloto = pilotoServicio.savePiloto(piloto);
        return new ResponseEntity<>(nuevoPiloto, HttpStatus.OK);
    }

    // PUT de un piloto, actualiza un piloto
    @PutMapping("/pilotos/{id}")
    public ResponseEntity<Piloto> modifyPiloto(@PathVariable long id, @RequestBody Piloto piloto) {
        Piloto pilotoActualizado = pilotoServicio.modifyPiloto(id, piloto);
        if (pilotoActualizado != null) {
            return new ResponseEntity<>(pilotoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Response> deletePiloto(@PathVariable long id) {
        pilotoServicio.deletePiloto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
