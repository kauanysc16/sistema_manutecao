package com.manutencao; // Pacote onde a classe Main está localizada

import javax.swing.JButton; // Importa a classe JButton para criar botões
import javax.swing.JFrame; // Importa a classe JFrame para criar a janela principal
import javax.swing.SwingUtilities; // Importa SwingUtilities para garantir a thread de eventos

import com.manutencao.view.TelaCadastroEquipamento; // Importa a tela de cadastro de equipamentos
import com.manutencao.view.TelaCadastroManutencao; // Importa a tela de cadastro de manutenções
import com.manutencao.view.TelaCadastroTecnico; // Importa a tela de cadastro de técnicos
import com.manutencao.view.TelaListaManutencao; // Importa a tela de listagem de manutenções

public class Main { // Classe principal do aplicativo
    public static void main(String[] args) { // Método principal que inicia a aplicação
        // Garante que a GUI seja atualizada na thread do evento
        SwingUtilities.invokeLater(() -> {
            criarTelaPrincipal(); // Chama o método para criar a tela principal
        });
    }

    private static void criarTelaPrincipal() { // Método para criar a janela principal
        JFrame frame = new JFrame("Sistema de Manutenção"); // Cria a janela principal com um título
        frame.setSize(300, 250); // Ajusta o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão de fechamento da janela
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setLayout(null); // Define o layout como nulo para posicionamento manual

        // Criação e configuração do botão "Cadastro Equipamento"
        JButton btnCadastroEquipamento = criarBotao("Cadastro Equipamento", 50, 30); // Cria o botão com texto e posição
        frame.add(btnCadastroEquipamento); // Adiciona o botão à janela

        // Criação e configuração do botão "Cadastro Técnico"
        JButton btnCadastroTecnico = criarBotao("Cadastro Técnico", 50, 70); // Cria o botão para cadastro de técnicos
        frame.add(btnCadastroTecnico); // Adiciona o botão à janela

        // Criação e configuração do botão "Cadastro Manutenção"
        JButton btnCadastroManutencao = criarBotao("Cadastro Manutenção", 50, 110); // Cria o botão para cadastro de manutenções
        frame.add(btnCadastroManutencao); // Adiciona o botão à janela

        // Criação e configuração do botão "Lista de Manutenções"
        JButton btnListaManutencao = criarBotao("Lista de Manutenções", 50, 150); // Cria o botão para listar manutenções
        frame.add(btnListaManutencao); // Adiciona o botão ao frame

        // Ações para abrir as telas correspondentes ao clicar nos botões
        btnCadastroEquipamento.addActionListener(e -> abrirTela(new TelaCadastroEquipamento())); // Abre a tela de cadastro de equipamentos
        btnCadastroTecnico.addActionListener(e -> abrirTela(new TelaCadastroTecnico())); // Abre a tela de cadastro de técnicos
        btnCadastroManutencao.addActionListener(e -> abrirTela(new TelaCadastroManutencao())); // Abre a tela de cadastro de manutenções
        btnListaManutencao.addActionListener(e -> abrirTela(new TelaListaManutencao())); // Abre a tela de listagem de manutenções

        frame.setVisible(true); // Torna a janela visível
    }

    private static JButton criarBotao(String texto, int x, int y) { // Método para criar botões personalizados
        JButton button = new JButton(texto); // Cria um botão com o texto especificado
        button.setBounds(x, y, 200, 30); // Define a posição e o tamanho do botão
        return button; // Retorna o botão criado
    }

    private static void abrirTela(JFrame tela) { // Método para abrir novas telas
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define a operação de fechamento da tela como DISPOSE
        tela.setVisible(true); // Torna a tela visível
    }
}
