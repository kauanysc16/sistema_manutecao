package com.manutencao.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.manutencao.controller.ManutencaoController;
import com.manutencao.model.Manutencao;

public class TelaListaManutencao extends JFrame {
    private static final int LARGURA_JANELA = 600;
    private static final int ALTURA_JANELA = 400;
    private static final int LARGURA_BOTAO = 110;
    private static final int ALTURA_BOTAO = 25;
    private static final int ESPACO = 10;

    private JTable table; // Tabela que exibirá as manutenções
    private DefaultTableModel tableModel; // Modelo da tabela para manipulação de dados
    private ManutencaoController manutencaoController; // Controlador para gerenciar operações de manutenção

    // Construtor da classe
    public TelaListaManutencao() {
        manutencaoController = new ManutencaoController(); // Instancia o controlador de manutenção

        setTitle("Lista de Manutenções"); // Define o título da janela
        setSize(LARGURA_JANELA, ALTURA_JANELA); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout da janela como nulo para permitir posicionamento manual

        configurarTabela(); // Configuração da tabela
        criarBotoes(); // Criação dos botões para manipulação das manutenções

        updateTable(); // Atualiza a tabela ao iniciar
    }

    private void configurarTabela() {
        // Configura o modelo da tabela com as colunas necessárias
        tableModel = new DefaultTableModel(new String[]{"ID", "ID Técnico", "ID Equipamento", "Data", "Descrição", "Status"}, 0);
        table = new JTable(tableModel); // Cria a tabela com o modelo definido
        JScrollPane scrollPane = new JScrollPane(table); // Adiciona uma barra de rolagem à tabela
        scrollPane.setBounds(ESPACO, ESPACO, LARGURA_JANELA - 2 * ESPACO, ALTURA_JANELA - 100); // Define a posição e tamanho da barra de rolagem
        add(scrollPane); // Adiciona a barra de rolagem à janela
        ajustarLarguraColunas(); // Ajusta a largura das colunas
    }

    private void ajustarLarguraColunas() {
        // Define larguras específicas para as colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // ID Técnico
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // ID Equipamento
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Data
        table.getColumnModel().getColumn(4).setPreferredWidth(200); // Descrição
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Status
    }

    private void criarBotoes() {
        // Criação dos botões e seus eventos
        JButton btnConcluir = criarBotao("Concluída", e -> alterarStatusManutencao("Concluída"));
        JButton btnPendente = criarBotao("Pendente", e -> alterarStatusManutencao("Pendente"));
        JButton btnExcluir = criarBotao("Excluir", e -> excluirManutencao());
        JButton btnAtualizar = criarBotao("Atualizar", e -> atualizarManutencao());

        // Posiciona os botões na tela
        btnConcluir.setBounds(ESPACO, ALTURA_JANELA - 60, LARGURA_BOTAO, ALTURA_BOTAO);
        btnPendente.setBounds(ESPACO + LARGURA_BOTAO + ESPACO, ALTURA_JANELA - 60, LARGURA_BOTAO, ALTURA_BOTAO);
        btnExcluir.setBounds(ESPACO + 2 * (LARGURA_BOTAO + ESPACO), ALTURA_JANELA - 60, LARGURA_BOTAO, ALTURA_BOTAO);
        btnAtualizar.setBounds(ESPACO + 3 * (LARGURA_BOTAO + ESPACO), ALTURA_JANELA - 60, LARGURA_BOTAO, ALTURA_BOTAO);

        // Adiciona os botões à janela
        add(btnConcluir);
        add(btnPendente);
        add(btnExcluir);
        add(btnAtualizar);
    }

    private JButton criarBotao(String texto, java.awt.event.ActionListener acao) {
        // Cria um botão com o texto e ação especificados
        JButton botao = new JButton(texto);
        botao.addActionListener(acao);
        return botao;
    }

    private void alterarStatusManutencao(String novoStatus) {
        // Altera o status da manutenção selecionada
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            if ("Concluída".equals(novoStatus)) {
                manutencaoController.marcarComoConcluida(id);
            } else if ("Pendente".equals(novoStatus)) {
                manutencaoController.marcarComoPendente(id);
            }
            updateTable(); // Atualiza a tabela
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma manutenção para alterar o status.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirManutencao() {
        // Exclui a manutenção selecionada
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            manutencaoController.excluirManutencao(id);
            updateTable(); // Atualiza a tabela
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma manutenção para excluir.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarManutencao() {
        // Atualiza a tabela com os dados mais recentes
        updateTable();
    }

    private void updateTable() {
        // Atualiza a tabela com a lista de manutenções
        List<Manutencao> listaManutencao = manutencaoController.listarManutencoes();
        tableModel.setRowCount(0); // Limpa a tabela antes de adicionar os dados atualizados
        for (Manutencao manutencao : listaManutencao) {
            tableModel.addRow(new Object[]{
                    manutencao.getId(),
                    manutencao.getIdTecnico(),
                    manutencao.getIdEquipamento(),
                    formatarData(manutencao.getDataManutencao()),
                    manutencao.getDescricao(),
                    manutencao.getStatus()
            });
        }
    }

    private String formatarData(LocalDate data) {
        // Formata a data para o formato "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public static void main(String[] args) {
        // Método principal para executar a aplicação
        SwingUtilities.invokeLater(() -> {
            TelaListaManutencao telaListaManutencao = new TelaListaManutencao();
            telaListaManutencao.setVisible(true); // Torna a janela visível
        });
    }
}
