package com.dam.proyectospring.servicios;

import com.dam.proyectospring.modelos.Piloto;

import java.util.List;

public interface PilotoServicio {
    List<Piloto> findAllPilotos();

    Piloto findPilotoById(long id);

    Piloto savePiloto(Piloto piloto);

    Piloto modifyPiloto(long id, Piloto piloto);

    void deletePiloto(long id);
}
