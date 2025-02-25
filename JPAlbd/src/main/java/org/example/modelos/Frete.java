package org.example.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

@Data
@Entity
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroNotaFiscal;
    private BigDecimal valorKmRodado;

    @NotNull
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "cidade_origem_id", nullable = false)
    private Cidade cidadeOrigem;

    @ManyToOne
    @JoinColumn(name = "cidade_destino_id", nullable = false)
    private Cidade cidadeDestino;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "categoria_frete_id", nullable = false)
    private CategoriaFrete categoriaFrete;

    public BigDecimal calcularFrete(Distancia distancia) {
        BigDecimal percentual = BigDecimal.ONE.add(
                BigDecimal.valueOf(categoriaFrete.getPercentualAdicional()).divide(BigDecimal.valueOf(100))
        );
        return valorKmRodado.multiply(BigDecimal.valueOf(distancia.getQuilometros())).multiply(percentual);
    }

}

