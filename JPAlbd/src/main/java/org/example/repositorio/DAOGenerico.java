package org.example.repositorio;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

public class DAOGenerico<T> {

    private final EntityManager manager;
    private final Class<T> clazz;

    public DAOGenerico(EntityManager manager, Class<T> clazz) {
        this.manager = manager;
        this.clazz = clazz;
    }

    public T buscaPorId(Object id) {
        return manager.find(clazz, id);
    }

    public List<T> buscaTodos() {
        return manager.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }

    public T salvaOuAtualiza(T t) {
        begin(); // Iniciar transação
        Object id = manager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t);
        if (id == null) { // Se o ID é nulo, significa que é um novo objeto
            manager.persist(t);
        } else {
            t = manager.merge(t); // Atualiza se já existir
        }
        commit(); // Finalizar transação
        return t;
    }

    public void remove(T t) {
        begin();
        T entity = manager.contains(t) ? t : manager.merge(t);
        manager.remove(entity);
        commit();
    }

    public void begin() {
        EntityTransaction tx = manager.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
    }

    public void commit() {
        EntityTransaction tx = manager.getTransaction();
        if (tx.isActive()) {
            tx.commit();
        }
    }

    public void rollback() {
        EntityTransaction tx = manager.getTransaction();
        if (tx.isActive()) {
            tx.rollback();
        }
    }

}

