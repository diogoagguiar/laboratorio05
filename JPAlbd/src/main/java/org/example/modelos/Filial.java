package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String endereco;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "funcionario_responsavel_id")
    private Funcionario funcionarioResponsavel;

    @OneToMany(mappedBy = "filial")
    private List<Veiculo> veiculos;

}

