package com.manutencao.model;

import java.time.LocalDate;

public class Equipamento {
    private int id;
    private String aparelho;
    private String modelo;
    private String local;
    private String especificacoesTecnicas;
    private LocalDate dataAquisicao;

    // Construtor vazio
    public Equipamento() {
    }

    // Construtor com todos os campos
    public Equipamento(int id, String aparelho, String modelo, String local, String especificacoesTecnicas,
            LocalDate dataAquisicao) {
        this.id = id;
        this.aparelho = aparelho;
        this.modelo = modelo;
        this.local = local;
        this.especificacoesTecnicas = especificacoesTecnicas;
        this.dataAquisicao = dataAquisicao;
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

    public String getEspecificacoesTecnicas() {
        return especificacoesTecnicas;
    }

    public void setEspecificacoesTecnicas(String especificacoesTecnicas) {
        this.especificacoesTecnicas = especificacoesTecnicas;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
