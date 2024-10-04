package com.manutencao.model; // Pacote onde a classe Tecnico está localizada

public class Tecnico {
    private int id; // ID do técnico (geralmente um campo autoincrementado no banco de dados)
    private String nome; // Nome do técnico
    private String especialidade; // Especialidade do técnico (ex: eletricista, mecânico)

    // Construtor sem parâmetros (construtor padrão)
    public Tecnico() {
    }

    // Construtor que aceita parâmetros para inicializar o técnico
    public Tecnico(int id, String nome, String especialidade) {
        this.id = id; // Inicializa o ID do técnico
        this.nome = nome; // Inicializa o nome do técnico
        this.especialidade = especialidade; // Inicializa a especialidade do técnico
    }

    // Métodos Getters e Setters
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
}
