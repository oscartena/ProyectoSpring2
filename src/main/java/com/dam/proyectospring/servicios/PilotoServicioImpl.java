package com.dam.proyectospring.servicios;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.repositorios.PilotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotoServicioImpl implements PilotoServicio {
    @Autowired
    private final PilotoRepositorio pilotoRepositorio;

    public PilotoServicioImpl(PilotoRepositorio pilotoRepositorio) {
        this.pilotoRepositorio = pilotoRepositorio;
    }

    @Override
    public List<Piloto> findAllPilotos() {
        return pilotoRepositorio.findAll();
    }

    @Override
    public Piloto findPilotoById(long id) {
        return pilotoRepositorio.findById(String.valueOf(id)).orElse(null);
    }

    @Override
    public Piloto savePiloto(Piloto piloto) {
        return pilotoRepositorio.save(piloto);
    }

    @Override
    public Piloto modifyPiloto(long id, Piloto piloto) {
        if (pilotoRepositorio.findById(String.valueOf(id)).isPresent()) {
            pilotoRepositorio.save(piloto);
            return piloto;
        } else {
            return null;

        }
    }

    @Override
    public void deletePiloto(long id) {
        pilotoRepositorio.deleteById(String.valueOf(id));
    }
}
