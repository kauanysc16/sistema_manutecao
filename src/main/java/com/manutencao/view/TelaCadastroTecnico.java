package com.manutencao.view; // Pacote onde a classe TelaCadastroTecnico está localizada

import com.manutencao.controller.TecnicoController; // Importa a classe controlador de técnicos
import com.manutencao.model.Tecnico; // Importa a classe modelo de técnicos

import javax.swing.*; // Importa classes da biblioteca Swing para a interface gráfica
import java.awt.event.ActionEvent; // Importa a classe para eventos de ação
import java.awt.event.ActionListener; // Importa a interface para ouvintes de ação

public class TelaCadastroTecnico extends JFrame { // Classe que representa a tela de cadastro de técnicos
    private JTextField txtNome; // Campo de texto para o nome do técnico
    private JTextField txtEspecialidade; // Campo de texto para a especialidade do técnico
    private JCheckBox chkDisponibilidade; // Caixa de seleção para a disponibilidade do técnico
    private JButton btnSalvar; // Botão para salvar o técnico
    private JButton btnCancelar; // Botão para cancelar a operação
    private TecnicoController tecnicoController; // Controlador de técnicos

    public TelaCadastroTecnico() { // Construtor da classe
        tecnicoController = new TecnicoController(); // Instancia o controlador

        // Configurações da janela
        setTitle("Cadastro de Técnico"); // Define o título da janela
        setSize(300, 250); // Aumenta o tamanho da janela para acomodar o novo campo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Define o layout como nulo (posicionamento manual)

        // Criação e adição de rótulos e campos de texto
        JLabel lblNome = new JLabel("Nome:"); // Rótulo para o campo de nome
        lblNome.setBounds(10, 10, 80, 25); // Define a posição e tamanho do rótulo
        add(lblNome); // Adiciona o rótulo à janela

        txtNome = new JTextField(); // Cria o campo de texto para o nome
        txtNome.setBounds(100, 10, 160, 25); // Define a posição e tamanho do campo
        add(txtNome); // Adiciona o campo à janela

        JLabel lblEspecialidade = new JLabel("Especialidade:"); // Rótulo para o campo de especialidade
        lblEspecialidade.setBounds(10, 40, 100, 25); // Define a posição e tamanho do rótulo
        add(lblEspecialidade); // Adiciona o rótulo à janela

        txtEspecialidade = new JTextField(); // Cria o campo de texto para a especialidade
        txtEspecialidade.setBounds(120, 40, 140, 25); // Define a posição e tamanho do campo
        add(txtEspecialidade); // Adiciona o campo à janela

        // Criação e adição da caixa de seleção para disponibilidade
        chkDisponibilidade = new JCheckBox("Disponível"); // Cria a caixa de seleção
        chkDisponibilidade.setBounds(10, 70, 100, 25); // Define a posição e tamanho da caixa
        add(chkDisponibilidade); // Adiciona a caixa à janela

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
                // Criação de um novo técnico com os dados inseridos
                Tecnico tecnico = new Tecnico(0, txtNome.getText(), txtEspecialidade.getText());
                tecnico.setDisponivel(chkDisponibilidade.isSelected()); // Define a disponibilidade do técnico
                tecnicoController.salvarTecnico(tecnico); // Salva o técnico usando o controlador
                JOptionPane.showMessageDialog(null, "Técnico salvo com sucesso!"); // Exibe uma mensagem de sucesso
                dispose(); // Fecha a janela de cadastro
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
        new TelaCadastroTecnico().setVisible(true); // Cria uma nova instância da tela e a torna visível
    }
}
