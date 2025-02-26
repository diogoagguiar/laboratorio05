package org.example.repositorio;


import jakarta.persistence.EntityManager;
import org.example.modelos.Cliente;
import java.util.List;

public class ClienteRepositorio {

    private final DAOGenerico<Cliente> dao;

    public ClienteRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, Cliente.class);
    }

    public Cliente buscarPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public List<Cliente> buscarTodos() {
        return dao.buscaTodos();
    }

    public Cliente salvarOuAtualizar(Cliente cliente) {
        return dao.salvaOuAtualiza(cliente);
    }

    public void remover(Cliente cliente) {
        dao.remove(cliente);
    }
}

