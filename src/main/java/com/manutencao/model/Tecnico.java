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
        this.id = id; // Inicializa o ID do técnico
        this.nome = nome; // Inicializa o nome do técnico
        this.especialidade = especialidade; // Inicializa a especialidade do técnico
        this.disponivel = disponivel; // Inicializa a disponibilidade do técnico
    }

    // Novo construtor que aceita apenas id, nome e especialidade
    public Tecnico(int id, String nome, String especialidade) {
        this.id = id; // Inicializa o ID do técnico
        this.nome = nome; // Inicializa o nome do técnico
        this.especialidade = especialidade; // Inicializa a especialidade do técnico
        this.disponivel = true; // Define disponibilidade como verdadeira por padrão
    }

    // Getters e Setters
    public int getId() {
        return id; // Retorna o ID do técnico
    }

    public void setId(int id) {
        this.id = id; // Define o ID do técnico
    }

    public String getNome() {
        return nome; // Retorna o nome do técnico
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do técnico
    }

    public String getEspecialidade() {
        return especialidade; // Retorna a especialidade do técnico
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade; // Define a especialidade do técnico
    }

    public boolean isDisponivel() {
        return disponivel; // Retorna a disponibilidade do técnico
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel; // Define a disponibilidade do técnico
    }
}
