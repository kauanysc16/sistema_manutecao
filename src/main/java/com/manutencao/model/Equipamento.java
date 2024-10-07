package com.manutencao.model;

import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas

public class Equipamento {
    private int id; // ID do equipamento
    private String aparelho; // Nome do aparelho
    private String modelo; // Modelo do equipamento
    private String local; // Local onde o equipamento está
    private LocalDate dataAquisicao; // Data de aquisição do equipamento
    private String especificacoesTecnicas; // Especificações técnicas do equipamento

    // Construtor
    public Equipamento() {
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAparelho() {
        return aparelho;
    }

    public void setAparelho(String aparelho) {
        this.aparelho = aparelho;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getEspecificacoesTecnicas() {
        return especificacoesTecnicas;
    }

    public void setEspecificacoesTecnicas(String especificacoesTecnicas) {
        this.especificacoesTecnicas = especificacoesTecnicas;
    }
}
