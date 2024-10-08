package com.manutencao.controller;

import java.util.List; // Importa a interface List para uso de listas

import com.manutencao.connection.EquipamentoDAO; // Importa a classe EquipamentoDAO que gerencia a persistência de Equipamentos
import com.manutencao.model.Equipamento; // Importa a classe Equipamento que representa a entidade de equipamento

public class EquipamentoController {
    private EquipamentoDAO equipamentoDAO; // Declaração do objeto EquipamentoDAO para gerenciar operações com Equipamentos

    // Construtor da classe que inicializa o EquipamentoDAO
    public EquipamentoController() {
        this.equipamentoDAO = new EquipamentoDAO(); // Inicializa o EquipamentoDAO
    }

    // Método para salvar um novo equipamento
    public boolean salvarEquipamento(Equipamento equipamento) {
        try {
            equipamentoDAO.salvar(equipamento); // Tenta salvar o equipamento usando o DAO
            return true; // Retorna true se a operação for bem-sucedida
        } catch (Exception e) { // Trata exceções
            e.printStackTrace(); // Imprime a stack trace para depuração
            return false; // Retorna false se ocorrer um erro
        }
    }

    // Método para atualizar um equipamento existente
    public boolean atualizarEquipamento(Equipamento equipamento) {
        try {
            equipamentoDAO.atualizar(equipamento); // Tenta atualizar o equipamento usando o DAO
            return true; // Retorna true se a operação for bem-sucedida
        } catch (Exception e) { // Trata exceções
            e.printStackTrace(); // Imprime a stack trace para depuração
            return false; // Retorna false se ocorrer um erro
        }
    }

    // Método para deletar um equipamento pelo ID
    public boolean deletarEquipamento(int id) {
        try {
            equipamentoDAO.deletar(id); // Tenta deletar o equipamento usando o DAO
            return true; // Retorna true se a operação for bem-sucedida
        } catch (Exception e) { // Trata exceções
            e.printStackTrace(); // Imprime a stack trace para depuração
            return false; // Retorna false se ocorrer um erro
        }
    }

    // Método para buscar um equipamento pelo ID
    public Equipamento buscarEquipamentoPorId(int id) {
        try {
            return equipamentoDAO.buscarPorId(id); // Tenta buscar o equipamento usando o DAO
        } catch (Exception e) { // Trata exceções
            e.printStackTrace(); // Imprime a stack trace para depuração
            return null; // Retorna null se ocorrer um erro
        }
    }

    // Método para listar todos os equipamentos
    public List<Equipamento> listarTodosEquipamentos() {
        try {
            return equipamentoDAO.listarTodos(); // Tenta listar todos os equipamentos usando o DAO
        } catch (Exception e) { // Trata exceções
            e.printStackTrace(); // Imprime a stack trace para depuração
            return null; // Retorna null se ocorrer um erro
        }
    }

    // Método para buscar equipamentos por local
    public List<Equipamento> buscarEquipamentoPorLocal(String local) {
        try {
            return equipamentoDAO.buscarPorLocal(local); // Tenta buscar equipamentos pelo local usando o DAO
        } catch (Exception e) { // Trata exceções
            e.printStackTrace(); // Imprime a stack trace para depuração
            return null; // Retorna null se ocorrer um erro
        }
    }
}
