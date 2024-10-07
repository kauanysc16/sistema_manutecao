package com.manutencao.model;

import java.time.LocalDate;

public class Manutencao {
    private int id; // Identificador da manutenção
    private int equipamentoId; // ID do equipamento relacionado
    private String tipo; // Tipo de manutenção (ex: Corretiva, Preventiva)
    private String descricao; // Descrição da manutenção
    private LocalDate dataManutencao; // Data da manutenção
    private String status; // Status da manutenção (ex: Concluída, Pendente)
    private String pecasSubstituidas; // Peças que foram substituídas
    private int tempoInatividade; // Tempo de inatividade em horas

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
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
}
