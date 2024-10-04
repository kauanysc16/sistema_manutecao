package com.manutencao.controller; // Pacote onde a classe EquipamentoController está localizada

import java.util.List; // Importa a classe List da biblioteca java.util

import com.manutencao.connection.EquipamentoDAO; // Importa a classe EquipamentoDAO para operações de banco de dados
import com.manutencao.model.Equipamento; // Importa a classe Equipamento do modelo

public class EquipamentoController {
    private EquipamentoDAO equipamentoDAO; // Instância de EquipamentoDAO para gerenciar operações de banco de dados

    // Construtor da classe EquipamentoController
    public EquipamentoController() {
        equipamentoDAO = new EquipamentoDAO(); // Inicializa a EquipamentoDAO
    }

    // Método para salvar um novo equipamento
    public void salvarEquipamento(Equipamento equipamento) {
        equipamentoDAO.salvar(equipamento); // Chama o método salvar da EquipamentoDAO
    }

    // Método para atualizar um equipamento existente
    public void atualizarEquipamento(Equipamento equipamento) {
        equipamentoDAO.atualizar(equipamento); // Chama o método atualizar da EquipamentoDAO
    }

    // Método para deletar um equipamento pelo ID
    public void deletarEquipamento(int id) {
        equipamentoDAO.deletar(id); // Chama o método deletar da EquipamentoDAO
    }

    // Método para buscar um equipamento pelo ID
    public Equipamento buscarEquipamentoPorId(int id) {
        return equipamentoDAO.buscarPorId(id); // Chama o método buscarPorId da EquipamentoDAO
    }

    // Método para listar todos os equipamentos
    public List<Equipamento> listarTodosEquipamentos() {
        return equipamentoDAO.listarTodos(); // Chama o método listarTodos da EquipamentoDAO
    }
}
