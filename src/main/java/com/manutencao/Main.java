package com.manutencao; // Pacote onde a classe Main está localizada

import javax.swing.JButton; // Importa a tela de cadastro de equipamentos
import javax.swing.JFrame; // Importa a tela de cadastro de técnicos
import javax.swing.SwingUtilities; // Importa a tela de cadastro de manutenções

import com.manutencao.view.TelaCadastroEquipamento; // Importa a tela de listagem de manutenções
import com.manutencao.view.TelaCadastroManutencao; // Importa a biblioteca Swing para a interface gráfica
import com.manutencao.view.TelaCadastroTecnico; // Importa a classe para eventos de ação
import com.manutencao.view.TelaListaManutencao; // Importa a interface para ouvintes de ação

public class Main { // Classe principal
    public static void main(String[] args) { // Método principal
        SwingUtilities.invokeLater(() -> { // Garante que a GUI seja atualizada na thread do evento
            criarTelaPrincipal(); // Chama o método para criar a tela principal
        });
    }

    private static void criarTelaPrincipal() { // Método para criar a janela principal
        JFrame frame = new JFrame("Sistema de Manutenção"); // Cria a janela principal
        frame.setSize(300, 250); // Ajusta o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setLayout(null); // Define o layout como nulo (posicionamento manual)

        // Criação e configuração do botão "Cadastro Equipamento"
        JButton btnCadastroEquipamento = criarBotao("Cadastro Equipamento", 50, 30);
        frame.add(btnCadastroEquipamento); // Adiciona o botão à janela

        // Criação e configuração do botão "Cadastro Técnico"
        JButton btnCadastroTecnico = criarBotao("Cadastro Técnico", 50, 70);
        frame.add(btnCadastroTecnico); // Adiciona o botão à janela

        // Criação e configuração do botão "Cadastro Manutenção"
        JButton btnCadastroManutencao = criarBotao("Cadastro Manutenção", 50, 110);
        frame.add(btnCadastroManutencao); // Adiciona o botão à janela

        // Criação e configuração do botão "Lista de Manutenções"
        JButton btnListaManutencao = criarBotao("Lista de Manutenções", 50, 150);
        frame.add(btnListaManutencao); // Adiciona o botão ao frame

        // Ações para abrir as telas correspondentes
        btnCadastroEquipamento.addActionListener(e -> abrirTela(new TelaCadastroEquipamento()));
        btnCadastroTecnico.addActionListener(e -> abrirTela(new TelaCadastroTecnico()));
        btnCadastroManutencao.addActionListener(e -> abrirTela(new TelaCadastroManutencao()));
        btnListaManutencao.addActionListener(e -> abrirTela(new TelaListaManutencao()));

        frame.setVisible(true); // Torna a janela visível
    }

    private static JButton criarBotao(String texto, int x, int y) { // Método para criar botões
        JButton button = new JButton(texto); // Cria o botão
        button.setBounds(x, y, 200, 30); // Define a posição e o tamanho do botão
        return button; // Retorna o botão criado
    }

    private static void abrirTela(JFrame tela) { // Método para abrir novas telas
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o fechamento da tela
        tela.setVisible(true); // Torna a tela visível
    }
}
