package com.manutencao.controller;

import java.util.List; // Importa a interface List para uso de listas

import com.manutencao.connection.TecnicoDAO; // Importa a classe TecnicoDAO que gerencia a persistência de Tecnico
import com.manutencao.model.Tecnico; // Importa a classe Tecnico que representa a entidade de técnico

public class TecnicoController {
    private TecnicoDAO tecnicoDAO; // Atributo para acesso ao DAO

    // Construtor que inicializa o DAO
    public TecnicoController() {
        tecnicoDAO = new TecnicoDAO(); // Inicializa o TecnicoDAO
    }

    // Método para salvar um novo técnico
    public void salvarTecnico(Tecnico tecnico) {
        tecnicoDAO.salvar(tecnico); // Salva o técnico no banco de dados
    }

    // Método para atualizar um técnico existente
    public void atualizarTecnico(Tecnico tecnico) {
        tecnicoDAO.atualizar(tecnico); // Atualiza o técnico no banco de dados
    }

    // Método para deletar um técnico pelo ID
    public void deletarTecnico(int id) {
        tecnicoDAO.deletar(id); // Deleta o técnico pelo ID
    }

    // Método para buscar um técnico pelo ID
    public Tecnico buscarTecnicoPorId(int id) {
        return tecnicoDAO.buscarPorId(id); // Busca o técnico pelo ID
    }

    // Método para listar todos os técnicos
    public List<Tecnico> listarTodosTecnicos() {
        return tecnicoDAO.listarTodos(); // Lista todos os técnicos
    }

    // Método adicional para buscar técnicos por especialidade
    public List<Tecnico> buscarTecnicosPorEspecialidade(String especialidade) {
        return tecnicoDAO.buscarPorEspecialidade(especialidade); // Busca técnicos pela especialidade
    }

    // Método adicional para verificar a disponibilidade dos técnicos
    public List<Tecnico> verificarDisponibilidade() {
        return tecnicoDAO.verificarDisponibilidade(); // Verifica a disponibilidade dos técnicos
    }
}
