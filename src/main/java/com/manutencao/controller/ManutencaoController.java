package com.manutencao.controller; // Pacote onde a classe ManutencaoController está localizada

import java.util.List; // Importa a classe List da biblioteca java.util

import com.manutencao.connection.ManutencaoDAO; // Importa a classe ManutencaoDAO para operações de banco de dados
import com.manutencao.model.Manutencao; // Importa a classe Manutencao do modelo

public class ManutencaoController {
    private ManutencaoDAO manutencaoDAO; // Instância de ManutencaoDAO para gerenciar operações de banco de dados

    // Construtor da classe ManutencaoController
    public ManutencaoController() {
        manutencaoDAO = new ManutencaoDAO(); // Inicializa a ManutencaoDAO
    }

    // Método para salvar uma nova manutenção
    public void salvarManutencao(Manutencao manutencao) {
        manutencaoDAO.salvar(manutencao); // Chama o método salvar da ManutencaoDAO
    }

    // Método para atualizar uma manutenção existente
    public void atualizarManutencao(Manutencao manutencao) {
        manutencaoDAO.atualizar(manutencao); // Chama o método atualizar da ManutencaoDAO
    }

    // Método para excluir uma manutenção pelo ID
    public void excluirManutencao(int id) {
        manutencaoDAO.deletar(id); // Chama o método deletar da ManutencaoDAO
    }

    // Método para buscar uma manutenção pelo ID
    public Manutencao buscarManutencaoPorId(int id) {
        return manutencaoDAO.buscarPorId(id); // Chama o método buscarPorId da ManutencaoDAO
    }

    // Método para listar todas as manutenções
    public List<Manutencao> listarManutencoes() {
        return manutencaoDAO.listarTodos(); // Chama o método listarTodos da ManutencaoDAO
    }

    // Método para marcar uma manutenção como concluída
    public void marcarComoConcluida(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id); // Busca a manutenção pelo ID
        if (manutencao != null) { // Verifica se a manutenção existe
            manutencao.setStatus("Concluída"); // Define o status como "Concluída"
            atualizarManutencao(manutencao); // Atualiza a manutenção no banco de dados
        }
    }
    
    // Método para marcar uma manutenção como pendente
    public void marcarComoPendente(int id) {
        Manutencao manutencao = buscarManutencaoPorId(id); // Busca a manutenção pelo ID
        if (manutencao != null) { // Verifica se a manutenção existe
            manutencao.setStatus("Pendente"); // Define o status como "Pendente"
            atualizarManutencao(manutencao); // Atualiza a manutenção no banco de dados
        }
    }
}
