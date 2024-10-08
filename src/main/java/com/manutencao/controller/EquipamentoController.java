package com.manutencao.controller;

import java.util.List;

import com.manutencao.connection.EquipamentoDAO;
import com.manutencao.model.Equipamento;

public class EquipamentoController {
    private EquipamentoDAO equipamentoDAO;

    public EquipamentoController() {
        this.equipamentoDAO = new EquipamentoDAO();
    }

    public boolean salvarEquipamento(Equipamento equipamento) {
        try {
            equipamentoDAO.salvar(equipamento);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarEquipamento(Equipamento equipamento) {
        try {
            equipamentoDAO.atualizar(equipamento);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletarEquipamento(int id) {
        try {
            equipamentoDAO.deletar(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Equipamento buscarEquipamentoPorId(int id) {
        try {
            return equipamentoDAO.buscarPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Equipamento> listarTodosEquipamentos() {
        try {
            return equipamentoDAO.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Equipamento> buscarEquipamentoPorLocal(String local) {
        try {
            return equipamentoDAO.buscarPorLocal(local);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
