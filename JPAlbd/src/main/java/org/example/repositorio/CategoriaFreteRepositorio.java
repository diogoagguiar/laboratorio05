package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.CategoriaFrete;

import jakarta.persistence.EntityManager;
import org.example.modelos.CategoriaFrete;

import java.util.List;

public class CategoriaFreteRepositorio {

    private final EntityManager manager;
    private final DAOGenerico<CategoriaFrete> daoGenerico;

    public CategoriaFreteRepositorio(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, CategoriaFrete.class);
    }

    public CategoriaFrete salvaOuAtualiza(CategoriaFrete categoriaFrete) {
        return daoGenerico.salvaOuAtualiza(categoriaFrete);
    }

    public CategoriaFrete buscaPorId(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    public List<CategoriaFrete> buscaTodos() {
        return daoGenerico.buscaTodos();
    }

    public void remove(CategoriaFrete categoriaFrete) {
        daoGenerico.remove(categoriaFrete);
    }

    // Métodos específicos para CategoriaFrete podem ser adicionados aqui
    // Ex: Buscar por nome, buscar por percentualAdicional, etc.
}
