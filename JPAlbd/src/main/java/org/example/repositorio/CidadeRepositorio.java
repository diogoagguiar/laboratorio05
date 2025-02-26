package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Cidade;
import java.util.List;

public class CidadeRepositorio {

    private final DAOGenerico<Cidade> dao;

    public CidadeRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, Cidade.class);
    }

    public Cidade buscarPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public List<Cidade> buscarTodos() {
        return dao.buscaTodos();
    }

    public Cidade salvarOuAtualizar(Cidade cidade) {
        return dao.salvaOuAtualiza(cidade);
    }

    public void remover(Cidade cidade) {
        dao.remove(cidade);
    }
}
