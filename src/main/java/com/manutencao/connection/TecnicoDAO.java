package com.manutencao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manutencao.model.Tecnico;

public class TecnicoDAO {
    private Connection connection;

    public TecnicoDAO() {
        connection = new ConnectionFactory().getConnection(); // Inicializa a conexão com o banco de dados
    }

    public void salvar(Tecnico tecnico) {
        String sql = "INSERT INTO tecnico (nome, especialidade, disponibilidade) VALUES (?, ?, ?)";
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());
            stmt.setBoolean(3, tecnico.isDisponivel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Tecnico tecnico) {
        String sql = "UPDATE tecnico SET nome = ?, especialidade = ?, disponibilidade = ? WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());
            stmt.setBoolean(3, tecnico.isDisponivel());
            stmt.setInt(4, tecnico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tecnico WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Tecnico buscarPorId(int id) {
        String sql = "SELECT * FROM tecnico WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tecnico tecnico = new Tecnico();
                tecnico.setId(rs.getInt("id"));
                tecnico.setNome(rs.getString("nome"));
                tecnico.setEspecialidade(rs.getString("especialidade"));
                tecnico.setDisponivel(rs.getBoolean("disponibilidade"));
                return tecnico;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Tecnico> listarTodos() {
        String sql = "SELECT * FROM tecnico";
        List<Tecnico> tecnicos = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tecnico tecnico = new Tecnico();
                tecnico.setId(rs.getInt("id"));
                tecnico.setNome(rs.getString("nome"));
                tecnico.setEspecialidade(rs.getString("especialidade"));
                tecnico.setDisponivel(rs.getBoolean("disponibilidade"));
                tecnicos.add(tecnico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tecnicos;
    }

    public List<Tecnico> listarPorDisponibilidade(boolean disponibilidade) {
        String sql = "SELECT * FROM tecnico WHERE disponibilidade = ?";
        List<Tecnico> tecnicos = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, disponibilidade);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tecnico tecnico = new Tecnico();
                tecnico.setId(rs.getInt("id"));
                tecnico.setNome(rs.getString("nome"));
                tecnico.setEspecialidade(rs.getString("especialidade"));
                tecnico.setDisponivel(rs.getBoolean("disponibilidade"));
                tecnicos.add(tecnico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tecnicos;
    }

    public List<Tecnico> verificarDisponibilidade() {
        List<Tecnico> tecnicosDisponiveis = new ArrayList<>(); // Lista para armazenar técnicos disponíveis
        String sql = "SELECT * FROM tecnico WHERE disponivel = true"; // Consulta SQL para selecionar técnicos
                                                                      // disponíveis

        try (PreparedStatement statement = connection.prepareStatement(sql); // Prepara a consulta
                ResultSet resultSet = statement.executeQuery()) { // Executa a consulta

            while (resultSet.next()) { // Enquanto houver resultados
                Tecnico tecnico = new Tecnico(); // Cria um novo objeto Tecnico
                tecnico.setId(resultSet.getInt("id")); // Define o ID do técnico
                tecnico.setNome(resultSet.getString("nome")); // Define o nome do técnico
                tecnico.setEspecialidade(resultSet.getString("especialidade")); // Define a especialidade do técnico
                tecnico.setDisponivel(resultSet.getBoolean("disponivel")); // Define a disponibilidade do técnico
                tecnicosDisponiveis.add(tecnico); // Adiciona o técnico à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace do erro
        }
        return tecnicosDisponiveis; // Retorna a lista de técnicos disponíveis
    }

    public List<Tecnico> buscarPorEspecialidade(String especialidade) {
        List<Tecnico> tecnicos = new ArrayList<>(); // Lista para armazenar técnicos encontrados
        String sql = "SELECT * FROM tecnico WHERE especialidade = ?"; // Consulta SQL para selecionar técnicos pela
                                                                      // especialidade

        try (PreparedStatement statement = connection.prepareStatement(sql)) { // Prepara a consulta
            statement.setString(1, especialidade); // Define o parâmetro da consulta
            try (ResultSet resultSet = statement.executeQuery()) { // Executa a consulta
                while (resultSet.next()) { // Enquanto houver resultados
                    Tecnico tecnico = new Tecnico(); // Cria um novo objeto Tecnico
                    tecnico.setId(resultSet.getInt("id")); // Define o ID do técnico
                    tecnico.setNome(resultSet.getString("nome")); // Define o nome do técnico
                    tecnico.setEspecialidade(resultSet.getString("especialidade")); // Define a especialidade do técnico
                    tecnico.setDisponivel(resultSet.getBoolean("disponivel")); // Define a disponibilidade do técnico
                    tecnicos.add(tecnico); // Adiciona o técnico à lista
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace do erro
        }
        return tecnicos; // Retorna a lista de técnicos encontrados
    }
}
