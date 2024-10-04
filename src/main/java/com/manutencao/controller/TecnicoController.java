package com.manutencao.controller; // Pacote onde a classe TecnicoController está localizada

import java.util.List; // Importa a classe List da biblioteca java.util

import com.manutencao.connection.TecnicoDAO; // Importa a classe TecnicoDAO para operações de banco de dados
import com.manutencao.model.Tecnico; // Importa a classe Tecnico do modelo

public class TecnicoController {
    private TecnicoDAO tecnicoDAO; // Instância de TecnicoDAO para gerenciar operações de banco de dados

    // Construtor da classe TecnicoController
    public TecnicoController() {
        tecnicoDAO = new TecnicoDAO(); // Inicializa a TecnicoDAO
    }

    // Método para salvar um novo técnico
    public void salvarTecnico(Tecnico tecnico) {
        tecnicoDAO.salvar(tecnico); // Chama o método salvar da TecnicoDAO
    }

    // Método para atualizar um técnico existente
    public void atualizarTecnico(Tecnico tecnico) {
        tecnicoDAO.atualizar(tecnico); // Chama o método atualizar da TecnicoDAO
    }

    // Método para deletar um técnico pelo ID
    public void deletarTecnico(int id) {
        tecnicoDAO.deletar(id); // Chama o método deletar da TecnicoDAO
    }

    // Método para buscar um técnico pelo ID
    public Tecnico buscarTecnicoPorId(int id) {
        return tecnicoDAO.buscarPorId(id); // Chama o método buscarPorId da TecnicoDAO
    }

    // Método para listar todos os técnicos
    public List<Tecnico> listarTodosTecnicos() {
        return tecnicoDAO.listarTodos(); // Chama o método listarTodos da TecnicoDAO
    }
}
