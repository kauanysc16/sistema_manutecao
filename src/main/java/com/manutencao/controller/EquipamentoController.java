package com.manutencao.controller;

import java.util.List;

import com.manutencao.connection.EquipamentoDAO;
import com.manutencao.model.Equipamento;

public class EquipamentoController {
    private EquipamentoDAO equipamentoDAO;

    public EquipamentoController() {
        equipamentoDAO = new EquipamentoDAO();
    }

    public void salvarEquipamento(Equipamento equipamento) {
        equipamentoDAO.salvar(equipamento);
    }

    public void atualizarEquipamento(Equipamento equipamento) {
        equipamentoDAO.atualizar(equipamento);
    }

    public void deletarEquipamento(int id) {
        equipamentoDAO.deletar(id);
    }

    public Equipamento buscarEquipamentoPorId(int id) {
        return equipamentoDAO.buscarPorId(id);
    }

    public List<Equipamento> listarTodosEquipamentos() {
        return equipamentoDAO.listarTodos();
    }

    // Método adicional para buscar equipamento pelo local
    public List<Equipamento> buscarEquipamentoPorLocal(String local) {
        return equipamentoDAO.buscarPorLocal(local);
    }
}
