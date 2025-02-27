package org.example.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.*;
import org.example.servicos.FreteService;

import java.util.List;

public class FreteServicoTeste {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("transportadoraPU");
        EntityManager em = emf.createEntityManager();

        try {
            FreteService freteService = new FreteService(em);

            // Iniciando transação para persistir dados iniciais
            em.getTransaction().begin();

            // Criando e persistindo cliente
            Cliente cliente = new Cliente();
            cliente.setNome("Diogo Silva Aguiar");
            cliente.setEmail("joao.silva@gmail.com");
            cliente.setTelefone("555-5555");
            cliente.setAtivo(true);
            em.persist(cliente);

            // Criando e persistindo cidades
            Cidade cidadeOrigem = new Cidade();
            cidadeOrigem.setNome("São Paulo");
            cidadeOrigem.setEstado("SP");
            em.persist(cidadeOrigem);

            Cidade cidadeDestino = new Cidade();
            cidadeDestino.setNome("Rio de Janeiro");
            cidadeDestino.setEstado("RJ");
            em.persist(cidadeDestino);

            // Criando e persistindo distância entre as cidades
            Distancia distancia = new Distancia();
            distancia.setOrigem(cidadeOrigem);
            distancia.setDestino(cidadeDestino);
            distancia.setQuilometros(400.0);
            em.persist(distancia);

            // Criando e persistindo categoria de frete
            CategoriaFrete categoria = new CategoriaFrete();
            categoria.setNome("Entrega Rápida");
            categoria.setDescricao("Entrega rápida");
            categoria.setPercentualAdicional(10.0F); // 10%
            em.persist(categoria);

            // Criando e persistindo veículo
            Veiculo veiculo = new Veiculo();
            veiculo.setNumeroPlaca("ABC-1234");
            em.persist(veiculo);

            // Criando e persistindo funcionário
            Funcionario funcionario = new Funcionario();
            funcionario.setNome("Lorena Teixeira");
            funcionario.setCpf("123.456.789-00"); // CPF com formato correto
            funcionario.setMatricula(123454);
            em.persist(funcionario);

            // Criando e persistindo frete
            Frete frete = new Frete();
            frete.setCliente(cliente);
            frete.setFuncionario(funcionario);
            frete.setCidadeDeOrigem(cidadeOrigem); // Alterado para cidadeDeOrigem
            frete.setCidadeDeDestino(cidadeDestino); // Alterado para cidadeDeDestino
            frete.setCategoriaFrete(categoria);
            frete.setValorKmRodado(2.0); // R$2,00 por km
            frete.setVeiculo(veiculo);
            frete.setCodigo("FRETE_123"); // Código do frete
            frete.setNumeroNotaFiscal("NF-1001"); // Adicionando número de nota fiscal
            em.persist(frete);

            // Finalizando transação
            em.getTransaction().commit();

            // Registrar frete
            freteService.registrarFrete(frete);
            System.out.println("✅ Frete registrado com sucesso!");

            // Buscar frete por ID
            Frete freteEncontrado = freteService.buscarFretePorId(frete.getId());
            double valorFrete = freteEncontrado.calcularFrete(distancia);
            System.out.println("\nDetalhes do Frete Encontrado:");
            System.out.printf("Código: %s | Nota Fiscal: %s | Valor do Frete: R$ %.2f%n",
                    freteEncontrado.getCodigo(), freteEncontrado.getNumeroNotaFiscal(), valorFrete);

            // Listar fretes de um cliente
            List<Frete> fretesCliente = freteService.listarFretesPorCliente(cliente.getId());
            System.out.println("\nTotal de fretes do cliente: " + fretesCliente.size());

        } catch (Exception e) {
            System.err.println("❌ Erro no teste: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
