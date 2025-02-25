package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemFrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private float peso;

    @ManyToOne
    @JoinColumn(name = "frete_id")
    private Frete frete;
}
