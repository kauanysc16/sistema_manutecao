package com.manutencao.controller;

import java.util.List;

import com.manutencao.connection.TecnicoDAO;
// Corrigido para apontar para o pacote correto
import com.manutencao.model.Tecnico;

public class TecnicoController {
    private TecnicoDAO tecnicoDAO;

    public TecnicoController() {
        tecnicoDAO = new TecnicoDAO();
    }

    public void salvarTecnico(Tecnico tecnico) {
        tecnicoDAO.salvar(tecnico);
    }

    public void atualizarTecnico(Tecnico tecnico) {
        tecnicoDAO.atualizar(tecnico);
    }

    public void deletarTecnico(int id) {
        tecnicoDAO.deletar(id);
    }

    public Tecnico buscarTecnicoPorId(int id) {
        return tecnicoDAO.buscarPorId(id);
    }

    public List<Tecnico> listarTodosTecnicos() {
        return tecnicoDAO.listarTodos();
    }

    // Método adicional para buscar técnicos por especialidade
    public List<Tecnico> buscarTecnicosPorEspecialidade(String especialidade) {
        return tecnicoDAO.buscarPorEspecialidade(especialidade);
    }

    // Método adicional para verificar disponibilidade dos técnicos
    public List<Tecnico> verificarDisponibilidade() {
        return tecnicoDAO.verificarDisponibilidade();
    }
}
