package org.example.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String codigo;

    private String numeroNotaFiscal;
    private Double valorKmRodado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "cidade_origem_id", nullable = false)
    private Cidade cidadeDeOrigem;  // ALTEREI O NOME PARA BATER COM O QUE ESTÁ NO BANCO

    @ManyToOne
    @JoinColumn(name = "cidade_destino_id", nullable = false)
    private Cidade cidadeDeDestino; // ALTEREI O NOME PARA BATER COM O QUE ESTÁ NO BANCO

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "categoria_frete_id", nullable = false)
    private CategoriaFrete categoriaFrete;

    public double calcularFrete(Distancia distancia) {
        if (categoriaFrete == null || valorKmRodado == null || distancia == null) {
            return 0.0;
        }

        double percentualAdicional = categoriaFrete.getPercentualAdicional();
        double percentual = 1.0 + (percentualAdicional / 100.0);

        return valorKmRodado * distancia.getQuilometros() * percentual;
    }
}
