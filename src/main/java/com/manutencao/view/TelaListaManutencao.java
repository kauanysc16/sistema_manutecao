package com.manutencao.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Importa a classe para manipulação de datas e horas
import java.util.List; // Importa a classe para formatar datas

import javax.swing.JButton; // Importa a classe List para trabalhar com listas
import javax.swing.JFrame; // Importa a classe para criar botões
import javax.swing.JOptionPane; // Importa a classe para criar a janela principal
import javax.swing.JScrollPane; // Importa a classe para mostrar diálogos
import javax.swing.JTable; // Importa a classe para criar barras de rolagem
import javax.swing.SwingUtilities; // Importa a classe para criar tabelas
import javax.swing.table.DefaultTableModel; // Importa a classe para manipular a interface gráfica

import com.manutencao.controller.ManutencaoController; // Importa a classe para manipulação de modelos de tabelas
import com.manutencao.model.Manutencao; // Importa o controlador para gerenciar manutenções

public class TelaListaManutencao extends JFrame {
    private static final int LARGURA_JANELA = 600;
    private static final int ALTURA_JANELA = 400;
    private static final int LARGURA_BOTAO = 110;
    private static final int ALTURA_BOTAO = 25;
    private static final int ESPACO = 10;

    private JTable table; // Declara a tabela que exibirá as manutenções
    private DefaultTableModel tableModel; // Modelo da tabela para manipulação de dados
    private ManutencaoController manutencaoController; // Controlador para gerenciar operações de manutenção

    public TelaListaManutencao() { // Construtor da classe
        manutencaoController = new ManutencaoController(); // Instancia o controlador de manutenção

        setTitle("Lista de Manutenções"); // Define o título da janela
        setSize(LARGURA_JANELA, ALTURA_JANELA); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout da janela como nulo para permitir posicionamento manual

        // Configuração da tabela
        configurarTabela();

        // Criação dos botões para manipulação das manutenções
        criarBotoes();

        updateTable(); // Atualiza a tabela ao iniciar
    }

    private void configurarTabela() {
        tableModel = new DefaultTableModel(
                new String[] { "ID", "ID Técnico", "ID Equipamento", "Data", "Descrição", "Status" }, 0);
        table = new JTable(tableModel); // Cria a tabela com o modelo definido
        JScrollPane scrollPane = new JScrollPane(table); // Adiciona uma barra de rolagem à tabela
        scrollPane.setBounds(ESPACO, ESPACO, LARGURA_JANELA - 2 * ESPACO, ALTURA_JANELA - 100); // Define a posição e
                                                                                                // tamanho da barra de
                                                                                                // rolagem
        add(scrollPane); // Adiciona a barra de rolagem à janela
        ajustarLarguraColunas(); // Ajustando a largura das colunas
    }

    private void ajustarLarguraColunas() {
        for (int i = 0; i < 6; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(i == 0 ? 50 : (i == 3 ? 150 : 100)); // Define
                                                                                                       // larguras
                                                                                                       // específicas
                                                                                                       // para as
                                                                                                       // colunas
        }
    }

    private void criarBotoes() {
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
        JButton botao = new JButton(texto);
        botao.addActionListener(acao);
        return botao;
    }

    private void alterarStatusManutencao(String novoStatus) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            if ("Concluída".equals(novoStatus)) {
                manutencaoController.marcarComoConcluida(id);
            } else if ("Pendente".equals(novoStatus)) {
                manutencaoController.marcarComoPendente(id);
            }
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma manutenção para alterar o status.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirManutencao() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            manutencaoController.excluirManutencao(id);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma manutenção para excluir.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarManutencao() {
        updateTable();
    }

    private void updateTable() {
        List<Manutencao> listaManutencao = manutencaoController.listarManutencoes();
        tableModel.setRowCount(0); // Limpa a tabela antes de adicionar os dados atualizados
        for (Manutencao manutencao : listaManutencao) {
            tableModel
                    .addRow(new Object[] { manutencao.getId(), manutencao.getIdTecnico(), manutencao.getIdEquipamento(),
                            formatarData(manutencao.getData()), manutencao.getDescricao(), manutencao.getStatus() });
        }
    }

    private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaListaManutencao telaListaManutencao = new TelaListaManutencao();
            telaListaManutencao.setVisible(true);
        });
    }
}
