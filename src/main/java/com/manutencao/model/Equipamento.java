package com.manutencao.model;

import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas

public class Equipamento {
    private int id; // ID único do equipamento
    private String aparelho; // Nome do aparelho
    private String modelo; // Modelo do aparelho
    private String local; // Local onde o equipamento está situado
    private String especificacoesTecnicas; // Especificações técnicas do equipamento
    private LocalDate dataAquisicao; // Data de aquisição do equipamento

    // Construtor vazio
    public Equipamento() {
    }

    // Construtor com todos os campos
    public Equipamento(int id, String aparelho, String modelo, String local, String especificacoesTecnicas,
            LocalDate dataAquisicao) {
        this.id = id; // Inicializa o ID
        this.aparelho = aparelho; // Inicializa o nome do aparelho
        this.modelo = modelo; // Inicializa o modelo do aparelho
        this.local = local; // Inicializa o local
        this.especificacoesTecnicas = especificacoesTecnicas; // Inicializa as especificações técnicas
        this.dataAquisicao = dataAquisicao; // Inicializa a data de aquisição
    }

    // Getters e Setters
    public int getId() {
        return id; // Retorna o ID do equipamento
    }

    public void setId(int id) {
        this.id = id; // Define o ID do equipamento
    }

    public String getAparelho() {
        return aparelho; // Retorna o nome do aparelho
    }

    public void setAparelho(String aparelho) {
        this.aparelho = aparelho; // Define o nome do aparelho
    }

    public String getModelo() {
        return modelo; // Retorna o modelo do aparelho
    }

    public void setModelo(String modelo) {
        this.modelo = modelo; // Define o modelo do aparelho
    }

    public String getLocal() {
        return local; // Retorna o local do equipamento
    }

    public void setLocal(String local) {
        this.local = local; // Define o local do equipamento
    }

    public String getEspecificacoesTecnicas() {
        return especificacoesTecnicas; // Retorna as especificações técnicas do equipamento
    }

    public void setEspecificacoesTecnicas(String especificacoesTecnicas) {
        this.especificacoesTecnicas = especificacoesTecnicas; // Define as especificações técnicas do equipamento
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao; // Retorna a data de aquisição do equipamento
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao; // Define a data de aquisição do equipamento
    }
}
