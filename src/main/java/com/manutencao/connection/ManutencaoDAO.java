package com.manutencao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.manutencao.model.Manutencao;

public class ManutencaoDAO {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Defina seu formato desejado

    public void salvar(Manutencao manutencao) {
        String sql = "INSERT INTO manutencao (equipamentoId, tipo, descricao, dataManutencao, status, pecasSubstituidas, tempoInatividade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, manutencao.getEquipamentoId());
            stmt.setString(2, manutencao.getTipo());
            stmt.setString(3, manutencao.getDescricao());
            stmt.setString(4, manutencao.getDataManutencao().format(formatter)); // Conversão de LocalDate para String
            stmt.setString(5, manutencao.getStatus());
            stmt.setString(6, manutencao.getPecasSubstituidas());
            stmt.setInt(7, manutencao.getTempoInatividade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Manutencao manutencao) {
        String sql = "UPDATE manutencao SET equipamentoId = ?, tipo = ?, descricao = ?, dataManutencao = ?, status = ?, pecasSubstituidas = ?, tempoInatividade = ? WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, manutencao.getEquipamentoId());
            stmt.setString(2, manutencao.getTipo());
            stmt.setString(3, manutencao.getDescricao());
            stmt.setString(4, manutencao.getDataManutencao().format(formatter)); // Conversão de LocalDate para String
            stmt.setString(5, manutencao.getStatus());
            stmt.setString(6, manutencao.getPecasSubstituidas());
            stmt.setInt(7, manutencao.getTempoInatividade());
            stmt.setInt(8, manutencao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM manutencao WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Manutencao buscarPorId(int id) {
        String sql = "SELECT * FROM manutencao WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToManutencao(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Manutencao> listarTodos() {
        String sql = "SELECT * FROM manutencao";
        List<Manutencao> manutencoes = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                manutencoes.add(mapResultSetToManutencao(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manutencoes;
    }

    public List<Manutencao> listarPorEquipamento(int equipamentoId) {
        String sql = "SELECT * FROM manutencao WHERE equipamentoId = ?";
        List<Manutencao> manutencoes = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, equipamentoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                manutencoes.add(mapResultSetToManutencao(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manutencoes;
    }

    public List<Manutencao> listarPorStatus(String status) {
        String sql = "SELECT * FROM manutencao WHERE status = ?";
        List<Manutencao> manutencoes = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                manutencoes.add(mapResultSetToManutencao(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manutencoes;
    }

    public double calcularMTTR() {
        String sql = "SELECT AVG(tempoInatividade) AS mttr FROM manutencao WHERE tipo = 'Corretiva'";
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                return rs.getDouble("mttr");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double calcularMTBF() {
        String sql = "SELECT (SUM(tempoAtivo) / COUNT(*)) AS mtbf FROM equipamento";
        try (Connection conn = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
             
            if (rs.next()) {
                return rs.getDouble("mtbf");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Manutencao mapResultSetToManutencao(ResultSet rs) throws SQLException {
        Manutencao manutencao = new Manutencao();
        manutencao.setId(rs.getInt("id"));
        manutencao.setEquipamentoId(rs.getInt("equipamentoId"));
        manutencao.setTipo(rs.getString("tipo"));
        manutencao.setDescricao(rs.getString("descricao"));
        // Converter a data do formato String para LocalDate
        manutencao.setDataManutencao(LocalDate.parse(rs.getString("dataManutencao"), formatter)); 
        manutencao.setStatus(rs.getString("status"));
        manutencao.setPecasSubstituidas(rs.getString("pecasSubstituidas"));
        manutencao.setTempoInatividade(rs.getInt("tempoInatividade"));
        return manutencao;
    }
}
