package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.ItemFrete;
import java.util.List;

public class ItemFreteRepositorio {

    private final DAOGenerico<ItemFrete> dao;
    private final EntityManager manager;

    public ItemFreteRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, ItemFrete.class);
        this.manager = manager;
    }

    public ItemFrete buscarPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public List<ItemFrete> buscarTodos() {
        return dao.buscaTodos();
    }

    public ItemFrete salvarOuAtualizar(ItemFrete itemFrete) {
        return dao.salvaOuAtualiza(itemFrete);
    }

    public void remover(ItemFrete itemFrete) {
        dao.remove(itemFrete);
    }

    public List<ItemFrete> buscarPorFrete(Integer freteId) {
        return manager.createQuery(
                        "SELECT i FROM ItemFrete i WHERE i.frete.id = :freteId",
                        ItemFrete.class)
                .setParameter("freteId", freteId)
                .getResultList();
    }
}
