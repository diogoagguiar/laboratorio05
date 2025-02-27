package org.example.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.*;
import org.example.servicos.FreteService;
import java.util.List;

public class ClienteTeste {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("transportadoraPU");

        try (emf; EntityManager em = emf.createEntityManager()) {
            FreteService freteService = new FreteService(em);

            // Iniciando transação para persistir dados iniciais
            em.getTransaction().begin();

            // Criando e persistindo cliente
            Cliente cliente = new Cliente();
            cliente.setNome("João Carlos");
            cliente.setEmail("joao.carlos@gmail.com");
            cliente.setTelefone("11988887777");
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

            // Criando e persistindo categoria de frete
            CategoriaFrete categoriaFrete = new CategoriaFrete();
            categoriaFrete.setNome("SEDEX");
            categoriaFrete.setDescricao("Entrega rápida");
            categoriaFrete.setPercentualAdicional(30.0F);
            em.persist(categoriaFrete);

            // Criando e persistindo veículo
            Veiculo veiculo = new Veiculo();
            veiculo.setNumeroPlaca("ABC-1234");
            em.persist(veiculo);

            // Criando e persistindo funcionário
            Funcionario funcionario = new Funcionario();
            funcionario.setNome("Carlos Silva");
            funcionario.setMatricula(12345);
            em.persist(funcionario);

            // Criando e persistindo distância entre as cidades
            Distancia distancia = new Distancia();
            distancia.setOrigem(cidadeOrigem);
            distancia.setDestino(cidadeDestino);
            distancia.setQuilometros(400.0);
            em.persist(distancia);

            // Finalizando transação inicial
            em.getTransaction().commit();

            // Criando e registrando DOIS FRETES DIFERENTES
            em.getTransaction().begin();

            Frete frete1 = new Frete();
            frete1.setCliente(cliente);
            frete1.setCidadeDeOrigem(cidadeOrigem); // Alterado para cidadeDeOrigem
            frete1.setCidadeDeDestino(cidadeDestino); // Alterado para cidadeDeDestino
            frete1.setVeiculo(veiculo);
            frete1.setCategoriaFrete(categoriaFrete);
            frete1.setCodigo("FRETE_1");
            frete1.setNumeroNotaFiscal("1001");
            frete1.setValorKmRodado(2.0);
            frete1.setFuncionario(funcionario);
            em.persist(frete1);

            Frete frete2 = new Frete();
            frete2.setCliente(cliente);
            frete2.setCidadeDeOrigem(cidadeDestino); // Alterado para cidadeDeOrigem
            frete2.setCidadeDeDestino(cidadeOrigem); // Alterado para cidadeDeDestino
            frete2.setVeiculo(veiculo);
            frete2.setCategoriaFrete(categoriaFrete);
            frete2.setCodigo("FRETE_2");
            frete2.setNumeroNotaFiscal("1002");
            frete2.setValorKmRodado(3.5);
            frete2.setFuncionario(funcionario);
            em.persist(frete2);

            em.getTransaction().commit();

            System.out.println("✅ Fretes registrados com sucesso!");

            // Listar fretes do cliente
            List<Frete> fretesCliente = freteService.listarFretesPorCliente(cliente.getId());

            System.out.println("\n=== FRETES REGISTRADOS PARA O CLIENTE ===");
            for (Frete f : fretesCliente) {
                System.out.println("-----------------------------------------------------");
                System.out.printf("Código: %s | Nota Fiscal: %s | Valor por Km: R$ %.2f%n",
                        f.getCodigo(), f.getNumeroNotaFiscal(), f.getValorKmRodado());
                System.out.printf("Origem: %s - %s | Destino: %s - %s%n",
                        f.getCidadeDeOrigem().getNome(), f.getCidadeDeOrigem().getEstado(),
                        f.getCidadeDeDestino().getNome(), f.getCidadeDeDestino().getEstado());
                System.out.printf("Categoria: %s - %s%n",
                        f.getCategoriaFrete().getNome(), f.getCategoriaFrete().getDescricao());
                System.out.printf("Veículo: %s | Cliente: %s (%s)%n",
                        f.getVeiculo().getNumeroPlaca(), f.getCliente().getNome(), f.getCliente().getEmail());
                System.out.println("-----------------------------------------------------");
            }

            // Buscar frete por ID
            Frete freteBuscado = freteService.buscarFretePorId(frete1.getId());
            System.out.println("\nDetalhes do Frete Buscado:");
            System.out.printf("Código: %s | Nota Fiscal: %s | Valor por Km: R$ %.2f%n",
                    freteBuscado.getCodigo(), freteBuscado.getNumeroNotaFiscal(), freteBuscado.getValorKmRodado());

        } catch (Exception e) {
            System.err.println("❌ Erro no teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
