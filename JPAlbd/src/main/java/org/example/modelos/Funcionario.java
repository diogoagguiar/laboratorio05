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
public class Funcionario extends PessoaFisica {
    private int matricula;

    @OneToMany(mappedBy = "funcionario")
    private List<Frete> fretes;
}
