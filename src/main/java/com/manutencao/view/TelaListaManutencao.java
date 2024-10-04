package com.manutencao.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTable table;
    private DefaultTableModel tableModel;
    private ManutencaoController manutencaoController;

    public TelaListaManutencao() {
        manutencaoController = new ManutencaoController();

        setTitle("Lista de Manutenções");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        tableModel = new DefaultTableModel(new String[]{"ID", "ID Técnico", "ID Equipamento", "Data", "Descrição", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 560, 300);
        add(scrollPane);

        // Ajustando a largura das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(300);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);

        // Botões
        JButton btnConcluir = new JButton("Concluída");
        btnConcluir.setBounds(10, 320, 110, 25);
        add(btnConcluir);

        JButton btnPendente = new JButton("Pendente");
        btnPendente.setBounds(130, 320, 110, 25);
        add(btnPendente);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(250, 320, 110, 25);
        add(btnExcluir);
        
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(370, 320, 110, 25);
        add(btnAtualizar);

        // Ação do botão "Concluída"
        btnConcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    manutencaoController.marcarComoConcluida(id); // Método que deve existir em ManutencaoController
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Manutenção marcada como concluída!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma manutenção para marcar.");
                }
            }
        });

        // Ação do botão "Pendente"
        btnPendente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    manutencaoController.marcarComoPendente(id); // Método que deve existir em ManutencaoController
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Manutenção marcada como pendente!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma manutenção para marcar.");
                }
            }
        });

        // Ação do botão "Excluir"
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta manutenção?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        manutencaoController.excluirManutencao(id); // Método que deve existir em ManutencaoController
                        updateTable();
                        JOptionPane.showMessageDialog(null, "Manutenção excluída com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma manutenção para excluir.");
                }
            }
        });

        // Ação do botão "Atualizar"
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String idTecnico = JOptionPane.showInputDialog("Informe o novo ID do Técnico:");
                    String idEquipamento = JOptionPane.showInputDialog("Informe o novo ID do Equipamento:");
                    String data = JOptionPane.showInputDialog("Informe a nova data (YYYY-MM-DD HH:mm):"); // Formato de data
                    String descricao = JOptionPane.showInputDialog("Informe a nova descrição:");
                    String status = JOptionPane.showInputDialog("Informe o novo status:");

                    if (idTecnico != null && idEquipamento != null && data != null && descricao != null && status != null) {
                        Manutencao manutencao = new Manutencao(id, Integer.parseInt(idTecnico), Integer.parseInt(idEquipamento), data, descricao, status);
                        manutencaoController.atualizarManutencao(manutencao); // Método que deve existir em ManutencaoController
                        updateTable();
                        JOptionPane.showMessageDialog(null, "Manutenção atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma manutenção para atualizar.");
                }
            }
        });

        updateTable(); // Atualiza a tabela ao iniciar
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Limpa a tabela antes de atualizar
        List<Manutencao> manutencoes = manutencaoController.listarManutencoes(); // Método que deve existir em ManutencaoController
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[]{
                    manutencao.getId(),
                    manutencao.getIdTecnico(),
                    manutencao.getIdEquipamento(),
                    manutencao.getData(),
                    manutencao.getDescricao(),
                    manutencao.getStatus()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaListaManutencao tela = new TelaListaManutencao();
            tela.setVisible(true);
        });
    }
}
