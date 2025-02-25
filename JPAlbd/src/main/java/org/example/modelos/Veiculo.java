package org.example.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Veiculo {
    @Id
    private String numeroPlaca;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "tipo_veiculo_id")
    private TipoVeiculo tipoVeiculo;
}
