package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Filial;

public class FilialRepositorio extends DAOGenerico<Filial> {
    public FilialRepositorio(EntityManager manager) {
        super(manager, Filial.class);
    }
}
