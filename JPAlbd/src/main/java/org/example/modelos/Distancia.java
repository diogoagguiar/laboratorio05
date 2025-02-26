package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Distancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double quilometros;

    @ManyToOne
    @JoinColumn(name = "origem_id")
    private Cidade origem;

    @ManyToOne
    @JoinColumn(name = "destino_id")
    private Cidade destino;
}
