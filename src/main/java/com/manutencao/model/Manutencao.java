package com.manutencao.model;

public class Manutencao {
    private int id;
    private int idTecnico;
    private int idEquipamento;
    private String data;
    private String descricao;
    private String status;

    // Construtor com todos os parâmetros
    public Manutencao(int id, int idTecnico, int idEquipamento, String data, String descricao, String status) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.idEquipamento = idEquipamento;
        this.data = data;
        this.descricao = descricao;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
