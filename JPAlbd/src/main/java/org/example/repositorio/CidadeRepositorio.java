package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Cidade;

public class CidadeRepositorio extends DAOGenerico<Cidade> {
    public CidadeRepositorio(EntityManager manager) {
        super(manager, Cidade.class);
    }
}
