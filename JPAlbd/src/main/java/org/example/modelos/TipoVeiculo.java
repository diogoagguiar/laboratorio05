package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TipoVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private float pesoMaximo;
}

