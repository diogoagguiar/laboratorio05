package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numeroPlaca;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "tipo_veiculo_id")
    private TipoVeiculo tipoVeiculo;
}
