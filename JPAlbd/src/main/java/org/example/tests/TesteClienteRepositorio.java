package org.example.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.Cliente;
import org.example.repositorio.ClienteRepositorio;

public class TesteClienteRepositorio {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("transportadoraPU");
        EntityManager manager = factory.createEntityManager();

        ClienteRepositorio clienteRepo = new ClienteRepositorio(manager);

        // Criar um novo cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Ana Souza");

        cliente.setTelefone("11988887777");

        // Salvar cliente no banco de dados
        clienteRepo.begin();
        clienteRepo.salvaOuAtualiza(cliente);
        clienteRepo.commit();

        // Buscar cliente por ID
        Cliente encontrado = clienteRepo.buscaPorId(cliente.getId());
        System.out.println("Cliente encontrado: " + encontrado.getNome());

        // Listar todos os clientes
        System.out.println("Lista de Clientes:");
        clienteRepo.buscaTodos().forEach(c -> System.out.println(c.getNome()));

        // Fechar conexão
        manager.close();
        factory.close();
    }
}
