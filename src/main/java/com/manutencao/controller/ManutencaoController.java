package com.manutencao.controller;

import java.util.List;

import com.manutencao.connection.ManutencaoDAO;
import com.manutencao.model.Manutencao;

public class ManutencaoController {
    private ManutencaoDAO manutencaoDAO; // Atributo para acesso ao DAO

    public ManutencaoController() {
        manutencaoDAO = new ManutencaoDAO(); // Inicializa o DAO
    }

    public void salvarManutencao(Manutencao manutencao) {
        try {
            validarManutencao(manutencao); // Valida os dados da manutenção
            manutencaoDAO.salvar(manutencao); // Salva a manutenção no banco de dados
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao salvar manutenção: " + e.getMessage());
            // Aqui você pode usar um Logger em vez de System.err para registrar erros
        }
    }

    public void atualizarManutencao(Manutencao manutencao) {
        try {
            validarManutencao(manutencao); // Valida os dados da manutenção
            manutencaoDAO.atualizar(manutencao); // Atualiza a manutenção no banco de dados
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar manutenção: " + e.getMessage());
        }
    }

    public void excluirManutencao(int id) {
        try {
            manutencaoDAO.deletar(id); // Deleta a manutenção pelo ID
        } catch (Exception e) {
            System.err.println("Erro ao excluir manutenção: " + e.getMessage());
        }
    }

    public Manutencao buscarManutencaoPorId(int id) {
        return manutencaoDAO.buscarPorId(id); // Busca a manutenção pelo ID
    }

    public List<Manutencao> listarManutencoes() {
        return manutencaoDAO.listarTodos(); // Lista todas as manutenções
    }

    public void marcarComoConcluida(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id);
        if (manutencao != null) {
            manutencao.setStatus("Concluída"); // Atualiza o status para 'Concluída'
            atualizarManutencao(manutencao); // Atualiza no banco
        }
    }

    public void marcarComoPendente(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id);
        if (manutencao != null) {
            manutencao.setStatus("Pendente"); // Atualiza o status para 'Pendente'
            atualizarManutencao(manutencao); // Atualiza no banco
        }
    }

    public List<Manutencao> buscarManutencoesPorEquipamentoId(int equipamentoId) {
        return manutencaoDAO.listarPorEquipamento(equipamentoId); // Lista manutenções por ID do equipamento
    }

    public void gerarRelatorioManutencoes() {
        List<Manutencao> manutencoes = listarManutencoes(); // Obtém todas as manutenções
        // Exemplo de como gerar um relatório (deve ser implementado):
        if (manutencoes.isEmpty()) {
            System.out.println("Nenhuma manutenção encontrada para gerar relatório.");
            return;
        }

    }

    private void validarManutencao(Manutencao manutencao) {
        if (manutencao.getIdEquipamento() <= 0) {
            throw new IllegalArgumentException("ID do equipamento deve ser um número positivo.");
        }
        if (manutencao.getIdTecnico() <= 0) {
            throw new IllegalArgumentException("ID do técnico deve ser um número positivo.");
        }
        if (manutencao.getDescricao() == null || manutencao.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        if (manutencao.getTipo() == null || manutencao.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de manutenção não pode estar vazio.");
        }
        if (manutencao.getData() == null) {
            throw new IllegalArgumentException("A data da manutenção não pode ser nula.");
        }
        if (manutencao.getStatus() == null || manutencao.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("O status não pode estar vazio.");
        }
        // Adicione mais validações conforme necessário
    }
}
