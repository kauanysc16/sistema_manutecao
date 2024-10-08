package com.manutencao.model;

public class Tecnico {
    private int id; // ID do técnico
    private String nome; // Nome do técnico
    private String especialidade; // Especialidade do técnico
    private boolean disponivel; // Disponibilidade do técnico

    // Construtor padrão
    public Tecnico() {
    }

    // Construtor com parâmetros
    public Tecnico(int id, String nome, String especialidade, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.disponivel = disponivel;
    }

    // Novo construtor que aceita apenas id, nome e especialidade
    public Tecnico(int id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.disponivel = true; // Valor padrão
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
