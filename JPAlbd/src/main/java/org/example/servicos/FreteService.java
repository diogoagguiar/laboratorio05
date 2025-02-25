package org.example.servicos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.*;

import java.math.BigDecimal;
import java.util.List;

public class FreteService {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public FreteService() {
        this.emf = Persistence.createEntityManagerFactory("transportadoraPU");
        this.em = emf.createEntityManager();
    }

    public void registrarFrete(Frete frete) {
        em.getTransaction().begin();

        // Buscar a distância entre as cidades de origem e destino
        Distancia distancia = em.createQuery(
                        "SELECT d FROM Distancia d WHERE d.origem = :origem AND d.destino = :destino",
                        Distancia.class)
                .setParameter("origem", frete.getCidadeOrigem())
                .setParameter("destino", frete.getCidadeDestino())
                .getSingleResult();

        // Calcular o valor do frete
        BigDecimal valorFrete = calcularValorFrete(frete.getValorKmRodado(), distancia, frete.getCategoriaFrete());
        frete.setValorKmRodado(valorFrete);

        em.persist(frete);
        em.getTransaction().commit();
    }

    public List<Frete> listarFretesPorCliente(Integer clienteId) {
        return em.createQuery("SELECT f FROM Frete f WHERE f.cliente.id = :clienteId", Frete.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    public Frete buscarFretePorId(Long id) {
        return em.find(Frete.class, id);
    }

    private BigDecimal calcularValorFrete(BigDecimal valorKmRodado, Distancia distancia, CategoriaFrete categoriaFrete) {
        BigDecimal percentual = BigDecimal.ONE.add(
                BigDecimal.valueOf(categoriaFrete.getPercentualAdicional()).divide(BigDecimal.valueOf(100))
        );
        return valorKmRodado.multiply(BigDecimal.valueOf(distancia.getQuilometros())).multiply(percentual);
    }

    public void fechar() {
        em.close();
        emf.close();
    }
}

