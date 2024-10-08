package com.manutencao.model;

import java.time.LocalDate;

public class Manutencao {
    private int id;
    private int idEquipamento; // Ensure this field exists
    private int idTecnico; // ID do técnico responsável
    private String tipo;
    private String descricao;
    private LocalDate dataManutencao; // Data da manutenção
    private String status; // Status da manutenção
    private String pecasSubstituidas; // Peças que foram substituídas
    private int tempoInatividade; // Tempo em que o equipamento ficou inativo

    // Construtor padrão
    public Manutencao() {
        // Inicializa com valores padrão, se necessário
    }

    // Construtor com parâmetros
    public Manutencao(int id, int idEquipamento, int idTecnico, String tipo, String descricao,
                      LocalDate dataManutencao, String status, String pecasSubstituidas, int tempoInatividade) {
        this.id = id;
        this.idEquipamento = idEquipamento; // Initialize the idEquipamento field
        this.idTecnico = idTecnico;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataManutencao = dataManutencao;
        this.status = status;
        this.pecasSubstituidas = pecasSubstituidas;
        this.tempoInatividade = tempoInatividade;
    }

    // Getters e Setters para os atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public int getIdTecnico() {
        return idTecnico; // Método para retornar o ID do técnico
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico; // Método para definir o ID do técnico
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

    public LocalDate getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(LocalDate dataManutencao) {
        this.dataManutencao = dataManutencao;
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
}
