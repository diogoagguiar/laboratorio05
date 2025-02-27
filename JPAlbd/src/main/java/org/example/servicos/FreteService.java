package org.example.servicos;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.modelos.*;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FreteService {
    private final EntityManager em;
    private Double valorFrete;

    public FreteService(EntityManager em) {
        this.em = em;
    }

    public void registrarFrete(Frete frete) {
        try {
            em.getTransaction().begin();

            // Buscar a distância entre as cidades de origem e destino
            Distancia distancia = em.createQuery(
                            "SELECT d FROM Distancia d WHERE d.origem = :origem AND d.destino = :destino",
                            Distancia.class)
                    .setParameter("origem", frete.getCidadeDeOrigem())  // Alterado para cidadeDeOrigem
                    .setParameter("destino", frete.getCidadeDeDestino()) // Alterado para cidadeDeDestino
                    .getSingleResult();

            // Verifica se a distância foi encontrada
            if (distancia == null) {
                throw new RuntimeException("Nenhuma distância encontrada entre as cidades informadas.");
            }

            // Calcular o valor do frete
            valorFrete = calcularValorFrete(frete.getValorKmRodado(), distancia, frete.getCategoriaFrete());

            // Persistir o frete atualizado com o valor calculado
            frete.setValorKmRodado(valorFrete); // Salvar o valor calculado no frete
            em.persist(frete);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao registrar frete: " + e.getMessage(), e);
        }
    }

    public List<Frete> listarFretesPorCliente(Integer clienteId) {
        return em.createQuery("SELECT f FROM Frete f WHERE f.cliente.id = :clienteId", Frete.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    public Frete buscarFretePorId(Integer id) {
        return em.find(Frete.class, id);
    }

    private Double calcularValorFrete(Double valorKmRodado, Distancia distancia, CategoriaFrete categoriaFrete) {
        if (categoriaFrete == null || valorKmRodado == null || distancia == null) {
            throw new IllegalArgumentException("Dados insuficientes para calcular o frete.");
        }

        BigDecimal percentual = BigDecimal.ONE.add(
                BigDecimal.valueOf(categoriaFrete.getPercentualAdicional()).divide(BigDecimal.valueOf(100))
        );

        return valorKmRodado * distancia.getQuilometros() * percentual.doubleValue();
    }

    public Double getValorFrete() {
        return valorFrete;
    }
}
