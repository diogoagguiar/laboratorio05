package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Frete;

public class FreteRepositorio extends DAOGenerico<Frete> {
    public FreteRepositorio(EntityManager manager) {
        super(manager, Frete.class);
    }
}

