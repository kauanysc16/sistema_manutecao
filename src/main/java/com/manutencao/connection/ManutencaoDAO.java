package com.manutencao.connection;

import java.sql.Connection; // Importa a classe Connection para estabelecer a conexão com o banco de dados
import java.sql.Date;
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para executar comandos SQL
import java.sql.ResultSet; // Importa a classe ResultSet para manipular os resultados das consultas SQL
import java.sql.SQLException; // Importa a classe SQLException para tratar exceções relacionadas a SQL
import java.util.ArrayList; // Importa a classe ArrayList para uso de listas
import java.util.List; // Importa a classe List para uso de listas dinâmicas

import com.manutencao.model.Manutencao; // Importa a classe Manutencao que representa a entidade de manutenção

public class ManutencaoDAO {

    public void salvar(Manutencao manutencao) throws SQLException {
        String sql = "INSERT INTO manutencao (idEquipamento, tipo, descricao, dataManutencao, status, pecasSubstituidas, tempoInatividade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, manutencao.getIdEquipamento());
            stmt.setString(2, manutencao.getTipo());
            stmt.setString(3, manutencao.getDescricao());
            stmt.setDate(4, java.sql.Date.valueOf(manutencao.getDataManutencao())); // Converte LocalDate para java.sql.Date
            stmt.setString(5, manutencao.getStatus());
            stmt.setString(6, manutencao.getPecasSubstituidas());
            stmt.setInt(7, manutencao.getTempoInatividade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Manutencao manutencao) throws SQLException {
        String sql = "UPDATE manutencao SET idEquipamento = ?, tipo = ?, descricao = ?, dataManutencao = ?, status = ?, pecasSubstituidas = ?, tempoInatividade = ? WHERE id = ?";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, manutencao.getIdEquipamento());
            stmt.setString(2, manutencao.getTipo());
            stmt.setString(3, manutencao.getDescricao());
            stmt.setDate(4, java.sql.Date.valueOf(manutencao.getDataManutencao())); // Converte LocalDate para java.sql.Date
            stmt.setString(5, manutencao.getStatus());
            stmt.setString(6, manutencao.getPecasSubstituidas());
            stmt.setInt(7, manutencao.getTempoInatividade());
            stmt.setInt(8, manutencao.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM manutencao WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Manutencao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM manutencao WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToManutencao(rs);
            }
        }
        return null;
    }

    public List<Manutencao> listarTodos() throws SQLException {
        List<Manutencao> manutencoes = new ArrayList<>();
        String sql = "SELECT * FROM manutencao";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                manutencoes.add(mapResultSetToManutencao(rs));
            }
        }
        return manutencoes;
    }

    public List<Manutencao> listarPorEquipamento(int idEquipamento) throws SQLException {
        List<Manutencao> manutencoes = new ArrayList<>();
        String sql = "SELECT * FROM manutencao WHERE idEquipamento = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                manutencoes.add(mapResultSetToManutencao(rs));
            }
        }
        return manutencoes;
    }

    // Método privado para mapear o ResultSet para um objeto Manutencao
    private Manutencao mapResultSetToManutencao(ResultSet rs) throws SQLException {
        Manutencao manutencao = new Manutencao(); // Cria um novo objeto Manutencao
        manutencao.setId(rs.getInt("id")); // Define o ID da manutenção
        manutencao.setIdEquipamento(rs.getInt("idEquipamento")); // Define o ID do equipamento
        manutencao.setTipo(rs.getString("tipo")); // Define o tipo de manutenção
        manutencao.setDescricao(rs.getString("descricao")); // Define a descrição da manutenção
        
        // Obtém a data como java.sql.Date e converte para LocalDate
        Date dataSql = rs.getDate("dataManutencao"); // Obtém a data do ResultSet
        if (dataSql != null) {
            manutencao.setDataManutencao(dataSql.toLocalDate()); // Converte para LocalDate
        } else {
            manutencao.setDataManutencao(null); // Define como null se a data for nula
        }
    
        manutencao.setStatus(rs.getString("status")); // Define o status da manutenção
        manutencao.setPecasSubstituidas(rs.getString("pecasSubstituidas")); // Define as peças substituídas
        manutencao.setTempoInatividade(rs.getInt("tempoInatividade")); // Define o tempo de inatividade
        return manutencao; // Retorna o objeto Manutencao mapeado
    }
}
