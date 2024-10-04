package com.manutencao; // Pacote onde a classe Main está localizada

import com.manutencao.view.TelaCadastroEquipamento; // Importa a tela de cadastro de equipamentos
import com.manutencao.view.TelaCadastroTecnico; // Importa a tela de cadastro de técnicos
import com.manutencao.view.TelaCadastroManutencao; // Importa a tela de cadastro de manutenções
import com.manutencao.view.TelaListaManutencao; // Importa a tela de listagem de manutenções

import javax.swing.*; // Importa a biblioteca Swing para a interface gráfica
import java.awt.event.ActionEvent; // Importa a classe para eventos de ação
import java.awt.event.ActionListener; // Importa a interface para ouvintes de ação

public class Main { // Classe principal
    public static void main(String[] args) { // Método principal
        JFrame frame = new JFrame("Sistema de Manutenção"); // Cria a janela principal
        frame.setSize(300, 250); // Ajusta o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setLayout(null); // Define o layout como nulo (posicionamento manual)

        // Criação e configuração do botão "Cadastro Equipamento"
        JButton btnCadastroEquipamento = new JButton("Cadastro Equipamento");
        btnCadastroEquipamento.setBounds(50, 30, 200, 30); // Define a posição e o tamanho do botão
        frame.add(btnCadastroEquipamento); // Adiciona o botão à janela

        // Criação e configuração do botão "Cadastro Técnico"
        JButton btnCadastroTecnico = new JButton("Cadastro Técnico");
        btnCadastroTecnico.setBounds(50, 70, 200, 30); // Define a posição e o tamanho do botão
        frame.add(btnCadastroTecnico); // Adiciona o botão à janela

        // Criação e configuração do botão "Cadastro Manutenção"
        JButton btnCadastroManutencao = new JButton("Cadastro Manutenção");
        btnCadastroManutencao.setBounds(50, 110, 200, 30); // Define a posição e o tamanho do botão
        frame.add(btnCadastroManutencao); // Adiciona o botão à janela

        // Criação e configuração do botão "Lista de Manutenções"
        JButton btnListaManutencao = new JButton("Lista de Manutenções"); // Novo botão
        btnListaManutencao.setBounds(50, 150, 200, 30); // Ajuste de posição
        frame.add(btnListaManutencao); // Adiciona o botão ao frame

        // Ação para abrir a tela de cadastro de equipamentos
        btnCadastroEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Quando o botão é clicado
                new TelaCadastroEquipamento().setVisible(true); // Abre a tela de cadastro de equipamentos
            }
        });

        // Ação para abrir a tela de cadastro de técnicos
        btnCadastroTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Quando o botão é clicado
                new TelaCadastroTecnico().setVisible(true); // Abre a tela de cadastro de técnicos
            }
        });

        // Ação para abrir a tela de cadastro de manutenções
        btnCadastroManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Quando o botão é clicado
                new TelaCadastroManutencao().setVisible(true); // Abre a tela de cadastro de manutenções
            }
        });

        // Ação para abrir a tela de listagem de manutenções
        btnListaManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Quando o botão é clicado
                new TelaListaManutencao().setVisible(true); // Abre a tela de listagem de manutenções
            }
        });

        frame.setVisible(true); // Torna a janela visível
    }
}
