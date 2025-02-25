package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String estado;
}

