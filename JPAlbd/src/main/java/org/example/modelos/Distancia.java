package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Distancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quilometros;

    @ManyToOne
    @JoinColumn(name = "origem_id")
    private Cidade origem;

    @ManyToOne
    @JoinColumn(name = "destino_id")
    private Cidade destino;
}
