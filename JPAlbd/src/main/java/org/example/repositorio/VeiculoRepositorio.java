package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Veiculo;
import java.util.List;

public class VeiculoRepositorio {

    private final DAOGenerico<Veiculo> dao;
    private final EntityManager manager;

    public VeiculoRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, Veiculo.class);
        this.manager = manager;
    }

    public Veiculo buscarPorPlaca(String placa) {
        return manager.find(Veiculo.class, placa);
    }

    public List<Veiculo> buscarTodos() {
        return dao.buscaTodos();
    }

    public Veiculo salvarOuAtualizar(Veiculo veiculo) {
        return dao.salvaOuAtualiza(veiculo);
    }

    public void remover(Veiculo veiculo) {
        dao.remove(veiculo);
    }
}

