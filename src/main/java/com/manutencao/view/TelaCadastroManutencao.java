package com.manutencao.view;

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
        criarComponentes();

        // Evento para o botão de salvar
        btnSalvar.addActionListener(e -> salvarManutencao()); // Lambda para simplificação

        // Evento para o botão de cancelar
        btnCancelar.addActionListener(e -> limparCampos()); // Lambda para simplificação
    }

    // Método para criar e adicionar componentes à tela
    private void criarComponentes() {
        JLabel lblIdEquipamento = new JLabel("ID Equipamento:");
        lblIdEquipamento.setBounds(10, 10, 120, 25);
        add(lblIdEquipamento);

        txtIdEquipamento = new JTextField();
        txtIdEquipamento.setBounds(140, 10, 160, 25);
        add(txtIdEquipamento);

        JLabel lblIdTecnico = new JLabel("ID Técnico:");
        lblIdTecnico.setBounds(10, 40, 120, 25);
        add(lblIdTecnico);

        txtIdTecnico = new JTextField();
        txtIdTecnico.setBounds(140, 40, 160, 25);
        add(txtIdTecnico);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(10, 70, 120, 25);
        add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(140, 70, 160, 25);
        add(txtTipo);

        JLabel lblData = new JLabel("Data (yyyy-MM-dd):");
        lblData.setBounds(10, 100, 120, 25);
        add(lblData);

        txtData = new JTextField();
        txtData.setBounds(140, 100, 160, 25);
        add(txtData);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(10, 130, 120, 25);
        add(lblDescricao);

        txtDescricao = new JTextArea();
        txtDescricao.setBounds(10, 160, 360, 80);
        add(txtDescricao);

        JLabel lblPecasSubstituidas = new JLabel("Peças Substituídas:");
        lblPecasSubstituidas.setBounds(10, 250, 120, 25);
        add(lblPecasSubstituidas);

        txtPecasSubstituidas = new JTextField();
        txtPecasSubstituidas.setBounds(140, 250, 160, 25);
        add(txtPecasSubstituidas);

        JLabel lblTempoInatividade = new JLabel("Tempo Inatividade:");
        lblTempoInatividade.setBounds(10, 280, 120, 25);
        add(lblTempoInatividade);

        txtTempoInatividade = new JTextField();
        txtTempoInatividade.setBounds(140, 280, 160, 25);
        add(txtTempoInatividade);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 320, 80, 25);
        add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(280, 320, 100, 25);
        add(btnCancelar);
    }

    // Método para salvar a manutenção
    private void salvarManutencao() {
        // Validação dos campos
        if (camposEstaoVazios()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE); // Mensagem de erro
            return; // Retorna se os campos não estiverem preenchidos
        }

        try {
            // Obtém e valida os dados inseridos
            int idEquipamento = Integer.parseInt(txtIdEquipamento.getText());
            int idTecnico = Integer.parseInt(txtIdTecnico.getText());
            String tipo = txtTipo.getText();
            LocalDate dataManutencao = LocalDate.parse(txtData.getText(), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String descricao = txtDescricao.getText();
            String pecasSubstituidas = txtPecasSubstituidas.getText();
            int tempoInatividade = Integer.parseInt(txtTempoInatividade.getText());
            String status = "Pendente"; // Define o status inicial

            // Cria um novo objeto Manutencao
            Manutencao manutencao = new Manutencao(0, idEquipamento, idTecnico, tipo, descricao, 
                    dataManutencao, status, pecasSubstituidas, tempoInatividade);
            manutencaoController.salvarManutencao(manutencao); // Salva a manutenção
            JOptionPane.showMessageDialog(null, "Manutenção cadastrada com sucesso!"); // Mensagem de sucesso
            limparCampos(); // Limpa os campos após salvar
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados. Verifique os campos e tente novamente.", "Erro",
                    JOptionPane.ERROR_MESSAGE); // Mensagem de erro
        }
    }

    // Método para verificar se os campos estão vazios
    private boolean camposEstaoVazios() {
        return txtIdEquipamento.getText().trim().isEmpty() || 
               txtIdTecnico.getText().trim().isEmpty() ||
               txtTipo.getText().trim().isEmpty() || 
               txtData.getText().trim().isEmpty() ||
               txtDescricao.getText().trim().isEmpty() || 
               txtPecasSubstituidas.getText().trim().isEmpty() ||
               txtTempoInatividade.getText().trim().isEmpty();
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
        SwingUtilities.invokeLater(() -> new TelaCadastroManutencao().setVisible(true)); // Torna a tela visível
    }
}
