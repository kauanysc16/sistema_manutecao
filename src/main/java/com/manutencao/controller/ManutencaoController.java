package com.manutencao.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; // Importa a interface List para uso de listas

import com.manutencao.connection.ManutencaoDAO; // Importa a classe ManutencaoDAO que gerencia a persistência de Manutencao
import com.manutencao.model.Manutencao; // Importa a classe Manutencao que representa a entidade de manutenção

public class ManutencaoController {
    private ManutencaoDAO manutencaoDAO; // Atributo para acesso ao DAO

    // Construtor que inicializa o DAO
    public ManutencaoController() {
        manutencaoDAO = new ManutencaoDAO(); // Inicializa o ManutencaoDAO
    }

    // Método para salvar uma nova manutenção
    public void salvarManutencao(Manutencao manutencao) {
        try {
            validarManutencao(manutencao);
            manutencaoDAO.salvar(manutencao);
            System.out.println("Manutenção salva com sucesso.");
        } catch (SQLException e) { // Trata a SQLException
            System.err.println("Erro ao salvar manutenção: " + e.getMessage());
        } catch (IllegalArgumentException e) { // Captura erros de validação
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }

    // Método para atualizar uma manutenção existente
    public void atualizarManutencao(Manutencao manutencao) {
        try {
            validarManutencao(manutencao); // Valida os dados da manutenção
            manutencaoDAO.atualizar(manutencao); // Atualiza a manutenção no banco de dados
            System.out.println("Manutenção atualizada com sucesso.");
        } catch (SQLException e) { // Trata a SQLException
            System.err.println("Erro ao atualizar manutenção: " + e.getMessage());
        } catch (IllegalArgumentException e) { // Captura erros de validação
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }

    // Método para excluir uma manutenção pelo ID
    public void excluirManutencao(int id) {
        try {
            manutencaoDAO.deletar(id); // Deleta a manutenção pelo ID
            System.out.println("Manutenção excluída com sucesso.");
        } catch (SQLException e) { // Trata a SQLException
            System.err.println("Erro ao excluir manutenção: " + e.getMessage());
        } catch (Exception e) { // Captura exceções gerais
            System.err.println("Erro inesperado ao excluir manutenção: " + e.getMessage());
        }
    }

    // Método para buscar uma manutenção pelo ID
    public Manutencao buscarManutencaoPorId(int id) {
        try {
            return manutencaoDAO.buscarPorId(id); // Busca a manutenção pelo ID
        } catch (SQLException e) { // Trata a SQLException
            System.err.println("Erro ao buscar manutenção: " + e.getMessage());
            return null; // Retorna null em caso de erro
        }
    }

    // Método para listar todas as manutenções
public List<Manutencao> listarManutencoes() {
    try {
        return manutencaoDAO.listarTodos(); // Lista todas as manutenções
    } catch (SQLException e) { // Trata a SQLException
        System.err.println("Erro ao listar manutenções: " + e.getMessage());
        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
    }
}

    // Método para marcar uma manutenção como concluída
    public void marcarComoConcluida(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id); // Busca a manutenção pelo ID
        if (manutencao != null) {
            manutencao.setStatus("Concluída"); // Atualiza o status para 'Concluída'
            atualizarManutencao(manutencao); // Atualiza no banco
        }
    }

    // Método para marcar uma manutenção como pendente
    public void marcarComoPendente(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id); // Busca a manutenção pelo ID
        if (manutencao != null) {
            manutencao.setStatus("Pendente"); // Atualiza o status para 'Pendente'
            atualizarManutencao(manutencao); // Atualiza no banco
        }
    }

    // Método para buscar manutenções por ID do equipamento
public List<Manutencao> buscarManutencoesPoridEquipamento(int idEquipamento) {
    try {
        return manutencaoDAO.listarPorEquipamento(idEquipamento); // Lista manutenções por ID do equipamento
    } catch (SQLException e) { // Trata a SQLException
        System.err.println("Erro ao buscar manutenções por ID do equipamento: " + e.getMessage());
        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
    }
}

    // Método para gerar um relatório de manutenções
    public void gerarRelatorioManutencoes() {
        List<Manutencao> manutencoes = listarManutencoes(); // Obtém todas as manutenções
        // Exemplo de como gerar um relatório (deve ser implementado)
        if (manutencoes.isEmpty()) {
            System.out.println("Nenhuma manutenção encontrada para gerar relatório.");
            return; // Retorna se não houver manutenções
        }
        // Implementação da geração do relatório deve ser adicionada aqui
    }

    // Método privado para validar os dados de uma manutenção
    private void validarManutencao(Manutencao manutencao) {
        // Valida se o ID do equipamento é positivo
        if (manutencao.getIdEquipamento() <= 0) {
            throw new IllegalArgumentException("ID do equipamento deve ser um número positivo.");
        }
        // Valida se a descrição não é nula ou vazia
        if (manutencao.getDescricao() == null || manutencao.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        // Valida se o tipo de manutenção não é nulo ou vazio
        if (manutencao.getTipo() == null || manutencao.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de manutenção não pode estar vazio.");
        }
        // Valida se a data da manutenção não é nula
        if (manutencao.getDataManutencao() == null) {
            throw new IllegalArgumentException("A data da manutenção não pode ser nula.");
        }
        // Valida se o status não é nulo ou vazio
        if (manutencao.getStatus() == null || manutencao.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("O status não pode estar vazio.");
        }
    }
}
