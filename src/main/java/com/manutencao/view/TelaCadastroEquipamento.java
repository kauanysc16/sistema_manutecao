package com.manutencao.view;

import com.manutencao.controller.EquipamentoController;
import com.manutencao.model.Equipamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroEquipamento extends JFrame {
    private JTextField txtAparelho; // Campo de texto para o aparelho
    private JTextField txtModelo; // Campo de texto para o modelo
    private JTextField txtLocal; // Campo de texto para o local
    private JButton btnSalvar; // Botão para salvar o equipamento
    private JButton btnCancelar; // Botão para cancelar a operação
    private EquipamentoController equipamentoController; // Controlador para gerenciar equipamentos

    // Construtor da tela de cadastro de equipamento
    public TelaCadastroEquipamento() {
        equipamentoController = new EquipamentoController(); // Inicializa o controlador

        setTitle("Cadastro de Equipamento"); // Define o título da janela
        setSize(300, 200); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Desabilita o layout padrão

        // Configuração do label e campo de texto para "Aparelho"
        JLabel lblAparelho = new JLabel("Aparelho:");
        lblAparelho.setBounds(10, 10, 80, 25); // Define a posição e tamanho do label
        add(lblAparelho); // Adiciona o label à janela

        txtAparelho = new JTextField(); // Inicializa o campo de texto
        txtAparelho.setBounds(100, 10, 160, 25); // Define a posição e tamanho do campo
        add(txtAparelho); // Adiciona o campo à janela

        // Configuração do label e campo de texto para "Modelo"
        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(10, 40, 80, 25); // Define a posição e tamanho do label
        add(lblModelo); // Adiciona o label à janela

        txtModelo = new JTextField(); // Inicializa o campo de texto
        txtModelo.setBounds(100, 40, 160, 25); // Define a posição e tamanho do campo
        add(txtModelo); // Adiciona o campo à janela

        // Configuração do label e campo de texto para "Local"
        JLabel lblLocal = new JLabel("Local:");
        lblLocal.setBounds(10, 70, 80, 25); // Define a posição e tamanho do label
        add(lblLocal); // Adiciona o label à janela

        txtLocal = new JTextField(); // Inicializa o campo de texto
        txtLocal.setBounds(100, 70, 160, 25); // Define a posição e tamanho do campo
        add(txtLocal); // Adiciona o campo à janela

        // Configuração do botão "Salvar"
        btnSalvar = new JButton("Salvar"); // Cria o botão
        btnSalvar.setBounds(10, 110, 80, 25); // Define a posição e tamanho do botão
        add(btnSalvar); // Adiciona o botão à janela

        // Configuração do botão "Cancelar"
        btnCancelar = new JButton("Cancelar"); // Cria o botão
        btnCancelar.setBounds(180, 110, 80, 25); // Define a posição e tamanho do botão
        add(btnCancelar); // Adiciona o botão à janela

        // Ação para o botão "Salvar"
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipamento equipamento = new Equipamento(); // Cria um novo objeto Equipamento
                equipamento.setAparelho(txtAparelho.getText()); // Define o aparelho
                equipamento.setModelo(txtModelo.getText()); // Define o modelo
                equipamento.setLocal(txtLocal.getText()); // Define o local
                equipamentoController.salvarEquipamento(equipamento); // Salva o equipamento
                JOptionPane.showMessageDialog(null, "Equipamento salvo com sucesso!"); // Mensagem de sucesso
                dispose(); // Fecha a janela
            }
        });

        // Ação para o botão "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela sem salvar
            }
        });
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastroEquipamento().setVisible(true); // Torna a tela visível
            }
        });
    }
}
