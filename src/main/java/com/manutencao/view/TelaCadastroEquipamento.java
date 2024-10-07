package com.manutencao.view; // Pacote onde a classe TelaCadastroEquipamento está localizada

import com.manutencao.controller.EquipamentoController; // Importa a classe controlador de equipamentos
import com.manutencao.model.Equipamento; // Importa a classe modelo de equipamentos

import javax.swing.*; // Importa classes da biblioteca Swing para interfaces gráficas
import java.awt.event.ActionEvent; // Importa a classe para eventos de ação
import java.awt.event.ActionListener; // Importa a interface para ouvintes de ação

public class TelaCadastroEquipamento extends JFrame { // Classe que representa a tela de cadastro de equipamentos
    private JTextField txtAparelho; // Campo de texto para o nome do aparelho
    private JTextField txtModelo; // Campo de texto para o modelo do equipamento
    private JTextField txtLocal; // Campo de texto para o local do equipamento
    private JButton btnSalvar; // Botão para salvar o equipamento
    private JButton btnCancelar; // Botão para cancelar a operação
    private EquipamentoController equipamentoController; // Controlador de equipamentos

    public TelaCadastroEquipamento() { // Construtor da classe
        equipamentoController = new EquipamentoController(); // Instancia o controlador

        setTitle("Cadastro de Equipamento"); // Define o título da janela
        setSize(300, 200); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout como nulo (posicionamento manual)

        // Criação e adição de rótulos e campos de texto
        JLabel lblAparelho = new JLabel("Aparelho:"); // Rótulo para o campo de aparelho
        lblAparelho.setBounds(10, 10, 80, 25); // Define a posição e tamanho do rótulo
        add(lblAparelho); // Adiciona o rótulo à janela

        txtAparelho = new JTextField(); // Cria o campo de texto para o aparelho
        txtAparelho.setBounds(100, 10, 160, 25); // Define a posição e tamanho do campo
        add(txtAparelho); // Adiciona o campo à janela

        JLabel lblModelo = new JLabel("Modelo:"); // Rótulo para o campo de modelo
        lblModelo.setBounds(10, 40, 80, 25); // Define a posição e tamanho do rótulo
        add(lblModelo); // Adiciona o rótulo à janela

        txtModelo = new JTextField(); // Cria o campo de texto para o modelo
        txtModelo.setBounds(100, 40, 160, 25); // Define a posição e tamanho do campo
        add(txtModelo); // Adiciona o campo à janela

        JLabel lblLocal = new JLabel("Local:"); // Rótulo para o campo de local
        lblLocal.setBounds(10, 70, 80, 25); // Define a posição e tamanho do rótulo
        add(lblLocal); // Adiciona o rótulo à janela

        txtLocal = new JTextField(); // Cria o campo de texto para o local
        txtLocal.setBounds(100, 70, 160, 25); // Define a posição e tamanho do campo
        add(txtLocal); // Adiciona o campo à janela

        // Criação e adição do botão de salvar
        btnSalvar = new JButton("Salvar"); // Cria o botão de salvar
        btnSalvar.setBounds(10, 110, 80, 25); // Define a posição e tamanho do botão
        add(btnSalvar); // Adiciona o botão à janela

        // Criação e adição do botão de cancelar
        btnCancelar = new JButton("Cancelar"); // Cria o botão de cancelar
        btnCancelar.setBounds(180, 110, 80, 25); // Define a posição e tamanho do botão
        add(btnCancelar); // Adiciona o botão à janela

        // Evento para o botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Método que é chamado quando o botão é pressionado
                // Cria um novo objeto Equipamento com os dados inseridos
                Equipamento equipamento = new Equipamento(
                        txtAparelho.getText(), // Obtém o texto do campo de aparelho
                        txtModelo.getText(), // Obtém o texto do campo de modelo
                        txtLocal.getText()); // Obtém o texto do campo de local
                
                // Chama o método de salvar no controlador
                if (equipamentoController.salvarEquipamento(equipamento)) { 
                    JOptionPane.showMessageDialog(null, "Equipamento salvo com sucesso!"); // Exibe uma mensagem de sucesso
                    dispose(); // Fecha a janela de cadastro
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar equipamento!", "Erro", JOptionPane.ERROR_MESSAGE);
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastroEquipamento().setVisible(true); // Cria uma nova instância da tela e a torna visível
            }
        });
    }
}
