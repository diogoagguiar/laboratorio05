package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends PessoaFisica {
    private String contato;
    private String endereco;
    private boolean ativo;
}
