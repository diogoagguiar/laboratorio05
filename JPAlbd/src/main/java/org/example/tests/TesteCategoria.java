package org.example.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.CategoriaFrete;
import org.example.repositorio.CategoriaFreteRepositorio;

public class TesteCategoria {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("transportadoraPU"); // Substitua "sua-persistence-unit"
        EntityManager em = emf.createEntityManager();

        CategoriaFreteRepositorio categoriaFreteRepository = new CategoriaFreteRepositorio(em);

        // Criar uma nova categoria de frete
        CategoriaFrete categoriaFrete = new CategoriaFrete();
        categoriaFrete.setNome("Carga Pesada");
        categoriaFrete.setDescricao("Fretes para cargas pesadas");
        categoriaFrete.setPercentualAdicional(0.15f);

        em.getTransaction().begin();
        categoriaFreteRepository.salvaOuAtualiza(categoriaFrete);
        em.getTransaction().begin();
        em.getTransaction().commit();

        // Buscar a categoria de frete por ID
        CategoriaFrete categoriaFreteEncontrada = categoriaFreteRepository.buscaPorId(categoriaFrete.getId());
        System.out.println(categoriaFreteEncontrada);

        em.close();
        emf.close();
    }
}
