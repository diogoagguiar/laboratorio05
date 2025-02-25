package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class PessoaFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
}
