package org.example.repositorio;


import jakarta.persistence.EntityManager;
import org.example.modelos.Funcionario;
import java.util.List;

public class FuncionarioRepositorio {

    private final DAOGenerico<Funcionario> dao;

    public FuncionarioRepositorio(EntityManager manager) {
        this.dao = new DAOGenerico<>(manager, Funcionario.class);
    }

    public Funcionario buscarPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public List<Funcionario> buscarTodos() {
        return dao.buscaTodos();
    }

    public Funcionario salvarOuAtualizar(Funcionario funcionario) {
        return dao.salvaOuAtualiza(funcionario);
    }

    public void remover(Funcionario funcionario) {
        dao.remove(funcionario);
    }
}
