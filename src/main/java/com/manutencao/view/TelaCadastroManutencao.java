package com.manutencao.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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
    private JTextField txtIdEquipamento; // Campo de texto para o ID do equipamento
    private JTextField txtIdTecnico; // Campo de texto para o ID do técnico
    private JTextField txtTipo; // Campo de texto para o tipo de manutenção
    private JTextField txtData; // Campo de texto para a data da manutenção
    private JTextArea txtDescricao; // Área de texto para a descrição da manutenção
    private JTextField txtPecasSubstituidas; // Campo de texto para as peças substituídas
    private JTextField txtTempoInatividade; // Campo de texto para o tempo de inatividade
    private JButton btnSalvar; // Botão para salvar a manutenção
    private JButton btnCancelar; // Botão para cancelar a operação
    private ManutencaoController manutencaoController; // Controlador de manutenções

    // Construtor da tela de cadastro de manutenção
    public TelaCadastroManutencao() {
        manutencaoController = new ManutencaoController(); // Instancia o controlador

        // Configurações da janela
        setTitle("Cadastro de Manutenção"); // Define o título da janela
        setSize(400, 450); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout como nulo (posicionamento manual)

        // Criação e adição de rótulos e campos de texto
        JLabel lblIdEquipamento = new JLabel("ID Equipamento:"); // Rótulo para o ID do equipamento
        lblIdEquipamento.setBounds(10, 10, 120, 25); // Define a posição e tamanho do rótulo
        add(lblIdEquipamento); // Adiciona o rótulo à janela

        txtIdEquipamento = new JTextField(); // Cria o campo de texto para o ID do equipamento
        txtIdEquipamento.setBounds(140, 10, 160, 25); // Define a posição e tamanho do campo
        add(txtIdEquipamento); // Adiciona o campo à janela

        JLabel lblIdTecnico = new JLabel("ID Técnico:"); // Rótulo para o ID do técnico
        lblIdTecnico.setBounds(10, 40, 120, 25); // Define a posição e tamanho do rótulo
        add(lblIdTecnico); // Adiciona o rótulo à janela

        txtIdTecnico = new JTextField(); // Cria o campo de texto para o ID do técnico
        txtIdTecnico.setBounds(140, 40, 160, 25); // Define a posição e tamanho do campo
        add(txtIdTecnico); // Adiciona o campo à janela

        JLabel lblTipo = new JLabel("Tipo:"); // Rótulo para o tipo
        lblTipo.setBounds(10, 70, 120, 25); // Define a posição e tamanho do rótulo
        add(lblTipo); // Adiciona o rótulo à janela

        txtTipo = new JTextField(); // Cria o campo de texto para o tipo
        txtTipo.setBounds(140, 70, 160, 25); // Define a posição e tamanho do campo
        add(txtTipo); // Adiciona o campo à janela

        JLabel lblData = new JLabel("Data (yyyy-MM-dd):"); // Rótulo para a data
        lblData.setBounds(10, 100, 120, 25); // Define a posição e tamanho do rótulo
        add(lblData); // Adiciona o rótulo à janela

        txtData = new JTextField(); // Cria o campo de texto para a data
        txtData.setBounds(140, 100, 160, 25); // Define a posição e tamanho do campo
        add(txtData); // Adiciona o campo à janela

        JLabel lblDescricao = new JLabel("Descrição:"); // Rótulo para a descrição
        lblDescricao.setBounds(10, 130, 120, 25); // Define a posição e tamanho do rótulo
        add(lblDescricao); // Adiciona o rótulo à janela

        txtDescricao = new JTextArea(); // Cria a área de texto para a descrição
        txtDescricao.setBounds(10, 160, 360, 80); // Define a posição e tamanho da área
        add(txtDescricao); // Adiciona a área à janela

        JLabel lblPecasSubstituidas = new JLabel("Peças Substituídas:"); // Rótulo para as peças substituídas
        lblPecasSubstituidas.setBounds(10, 250, 120, 25); // Define a posição e tamanho do rótulo
        add(lblPecasSubstituidas); // Adiciona o rótulo à janela

        txtPecasSubstituidas = new JTextField(); // Cria o campo de texto para as peças substituídas
        txtPecasSubstituidas.setBounds(140, 250, 160, 25); // Define a posição e tamanho do campo
        add(txtPecasSubstituidas); // Adiciona o campo à janela

        JLabel lblTempoInatividade = new JLabel("Tempo Inatividade:"); // Rótulo para o tempo de inatividade
        lblTempoInatividade.setBounds(10, 280, 120, 25); // Define a posição e tamanho do rótulo
        add(lblTempoInatividade); // Adiciona o rótulo à janela

        txtTempoInatividade = new JTextField(); // Cria o campo de texto para o tempo de inatividade
        txtTempoInatividade.setBounds(140, 280, 160, 25); // Define a posição e tamanho do campo
        add(txtTempoInatividade); // Adiciona o campo à janela

        // Criação e adição do botão de salvar
        btnSalvar = new JButton("Salvar"); // Cria o botão de salvar
        btnSalvar.setBounds(10, 320, 80, 25); // Define a posição e tamanho do botão
        add(btnSalvar); // Adiciona o botão à janela

        // Criação e adição do botão de cancelar
        btnCancelar = new JButton("Cancelar"); // Cria o botão de cancelar
        btnCancelar.setBounds(280, 320, 100, 25); // Define a posição e tamanho do botão
        add(btnCancelar); // Adiciona o botão à janela

        // Evento para o botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação dos campos
                if (txtIdEquipamento.getText().trim().isEmpty() || txtIdTecnico.getText().trim().isEmpty() ||
                        txtTipo.getText().trim().isEmpty() || txtData.getText().trim().isEmpty() ||
                        txtDescricao.getText().trim().isEmpty() || txtPecasSubstituidas.getText().trim().isEmpty() ||
                        txtTempoInatividade.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro",
                            JOptionPane.ERROR_MESSAGE); // Mensagem de erro
                    return; // Retorna se os campos não estiverem preenchidos
                }

                try {
                    // Obtém e valida os dados inseridos
                    int idEquipamento = Integer.parseInt(txtIdEquipamento.getText()); // Obtém o ID do equipamento
                    int idTecnico = Integer.parseInt(txtIdTecnico.getText()); // Obtém o ID do técnico
                    String tipo = txtTipo.getText(); // Obtém o tipo
                    LocalDate dataManutencao = LocalDate.parse(txtData.getText(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Valida a data
                    String descricao = txtDescricao.getText(); // Obtém a descrição
                    String pecasSubstituidas = txtPecasSubstituidas.getText(); // Obtém as peças substituídas
                    int tempoInatividade = Integer.parseInt(txtTempoInatividade.getText()); // Obtém o tempo de inatividade
                    String status = "Pendente"; // Define o status inicial

                    // Cria um novo objeto Manutencao
                    Manutencao manutencao = new Manutencao(0, idTecnico, idEquipamento, tipo, descricao, dataManutencao,
                            status, pecasSubstituidas, tempoInatividade);
                    manutencaoController.salvarManutencao(manutencao); // Salva a manutenção
                    JOptionPane.showMessageDialog(null, "Manutenção cadastrada com sucesso!"); // Mensagem de sucesso
                    limparCampos(); // Limpa os campos após salvar
                } catch (NumberFormatException | DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir os dados. Verifique os campos e tente novamente.", "Erro",
                            JOptionPane.ERROR_MESSAGE); // Mensagem de erro
                }
            }
        });

        // Evento para o botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos(); // Limpa os campos ao cancelar
            }
        });
    }

    // Método para limpar os campos da tela
    private void limparCampos() {
        txtIdEquipamento.setText(""); // Limpa o campo do ID do equipamento
        txtIdTecnico.setText(""); // Limpa o campo do ID do técnico
        txtTipo.setText(""); // Limpa o campo do tipo
        txtData.setText(""); // Limpa o campo da data
        txtDescricao.setText(""); // Limpa a área de descrição
        txtPecasSubstituidas.setText(""); // Limpa o campo das peças substituídas
        txtTempoInatividade.setText(""); // Limpa o campo do tempo de inatividade
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastroManutencao().setVisible(true); // Torna a tela visível
            }
        });
    }
}
