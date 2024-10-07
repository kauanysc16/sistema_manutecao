package com.manutencao.controller;

import java.util.List;

import com.manutencao.connection.ManutencaoDAO;
import com.manutencao.model.Manutencao;

public class ManutencaoController {
    private ManutencaoDAO manutencaoDAO;

    public ManutencaoController() {
        manutencaoDAO = new ManutencaoDAO();
    }

    public void salvarManutencao(Manutencao manutencao) {
        manutencaoDAO.salvar(manutencao);
    }

    public void atualizarManutencao(Manutencao manutencao) {
        manutencaoDAO.atualizar(manutencao);
    }

    public void excluirManutencao(int id) {
        manutencaoDAO.deletar(id);
    }

    public Manutencao buscarManutencaoPorId(int id) {
        return manutencaoDAO.buscarPorId(id);
    }

    public List<Manutencao> listarManutencoes() {
        return manutencaoDAO.listarTodos();
    }

    public void marcarComoConcluida(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id);
        if (manutencao != null) {
            manutencao.setStatus("Concluída");
            atualizarManutencao(manutencao);
        }
    }

    public void marcarComoPendente(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id);
        if (manutencao != null) {
            manutencao.setStatus("Pendente");
            atualizarManutencao(manutencao);
        }
    }

    public List<Manutencao> buscarManutencoesPorEquipamentoId(int equipamentoId) {
        return manutencaoDAO.listarPorEquipamento(equipamentoId);
    }

    public void gerarRelatorioManutencoes() {
        // Implementação do método gerarRelatorio(), caso não esteja no ManutencaoDAO
        List<Manutencao> manutencoes = listarManutencoes();
        // Lógica para gerar o relatório, como exportação para PDF ou exibição em tela
    }
}
