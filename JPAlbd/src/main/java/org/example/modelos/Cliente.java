package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cliente extends PessoaFisica {
    private String contato;
    private boolean ativo;
    private String endereco;
}
