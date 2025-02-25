package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Funcionario extends PessoaFisica {
    private int matricula;

    @OneToMany(mappedBy = "funcionario")
    private List<Frete> fretes;
}
