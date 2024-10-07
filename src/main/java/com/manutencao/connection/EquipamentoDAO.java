package com.manutencao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manutencao.model.Equipamento;

public class EquipamentoDAO {

    public void salvar(Equipamento equipamento) {
        String sql = "INSERT INTO equipamento (aparelho, modelo, local, especificacoesTecnicas, dataAquisicao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getAparelho());
            stmt.setString(2, equipamento.getModelo());
            stmt.setString(3, equipamento.getLocal());
            stmt.setString(4, equipamento.getEspecificacoesTecnicas());
            stmt.setDate(5, java.sql.Date.valueOf(equipamento.getDataAquisicao())); // Converte LocalDate para java.sql.Date
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Equipamento equipamento) {
        String sql = "UPDATE equipamento SET aparelho = ?, modelo = ?, local = ?, especificacoesTecnicas = ?, dataAquisicao = ? WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getAparelho());
            stmt.setString(2, equipamento.getModelo());
            stmt.setString(3, equipamento.getLocal());
            stmt.setString(4, equipamento.getEspecificacoesTecnicas());
            stmt.setDate(5, java.sql.Date.valueOf(equipamento.getDataAquisicao())); // Converte LocalDate para java.sql.Date
            stmt.setInt(6, equipamento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM equipamento WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Equipamento buscarPorId(int id) {
        String sql = "SELECT * FROM equipamento WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setId(rs.getInt("id"));
                equipamento.setAparelho(rs.getString("aparelho"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setLocal(rs.getString("local"));
                equipamento.setEspecificacoesTecnicas(rs.getString("especificacoesTecnicas"));
                equipamento.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate()); // Converte java.sql.Date para LocalDate
                return equipamento;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Equipamento> listarTodos() {
        String sql = "SELECT * FROM equipamento";
        List<Equipamento> equipamentos = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setId(rs.getInt("id"));
                equipamento.setAparelho(rs.getString("aparelho"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setLocal(rs.getString("local"));
                equipamento.setEspecificacoesTecnicas(rs.getString("especificacoesTecnicas"));
                equipamento.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate()); // Converte java.sql.Date para LocalDate
                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }

    public List<Equipamento> buscarPorLocal(String local) {
        String sql = "SELECT * FROM equipamento WHERE local = ?";
        List<Equipamento> equipamentos = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, local);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setId(rs.getInt("id"));
                equipamento.setAparelho(rs.getString("aparelho"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setLocal(rs.getString("local"));
                equipamento.setEspecificacoesTecnicas(rs.getString("especificacoesTecnicas"));
                equipamento.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate()); // Converte java.sql.Date para LocalDate
                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }
}
