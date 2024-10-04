package com.manutencao.model; // Pacote onde a classe Equipamento está localizada

public class Equipamento {
    private int id; // ID do equipamento (geralmente um campo autoincrementado no banco de dados)
    private String aparelho; // Nome do aparelho
    private String modelo; // Modelo do equipamento
    private String local; // Local onde o equipamento está instalado

    // Construtor da classe Equipamento com todos os campos
    public Equipamento(int id, String aparelho, String modelo, String local) {
        this.id = id; // Inicializa o ID do equipamento
        this.aparelho = aparelho; // Inicializa o nome do aparelho
        this.modelo = modelo; // Inicializa o modelo do equipamento
        this.local = local; // Inicializa o local do equipamento
    }

    // Construtor da classe Equipamento sem o ID (usado para inserção)
    public Equipamento(String aparelho, String modelo, String local) {
        this.aparelho = aparelho; // Inicializa o nome do aparelho
        this.modelo = modelo; // Inicializa o modelo do equipamento
        this.local = local; // Inicializa o local do equipamento
    }

    // Método para obter o ID do equipamento
    public int getId() {
        return id;
    }

    // Método para definir o ID do equipamento
    public void setId(int id) {
        this.id = id;
    }

    // Método para obter o nome do aparelho
    public String getAparelho() {
        return aparelho;
    }

    // Método para definir o nome do aparelho
    public void setAparelho(String aparelho) {
        this.aparelho = aparelho;
    }

    // Método para obter o modelo do equipamento
    public String getModelo() {
        return modelo;
    }

    // Método para definir o modelo do equipamento
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Método para obter o local do equipamento
    public String getLocal() {
        return local;
    }

    // Método para definir o local do equipamento
    public void setLocal(String local) {
        this.local = local;
    }
}
