package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Filial;
import java.util.List;

public class FilialRepositorio {

    private final DAOGenerico<Filial> dao;

    public FilialRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, Filial.class);
    }

    public Filial buscarPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public List<Filial> buscarTodos() {
        return dao.buscaTodos();
    }

    public Filial salvarOuAtualizar(Filial filial) {
        return dao.salvaOuAtualiza(filial);
    }

    public void remover(Filial filial) {
        dao.remove(filial);
    }
}

