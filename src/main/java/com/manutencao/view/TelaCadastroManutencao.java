package com.manutencao.view; // Pacote onde a classe TelaCadastroManutencao está localizada

import java.awt.event.ActionEvent; // Importa a classe para eventos de ação
import java.awt.event.ActionListener; // Importa a interface para ouvintes de ação
import java.time.LocalDateTime; // Importa a classe para manipulação de data e hora
import java.time.format.DateTimeFormatter; // Importa a classe para formatar data e hora
import java.time.format.DateTimeParseException; // Importa a classe para exceções de análise de data

import javax.swing.JButton; // Importa a classe para botões
import javax.swing.JFrame; // Importa a classe para a janela principal
import javax.swing.JLabel; // Importa a classe para rótulos
import javax.swing.JOptionPane; // Importa a classe para diálogos
import javax.swing.JTextArea; // Importa a classe para áreas de texto
import javax.swing.JTextField; // Importa a classe para campos de texto
import javax.swing.SwingUtilities; // Importa a classe para manipulação de threads do Swing

import com.manutencao.controller.ManutencaoController; // Importa a classe controlador de manutenções
import com.manutencao.model.Manutencao; // Importa a classe modelo de manutenções

public class TelaCadastroManutencao extends JFrame { // Classe que representa a tela de cadastro de manutenção
    private JTextField txtIdTecnico; // Campo de texto para o ID do técnico
    private JTextField txtIdEquipamento; // Campo de texto para o ID do equipamento
    private JTextField txtData; // Campo de texto para a data da manutenção
    private JTextArea txtDescricao; // Área de texto para a descrição da manutenção
    private JButton btnSalvar; // Botão para salvar a manutenção
    private JButton btnCancelar; // Botão para cancelar a operação
    private ManutencaoController manutencaoController; // Controlador de manutenções

    public TelaCadastroManutencao() { // Construtor da classe
        manutencaoController = new ManutencaoController(); // Instancia o controlador

        // Configurações da janela
        setTitle("Cadastro de Manutenção"); // Define o título da janela
        setSize(400, 300); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout como nulo (posicionamento manual)

        // Criação e adição de rótulos e campos de texto
        JLabel lblIdTecnico = new JLabel("ID Técnico:"); // Rótulo para o campo de ID do técnico
        lblIdTecnico.setBounds(10, 10, 80, 25); // Define a posição e tamanho do rótulo
        add(lblIdTecnico); // Adiciona o rótulo à janela

        txtIdTecnico = new JTextField(); // Cria o campo de texto para o ID do técnico
        txtIdTecnico.setBounds(100, 10, 160, 25); // Define a posição e tamanho do campo
        add(txtIdTecnico); // Adiciona o campo à janela

        JLabel lblIdEquipamento = new JLabel("ID Equipamento:"); // Rótulo para o campo de ID do equipamento
        lblIdEquipamento.setBounds(10, 40, 120, 25); // Define a posição e tamanho do rótulo
        add(lblIdEquipamento); // Adiciona o rótulo à janela

        txtIdEquipamento = new JTextField(); // Cria o campo de texto para o ID do equipamento
        txtIdEquipamento.setBounds(140, 40, 120, 25); // Define a posição e tamanho do campo
        add(txtIdEquipamento); // Adiciona o campo à janela

        JLabel lblData = new JLabel("Data (yyyy-MM-dd HH:mm):"); // Rótulo para o campo de data
        lblData.setBounds(10, 70, 200, 25); // Define a posição e tamanho do rótulo
        add(lblData); // Adiciona o rótulo à janela

        txtData = new JTextField(); // Cria o campo de texto para a data
        txtData.setBounds(210, 70, 160, 25); // Define a posição e tamanho do campo
        add(txtData); // Adiciona o campo à janela

        JLabel lblDescricao = new JLabel("Descrição:"); // Rótulo para a área de descrição
        lblDescricao.setBounds(10, 100, 80, 25); // Define a posição e tamanho do rótulo
        add(lblDescricao); // Adiciona o rótulo à janela

        txtDescricao = new JTextArea(); // Cria a área de texto para a descrição
        txtDescricao.setBounds(10, 130, 360, 80); // Define a posição e tamanho da área de texto
        add(txtDescricao); // Adiciona a área de texto à janela

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
            public void actionPerformed(ActionEvent e) { // Método que é chamado quando o botão é pressionado
                // Validação dos campos
                if (txtIdTecnico.getText().trim().isEmpty() || txtIdEquipamento.getText().trim().isEmpty() || txtData.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return; // Retorna se algum campo está vazio
                }
        
                try {
                    // Obtém e valida os dados inseridos
                    int idTecnico = Integer.parseInt(txtIdTecnico.getText()); // Converte o ID do técnico para inteiro
                    int idEquipamento = Integer.parseInt(txtIdEquipamento.getText()); // Converte o ID do equipamento para inteiro
                    LocalDateTime data = LocalDateTime.parse(txtData.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // Converte a string da data para LocalDateTime
                    String descricao = txtDescricao.getText(); // Obtém a descrição da manutenção
                    String status = "Pendente"; // Status padrão da manutenção
        
                    // Cria um novo objeto Manutencao
                    Manutencao manutencao = new Manutencao(0, idTecnico, idEquipamento, data.toString(), descricao, status);
                    manutencaoController.salvarManutencao(manutencao); // Salva a manutenção
                    JOptionPane.showMessageDialog(null, "Manutenção salva com sucesso!"); // Exibe uma mensagem de sucesso

                    // Limpa os campos após o sucesso
                    txtIdTecnico.setText("");
                    txtIdEquipamento.setText("");
                    txtData.setText("");
                    txtDescricao.setText("");
                } catch (NumberFormatException ex) {
                    // Trata exceções de formatação de número
                    JOptionPane.showMessageDialog(null, "Por favor, insira IDs válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException ex) {
                    // Trata exceções de formatação de data
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use yyyy-MM-dd HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    // Trata outras exceções
                    JOptionPane.showMessageDialog(null, "Erro ao salvar a manutenção: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Evento para o botão de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Método que é chamado quando o botão é pressionado
                dispose(); // Fecha a janela de cadastro
            }
        });
    }

    public static void main(String[] args) { // Método principal para executar a aplicação
        SwingUtilities.invokeLater(() -> { // Executa a criação da interface na thread do Swing
            TelaCadastroManutencao tela = new TelaCadastroManutencao(); // Cria uma nova instância da tela
            tela.setVisible(true); // Torna a tela visível
        });
    }
}
