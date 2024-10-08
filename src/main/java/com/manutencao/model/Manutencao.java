package com.manutencao.model;

import java.time.LocalDate;

public class Manutencao {
    private int id; // ID da manutenção
    private int idEquipamento; // ID do equipamento
    private int idTecnico;
    private String tipo; // Tipo da manutenção
    private String descricao; // Descrição da manutenção
    private LocalDate data; // Data da manutenção
    private String status; // Status da manutenção
    private String pecasSubstituidas; // Peças substituídas
    private int tempoInatividade; // Tempo de inatividade

    // Construtor sem argumentos
    public Manutencao() {
    }

    // Construtor com todos os parâmetros
    public Manutencao(int id, int idTecnico, int idEquipamento, String tipo, String descricao, LocalDate data,
            String status, String pecasSubstituidas, int tempoInatividade) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.idEquipamento = idEquipamento;
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.pecasSubstituidas = pecasSubstituidas;
        this.tempoInatividade = tempoInatividade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipamentoId() { // Renomeado
        return idEquipamento;
    }

    public void setEquipamentoId(int idEquipamento) { // Renomeado
        this.idEquipamento = idEquipamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataManutencao() { // Renomeado
        return data;
    }

    public void setDataManutencao(LocalDate data) { // Renomeado
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPecasSubstituidas() {
        return pecasSubstituidas;
    }

    public void setPecasSubstituidas(String pecasSubstituidas) {
        this.pecasSubstituidas = pecasSubstituidas;
    }

    public int getTempoInatividade() {
        return tempoInatividade;
    }

    public void setTempoInatividade(int tempoInatividade) {
        this.tempoInatividade = tempoInatividade;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
