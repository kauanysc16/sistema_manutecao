package com.manutencao.view; // Pacote onde a classe TelaListaManutencao está localizada

import java.time.LocalDateTime; // Importa a classe para manipulação de datas e horas
import java.time.format.DateTimeFormatter; // Importa a classe para formatar datas
import java.util.List; // Importa a classe List para trabalhar com listas

import javax.swing.JButton; // Importa a classe para criar botões
import javax.swing.JFrame; // Importa a classe para criar a janela principal
import javax.swing.JOptionPane; // Importa a classe para mostrar diálogos
import javax.swing.JScrollPane; // Importa a classe para criar barras de rolagem
import javax.swing.JTable; // Importa a classe para criar tabelas
import javax.swing.SwingUtilities; // Importa a classe para manipular a interface gráfica
import javax.swing.table.DefaultTableModel; // Importa a classe para manipulação de modelos de tabelas

import com.manutencao.controller.ManutencaoController; // Importa o controlador para gerenciar manutenções
import com.manutencao.model.Manutencao; // Importa a classe modelo de manutenção

public class TelaListaManutencao extends JFrame {
    private JTable table; // Declara a tabela que exibirá as manutenções
    private DefaultTableModel tableModel; // Modelo da tabela para manipulação de dados
    private ManutencaoController manutencaoController; // Controlador para gerenciar operações de manutenção

    public TelaListaManutencao() { // Construtor da classe
        manutencaoController = new ManutencaoController(); // Instancia o controlador de manutenção

        setTitle("Lista de Manutenções"); // Define o título da janela
        setSize(600, 400); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout da janela como nulo para permitir posicionamento manual

        // Configuração da tabela
        tableModel = new DefaultTableModel(
                new String[] { "ID", "ID Técnico", "ID Equipamento", "Data", "Descrição", "Status" }, 0);
        table = new JTable(tableModel); // Cria a tabela com o modelo definido
        JScrollPane scrollPane = new JScrollPane(table); // Adiciona uma barra de rolagem à tabela
        scrollPane.setBounds(10, 10, 560, 300); // Define a posição e tamanho da barra de rolagem
        add(scrollPane); // Adiciona a barra de rolagem à janela

        // Ajustando a largura das colunas
        for (int i = 0; i < 6; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(i == 0 ? 50 : (i == 3 ? 150 : 100)); // Define
                                                                                                       // larguras
                                                                                                       // específicas
                                                                                                       // para as
                                                                                                       // colunas
        }

        // Criação dos botões para manipulação das manutenções
        JButton btnConcluir = new JButton("Concluída"); // Botão para marcar como concluída
        btnConcluir.setBounds(10, 320, 110, 25); // Define posição e tamanho do botão
        add(btnConcluir); // Adiciona o botão à janela

        JButton btnPendente = new JButton("Pendente"); // Botão para marcar como pendente
        btnPendente.setBounds(130, 320, 110, 25); // Define posição e tamanho do botão
        add(btnPendente); // Adiciona o botão à janela

        JButton btnExcluir = new JButton("Excluir"); // Botão para excluir uma manutenção
        btnExcluir.setBounds(250, 320, 110, 25); // Define posição e tamanho do botão
        add(btnExcluir); // Adiciona o botão à janela

        JButton btnAtualizar = new JButton("Atualizar"); // Botão para atualizar uma manutenção
        btnAtualizar.setBounds(370, 320, 110, 25); // Define posição e tamanho do botão
        add(btnAtualizar); // Adiciona o botão à janela

        // Ações dos botões
        btnConcluir.addActionListener(e -> alterarStatusManutencao("Concluída")); // Ação para marcar como concluída
        btnPendente.addActionListener(e -> alterarStatusManutencao("Pendente")); // Ação para marcar como pendente
        btnExcluir.addActionListener(e -> excluirManutencao()); // Ação para excluir
        btnAtualizar.addActionListener(e -> atualizarManutencao()); // Ação para atualizar

        updateTable(); // Chama o método para atualizar a tabela ao iniciar
    }

    private void alterarStatusManutencao(String novoStatus) { // Método para alterar o status da manutenção
        int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada na tabela
        if (selectedRow >= 0) { // Verifica se uma linha foi selecionada
            int id = (int) tableModel.getValueAt(selectedRow, 0); // Obtém o ID da manutenção selecionada
            if ("Concluída".equals(novoStatus)) {
                manutencaoController.marcarComoConcluida(id); // Marca a manutenção como concluída
            } else {
                manutencaoController.marcarComoPendente(id); // Marca a manutenção como pendente
            }
            updateTable(); // Atualiza a tabela após alteração de status
            JOptionPane.showMessageDialog(null, "Manutenção marcada como " + novoStatus + "!"); // Exibe mensagem de
                                                                                                // sucesso
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma manutenção para marcar."); // Exibe mensagem de erro se
                                                                                          // nenhuma linha for
                                                                                          // selecionada
        }
    }

    private void excluirManutencao() { // Método para excluir uma manutenção
        int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada na tabela
        if (selectedRow >= 0) { // Verifica se uma linha foi selecionada
            int id = (int) tableModel.getValueAt(selectedRow, 0); // Obtém o ID da manutenção selecionada
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta manutenção?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION); // Confirmação da exclusão
            if (confirm == JOptionPane.YES_OPTION) {
                manutencaoController.excluirManutencao(id); // Exclui a manutenção
                updateTable(); // Atualiza a tabela após exclusão
                JOptionPane.showMessageDialog(null, "Manutenção excluída com sucesso!"); // Exibe mensagem de sucesso
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma manutenção para excluir."); // Exibe mensagem de erro se
                                                                                           // nenhuma linha for
                                                                                           // selecionada
        }
    }

    public void atualizarManutencao() { // Método para atualizar os dados de uma manutenção
        int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada na tabela
        if (selectedRow >= 0) { // Verifica se uma linha foi selecionada
            int id = (int) tableModel.getValueAt(selectedRow, 0); // Obtém o ID da manutenção selecionada
            // Solicita novos dados ao usuário
            String idTecnico = JOptionPane.showInputDialog("Informe o novo ID do Técnico:");
            String idEquipamento = JOptionPane.showInputDialog("Informe o novo ID do Equipamento:");
            String data = JOptionPane.showInputDialog("Informe a nova data (YYYY-MM-DD HH:mm):");
            String descricao = JOptionPane.showInputDialog("Informe a nova descrição:");
            String status = JOptionPane.showInputDialog("Informe o novo status:");

            // Verifica se todos os campos foram preenchidos
            if (idTecnico != null && idEquipamento != null && data != null && descricao != null && status != null) {
                try {
                    LocalDateTime dataFormatada = LocalDateTime.parse(data,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // Formata a nova data
                    Manutencao manutencao = new Manutencao(id, Integer.parseInt(idTecnico),
                            Integer.parseInt(idEquipamento), dataFormatada, descricao, status); // Cria um novo objeto
                                                                                                // Manutencao
                    manutencaoController.atualizarManutencao(manutencao); // Atualiza a manutenção
                    updateTable(); // Atualiza a tabela após atualização
                    JOptionPane.showMessageDialog(null, "Manutenção atualizada com sucesso!"); // Exibe mensagem de
                                                                                               // sucesso
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "IDs devem ser números válidos."); // Mensagem de erro se os IDs
                                                                                           // não forem válidos
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato YYYY-MM-DD HH:mm."); // Mensagem
                                                                                                                      // de
                                                                                                                      // erro
                                                                                                                      // se
                                                                                                                      // o
                                                                                                                      // formato
                                                                                                                      // da
                                                                                                                      // data
                                                                                                                      // for
                                                                                                                      // inválido
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos."); // Mensagem de erro se
                                                                                               // algum campo não for
                                                                                               // preenchido
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma manutenção para atualizar."); // Exibe mensagem de erro
                                                                                             // se nenhuma linha for
                                                                                             // selecionada
        }
    }

    private void updateTable() { // Método para atualizar os dados da tabela
        tableModel.setRowCount(0); // Limpa a tabela antes de atualizar
        List<Manutencao> manutencoes = manutencaoController.listarManutencoes(); // Obtém a lista de manutenções
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Define o formato de data
        // Adiciona cada manutenção à tabela
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[] {
                    manutencao.getId(), // ID da manutenção
                    manutencao.getIdTecnico(), // ID do técnico
                    manutencao.getIdEquipamento(), // ID do equipamento
                    manutencao.getData().format(formatter), // Data formatada
                    manutencao.getDescricao(), // Descrição da manutenção
                    manutencao.getStatus() // Status da manutenção
            });
        }
    }

    public static void main(String[] args) { // Método principal para executar a aplicação
        SwingUtilities.invokeLater(() -> {
            TelaListaManutencao tela = new TelaListaManutencao(); // Cria uma nova instância da tela
            tela.setVisible(true); // Torna a tela visível
        });
    }
}
