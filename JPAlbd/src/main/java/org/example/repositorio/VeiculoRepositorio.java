package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Veiculo;

public class VeiculoRepositorio extends DAOGenerico<Veiculo> {
    public VeiculoRepositorio(EntityManager manager) {
        super(manager, Veiculo.class);
    }
}

