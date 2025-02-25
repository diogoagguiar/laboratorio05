package org.example.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.Cliente;

public class ClienteTesteSimples {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("transportadoraPU"); // Substitua pela sua unidade de persistência de teste
        EntityManager em = emf.createEntityManager();

        // Teste de Salvar Cliente
        em.getTransaction().begin();
        Cliente cliente = new Cliente();
        cliente.setNome("Teste Simples");
        cliente.setCpf("123.456.789-00");
        em.persist(cliente);
        em.getTransaction().commit();

        if (cliente.getId() != null) {
            System.out.println("Cliente salvo com sucesso! ID: " + cliente.getId());
        } else {
            System.out.println("Erro ao salvar cliente.");
        }

        // Teste de Buscar Cliente
        Cliente clienteEncontrado = em.find(Cliente.class, cliente.getId());
        if (clienteEncontrado != null && clienteEncontrado.getNome().equals("Teste Simples")) {
            System.out.println("Cliente encontrado com sucesso! Nome: " + clienteEncontrado.getNome());
        } else {
            System.out.println("Erro ao buscar cliente.");
        }

        // Teste de Atualizar Cliente
        em.getTransaction().begin();
        cliente.setNome("Teste Atualizado");
        em.merge(cliente);
        em.getTransaction().commit();

        Cliente clienteAtualizado = em.find(Cliente.class, cliente.getId());
        if (clienteAtualizado != null && clienteAtualizado.getNome().equals("transportadora")) {
            System.out.println("Cliente atualizado com sucesso! Novo nome: " + clienteAtualizado.getNome());
        } else {
            System.out.println("Erro ao atualizar cliente.");
        }

        // Teste de Remover Cliente
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();

        Cliente clienteRemovido = em.find(Cliente.class, cliente.getId());
        if (clienteRemovido == null) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Erro ao remover cliente.");
        }

        em.close();
        emf.close();
    }
}
