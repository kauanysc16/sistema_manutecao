package com.manutencao.view;

import com.manutencao.controller.EquipamentoController;
import com.manutencao.model.Equipamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroEquipamento extends JFrame {
    private JTextField txtAparelho;
    private JTextField txtModelo;
    private JTextField txtLocal;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private EquipamentoController equipamentoController;

    public TelaCadastroEquipamento() {
        equipamentoController = new EquipamentoController();

        setTitle("Cadastro de Equipamento");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblAparelho = new JLabel("Aparelho:");
        lblAparelho.setBounds(10, 10, 80, 25);
        add(lblAparelho);

        txtAparelho = new JTextField();
        txtAparelho.setBounds(100, 10, 160, 25);
        add(txtAparelho);

        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(10, 40, 80, 25);
        add(lblModelo);

        txtModelo = new JTextField();
        txtModelo.setBounds(100, 40, 160, 25);
        add(txtModelo);

        JLabel lblLocal = new JLabel("Local:");
        lblLocal.setBounds(10, 70, 80, 25);
        add(lblLocal);

        txtLocal = new JTextField();
        txtLocal.setBounds(100, 70, 160, 25);
        add(txtLocal);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 110, 80, 25);
        add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(180, 110, 80, 25);
        add(btnCancelar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipamento equipamento = new Equipamento();
                equipamento.setAparelho(txtAparelho.getText());
                equipamento.setModelo(txtModelo.getText());
                equipamento.setLocal(txtLocal.getText());
                equipamentoController.salvarEquipamento(equipamento);
                JOptionPane.showMessageDialog(null, "Equipamento salvo com sucesso!");
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastroEquipamento().setVisible(true);
            }
        });
    }
}
