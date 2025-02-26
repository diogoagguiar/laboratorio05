package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Distancia;
import java.util.List;

public class DistanciaRepositorio {

    private final DAOGenerico<Distancia> dao;
    private final EntityManager manager;

    public DistanciaRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, Distancia.class);
        this.manager = manager;
    }

    public Distancia buscarPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public List<Distancia> buscarTodos() {
        return dao.buscaTodos();
    }

    public Distancia salvarOuAtualizar(Distancia distancia) {
        return dao.salvaOuAtualiza(distancia);
    }

    public void remover(Distancia distancia) {
        dao.remove(distancia);
    }

    public Distancia buscarPorCidades(Integer origemId, Integer destinoId) {
        return manager.createQuery(
                        "SELECT d FROM Distancia d WHERE d.origem.id = :origemId AND d.destino.id = :destinoId",
                        Distancia.class)
                .setParameter("origemId", origemId)
                .setParameter("destinoId", destinoId)
                .getSingleResult();
    }
}
