package org.example.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.*;
import org.example.repositorio.*;
import org.example.servicos.FreteService;

import java.util.List;

public class FreteServicoTeste {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("transportadoraPU");
        EntityManager em = emf.createEntityManager();

        try {
            FreteService freteService = new FreteService(em);

            // Criando objetos para teste
            Cliente cliente = new Cliente();
            cliente.setNome("Diogo Silva Aguiar");
            cliente.setEmail("joao.silva@gmail.com");
            cliente.setTelefone("555-5555");
            cliente.setAtivo(true);

            Cidade cidadeOrigem = new Cidade();
            cidadeOrigem.setNome("São Paulo");
            cidadeOrigem.setEstado("SP");

            Cidade cidadeDestino = new Cidade();
            cidadeDestino.setNome("Rio de Janeiro");
            cidadeDestino.setEstado("RJ");

            Distancia distancia = new Distancia();
            distancia.setOrigem(cidadeOrigem);
            distancia.setDestino(cidadeDestino);
            distancia.setQuilometros(400.0);

            CategoriaFrete categoria = new CategoriaFrete();
            categoria.setDescricao("Entrega Rápida");
            categoria.setPercentualAdicional(10); // 10%

            Veiculo veiculo = new Veiculo();
            veiculo.setNumeroPlaca("ABC-1234");

            Funcionario funcionario = new Funcionario();
            funcionario.setNome("Lorena Teixeira");
            funcionario.setCpf("123.456.789");
            funcionario.setMatricula(123454);

            Frete frete = new Frete();
            frete.setCliente(cliente);
            frete.setFuncionario(funcionario);
            frete.setCidadeOrigem(cidadeOrigem);
            frete.setCidadeDestino(cidadeDestino);
            frete.setCategoriaFrete(categoria);
            frete.setValorKmRodado(2.0); // R$2,00 por km
            frete.setVeiculo(veiculo);
            frete.setCodigo("FRETE_123"); // Adicione esta linha

            ClienteRepositorio clienteRepositorio = new ClienteRepositorio(em);
            FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio(em);
            CidadeRepositorio cidadeRepositorio = new CidadeRepositorio(em);
            DistanciaRepositorio distanciaRepositorio = new DistanciaRepositorio(em);
            CategoriaFreteRepositorio categoriaFreteRepositorio = new CategoriaFreteRepositorio(em);
            VeiculoRepositorio veiculoRepositorio = new VeiculoRepositorio(em);
            FreteRepositorio freteRepositorio = new FreteRepositorio(em);

            // Persistindo todas as entidades usando os repositórios
            clienteRepositorio.salvarOuAtualizar(cliente);
            funcionarioRepositorio.salvarOuAtualizar(funcionario);
            cidadeRepositorio.salvarOuAtualizar(cidadeOrigem);
            cidadeRepositorio.salvarOuAtualizar(cidadeDestino);
            distanciaRepositorio.salvarOuAtualizar(distancia);
            categoriaFreteRepositorio.salvaOuAtualiza(categoria);
            veiculoRepositorio.salvarOuAtualizar(veiculo);
            freteRepositorio.salvarOuAtualizar(frete);

            // Registrar frete
            freteService.registrarFrete(frete);
            System.out.println("Frete registrado com sucesso!");

            // Buscar frete por ID
            Frete freteEncontrado = freteService.buscarFretePorId(frete.getId());
            Double valorFrete = freteService.getValorFrete();
            System.out.println(" Frete encontrado: " + freteEncontrado + ", valor: " + valorFrete);

            // Listar fretes de um cliente
            List<Frete> fretesCliente = freteService.listarFretesPorCliente(cliente.getId());
            System.out.println("Frete encontrado: " + freteEncontrado.getCodigo() + ", valor: " + valorFrete);
            System.out.println("Frete registrado com sucesso!");
            System.out.println(" Total de fretes do cliente: " + fretesCliente.size());

        } catch (Exception e) {
            System.err.println(" Erro no teste: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}