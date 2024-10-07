package com.manutencao.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.manutencao.controller.ManutencaoController;
import com.manutencao.model.Manutencao;

public class TelaCadastroManutencao extends JFrame {
    private JTextField txtIdTecnico; // Campo de texto para o ID do técnico
    private JTextField txtIdEquipamento; // Campo de texto para o ID do equipamento
    private JTextField txtData; // Campo de texto para a data da manutenção
    private JTextArea txtDescricao; // Área de texto para a descrição da manutenção
    private JButton btnSalvar; // Botão para salvar a manutenção
    private JButton btnCancelar; // Botão para cancelar a operação
    private ManutencaoController manutencaoController; // Controlador de manutenções

    public TelaCadastroManutencao() {
        manutencaoController = new ManutencaoController(); // Instancia o controlador

        // Configurações da janela
        setTitle("Cadastro de Manutenção"); // Define o título da janela
        setSize(400, 300); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout como nulo (posicionamento manual)

        // Criação e adição de rótulos e campos de texto
        JLabel lblIdTecnico = new JLabel("ID Técnico:"); // Rótulo para o ID do técnico
        lblIdTecnico.setBounds(10, 10, 80, 25); // Define a posição e tamanho do rótulo
        add(lblIdTecnico); // Adiciona o rótulo à janela

        txtIdTecnico = new JTextField(); // Cria o campo de texto para o ID do técnico
        txtIdTecnico.setBounds(100, 10, 160, 25); // Define a posição e tamanho do campo
        add(txtIdTecnico); // Adiciona o campo à janela

        JLabel lblIdEquipamento = new JLabel("ID Equipamento:"); // Rótulo para o ID do equipamento
        lblIdEquipamento.setBounds(10, 40, 120, 25); // Define a posição e tamanho do rótulo
        add(lblIdEquipamento); // Adiciona o rótulo à janela

        txtIdEquipamento = new JTextField(); // Cria o campo de texto para o ID do equipamento
        txtIdEquipamento.setBounds(140, 40, 120, 25); // Define a posição e tamanho do campo
        add(txtIdEquipamento); // Adiciona o campo à janela

        JLabel lblData = new JLabel("Data (yyyy-MM-dd HH:mm):"); // Rótulo para a data
        lblData.setBounds(10, 70, 200, 25); // Define a posição e tamanho do rótulo
        add(lblData); // Adiciona o rótulo à janela

        txtData = new JTextField(); // Cria o campo de texto para a data
        txtData.setBounds(210, 70, 160, 25); // Define a posição e tamanho do campo
        add(txtData); // Adiciona o campo à janela

        JLabel lblDescricao = new JLabel("Descrição:"); // Rótulo para a descrição
        lblDescricao.setBounds(10, 100, 80, 25); // Define a posição e tamanho do rótulo
        add(lblDescricao); // Adiciona o rótulo à janela

        txtDescricao = new JTextArea(); // Cria a área de texto para a descrição
        txtDescricao.setBounds(10, 130, 360, 80); // Define a posição e tamanho da área
        add(txtDescricao); // Adiciona a área à janela

        // Criação e adição do botão de salvar
        btnSalvar = new JButton("Salvar"); // Cria o botão de salvar
        btnSalvar.setBounds(10, 220, 80, 25); // Define a posição e tamanho do botão
        add(btnSalvar); // Adiciona o botão à janela

        // Criação e adição do botão de cancelar
        btnCancelar = new JButton("Cancelar"); // Cria o botão de cancelar
        btnCancelar.setBounds(280, 220, 100, 25); // Define a posição e tamanho do botão
        add(btnCancelar); // Adiciona o botão à janela

        // Evento para o botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação dos campos
                if (txtIdTecnico.getText().trim().isEmpty() || txtIdEquipamento.getText().trim().isEmpty()
                        || txtData.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro",
                            JOptionPane.ERROR_MESSAGE); // Mensagem de erro
                    return; // Retorna se os campos não estiverem preenchidos
                }

                try {
                    // Obtém e valida os dados inseridos
                    int idTecnico = Integer.parseInt(txtIdTecnico.getText()); // Obtém o ID do técnico
                    int idEquipamento = Integer.parseInt(txtIdEquipamento.getText()); // Obtém o ID do equipamento
                    LocalDateTime data = LocalDateTime.parse(txtData.getText(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // Valida a data
                    String descricao = txtDescricao.getText(); // Obtém a descrição
                    String status = "Pendente"; // Define o status inicial

                    // Cria um novo objeto Manutencao
                    Manutencao manutencao = new Manutencao(0, idTecnico, idEquipamento, data, descricao, status); // Cria
                                                                                                                  // a
                                                                                                                  // manutenção
                    manutencaoController.salvarManutencao(manutencao); // Salva a manutenção
                    JOptionPane.showMessageDialog(null, "Manutenção salva com sucesso!"); // Mensagem de sucesso

                    // Limpa os campos após o sucesso
                    txtIdTecnico.setText(""); // Limpa o campo de ID do técnico
                    txtIdEquipamento.setText(""); // Limpa o campo de ID do equipamento
                    txtData.setText(""); // Limpa o campo de data
                    txtDescricao.setText(""); // Limpa a área de descrição
                } catch (NumberFormatException ex) {
                    // Trata exceções de formatação de número
                    JOptionPane.showMessageDialog(null, "Por favor, insira IDs válidos.", "Erro",
                            JOptionPane.ERROR_MESSAGE); // Mensagem de erro
                } catch (DateTimeParseException ex) {
                    // Trata exceções de formatação de data
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use yyyy-MM-dd HH:mm.", "Erro",
                            JOptionPane.ERROR_MESSAGE); // Mensagem de erro
                } catch (Exception ex) {
                    // Trata outras exceções
                    JOptionPane.showMessageDialog(null, "Erro ao salvar a manutenção: " + ex.getMessage(), "Erro",
                            JOptionPane.ERROR_MESSAGE); // Mensagem de erro
                }
            }
        });

        // Evento para o botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastroManutencao tela = new TelaCadastroManutencao(); // Cria uma nova instância da tela
            tela.setVisible(true); // Torna a tela visível
        });
    }
}
