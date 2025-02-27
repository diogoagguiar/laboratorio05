package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Frete;
import java.util.List;

public class FreteRepositorio {

    private final DAOGenerico<Frete> dao;
    private final EntityManager manager;

    public FreteRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, Frete.class);
        this.manager = manager;
    }

    public Frete buscarPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public List<Frete> buscarTodos() {
        return dao.buscaTodos();
    }

    public Frete salvarOuAtualizar(Frete frete) {
        return dao.salvaOuAtualiza(frete);
    }

    public void remover(Frete frete) {
        dao.remove(frete);
    }

    public List<Frete> buscarPorCliente(Integer clienteId) {
        return manager.createQuery("SELECT f FROM Frete f WHERE f.cliente.id = :clienteId", Frete.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }
}