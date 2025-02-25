package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.ItemFrete;

public class ItemFreteRepositorio extends DAOGenerico<ItemFrete> {
    public ItemFreteRepositorio(EntityManager manager) {
        super(manager, ItemFrete.class);
    }
}
