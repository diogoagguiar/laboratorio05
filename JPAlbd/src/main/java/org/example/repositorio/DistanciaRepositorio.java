package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Distancia;

public class DistanciaRepositorio extends DAOGenerico<Distancia> {
    public DistanciaRepositorio(EntityManager manager) {
        super(manager, Distancia.class);
    }
}
