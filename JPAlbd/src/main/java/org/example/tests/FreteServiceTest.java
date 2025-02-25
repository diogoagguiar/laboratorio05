package org.example.tests;

import org.example.modelos.CategoriaFrete;
import org.example.modelos.*;
import org.example.servicos.FreteService;

import java.math.BigDecimal;
import java.util.List;


public class FreteServiceTest {
    public static void main(String[] args) {
        FreteService freteService = new FreteService();

        // 1️⃣ Teste: Registrar um frete
        System.out.println("=== Teste: Registrar Frete ===");
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos Souza");
        cliente.setTelefone("11987654321");

        CategoriaFrete categoriaFrete = new CategoriaFrete();
        categoriaFrete.setNome("Entrega Rápida");
        categoriaFrete.setPercentualAdicional(10);

        Cidade origem = new Cidade();
        origem.setNome("São Paulo");
        origem.setEstado("SP");

        Cidade destino = new Cidade();
        destino.setNome("Campinas");
        destino.setEstado("SP");

        Frete frete = new Frete();
        frete.setCodigo("FRETE001");
        frete.setCliente(cliente);
        frete.setCidadeOrigem(origem);
        frete.setCidadeDestino(destino);
        frete.setCategoriaFrete(categoriaFrete);
        frete.setValorKmRodado(BigDecimal.valueOf(5));

        try {
            freteService.registrarFrete(frete);
            System.out.println("✅ Frete registrado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao registrar frete: " + e.getMessage());
        }

        // 2️⃣ Teste: Buscar um frete por ID
        System.out.println("\n=== Teste: Buscar Frete por ID ===");
        Long idFrete = frete.getId(); // Simulando o ID retornado pelo banco de dados
        Frete freteBuscado = freteService.buscarFretePorId(idFrete);
        if (freteBuscado != null) {
            System.out.println("✅ Frete encontrado: " + freteBuscado.getCodigo());
        } else {
            System.out.println("❌ Frete não encontrado!");
        }

        // 3️⃣ Teste: Listar fretes de um cliente
        System.out.println("\n=== Teste: Listar Fretes de um Cliente ===");
        List<Frete> fretesCliente = freteService.listarFretesPorCliente(cliente.getId());
        if (!fretesCliente.isEmpty()) {
            System.out.println("✅ Cliente possui " + fretesCliente.size() + " fretes.");
        } else {
            System.out.println("❌ Nenhum frete encontrado para o cliente.");
        }

        // Finalizando o serviço
        freteService.fechar();
    }
}

