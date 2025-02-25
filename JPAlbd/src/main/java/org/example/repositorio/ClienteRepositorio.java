package org.example.repositorio;

import jakarta.persistence.EntityManager;
import org.example.modelos.Cliente;

public class ClienteRepositorio extends DAOGenerico<Cliente> {
    public ClienteRepositorio(EntityManager manager) {
        super(manager, Cliente.class);
    }
}
