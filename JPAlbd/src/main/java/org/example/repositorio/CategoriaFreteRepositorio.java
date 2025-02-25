package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.CategoriaFrete;

public class CategoriaFreteRepositorio extends DAOGenerico<CategoriaFrete> {
    public CategoriaFreteRepositorio(EntityManager manager) {
        super(manager, CategoriaFrete.class);
    }
}
