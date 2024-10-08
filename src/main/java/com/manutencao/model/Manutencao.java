package com.manutencao.model;

import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas

public class Manutencao {
    private int id; // ID da manutenção
    private int idEquipamento; // ID do equipamento relacionado à manutenção
    private int idTecnico; // ID do técnico responsável pela manutenção
    private String tipo; // Tipo da manutenção (ex: preventiva, corretiva)
    private String descricao; // Descrição detalhada da manutenção
    private LocalDate data; // Data em que a manutenção foi realizada
    private String status; // Status da manutenção (ex: pendente, concluída)
    private String pecasSubstituidas; // Peças que foram substituídas durante a manutenção
    private int tempoInatividade; // Tempo em que o equipamento ficou inativo

    // Construtor sem argumentos
    public Manutencao() {
    }

    // Construtor com todos os parâmetros
    public Manutencao(int id, int idTecnico, int idEquipamento, String tipo, String descricao, LocalDate data,
            String status, String pecasSubstituidas, int tempoInatividade) {
        this.id = id; // Inicializa o ID da manutenção
        this.idTecnico = idTecnico; // Inicializa o ID do técnico
        this.idEquipamento = idEquipamento; // Inicializa o ID do equipamento
        this.tipo = tipo; // Inicializa o tipo de manutenção
        this.descricao = descricao; // Inicializa a descrição da manutenção
        this.data = data; // Inicializa a data da manutenção
        this.status = status; // Inicializa o status da manutenção
        this.pecasSubstituidas = pecasSubstituidas; // Inicializa as peças substituídas
        this.tempoInatividade = tempoInatividade; // Inicializa o tempo de inatividade
    }

    // Getters e Setters
    public int getId() {
        return id; // Retorna o ID da manutenção
    }

    public void setId(int id) {
        this.id = id; // Define o ID da manutenção
    }

    public int getEquipamentoId() { // Renomeado para refletir melhor o significado
        return idEquipamento; // Retorna o ID do equipamento
    }

    public void setEquipamentoId(int idEquipamento) { // Renomeado para refletir melhor o significado
        this.idEquipamento = idEquipamento; // Define o ID do equipamento
    }

    public String getTipo() {
        return tipo; // Retorna o tipo da manutenção
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; // Define o tipo da manutenção
    }

    public String getDescricao() {
        return descricao; // Retorna a descrição da manutenção
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao; // Define a descrição da manutenção
    }

    public LocalDate getDataManutencao() { // Renomeado para refletir melhor o significado
        return data; // Retorna a data da manutenção
    }

    public void setDataManutencao(LocalDate data) { // Renomeado para refletir melhor o significado
        this.data = data; // Define a data da manutenção
    }

    public String getStatus() {
        return status; // Retorna o status da manutenção
    }

    public void setStatus(String status) {
        this.status = status; // Define o status da manutenção
    }

    public String getPecasSubstituidas() {
        return pecasSubstituidas; // Retorna as peças que foram substituídas
    }

    public void setPecasSubstituidas(String pecasSubstituidas) {
        this.pecasSubstituidas = pecasSubstituidas; // Define as peças que foram substituídas
    }

    public int getTempoInatividade() {
        return tempoInatividade; // Retorna o tempo de inatividade
    }

    public void setTempoInatividade(int tempoInatividade) {
        this.tempoInatividade = tempoInatividade; // Define o tempo de inatividade
    }

    public int getIdEquipamento() {
        return idEquipamento; // Retorna o ID do equipamento (duplicado, pode ser removido)
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento; // Define o ID do equipamento (duplicado, pode ser removido)
    }

    public int getIdTecnico() {
        return idTecnico; // Retorna o ID do técnico
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico; // Define o ID do técnico
    }

    public LocalDate getData() {
        return data; // Retorna a data da manutenção
    }

    public void setData(LocalDate data) {
        this.data = data; // Define a data da manutenção
    }
}
