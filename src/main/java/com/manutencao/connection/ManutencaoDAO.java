package com.manutencao.connection; // Pacote onde a classe ManutencaoDAO está localizada

import com.manutencao.model.Manutencao; // Importa a classe Manutencao do modelo

import java.sql.*; // Importa todas as classes da biblioteca java.sql
import java.util.ArrayList; // Importa a classe ArrayList da biblioteca java.util
import java.util.List; // Importa a classe List da biblioteca java.util

public class ManutencaoDAO {
    private ConnectionFactory connectionFactory; // Instância de ConnectionFactory para gerenciar conexões com o banco de dados

    // Consultas SQL como constantes
    private static final String SQL_INSERT = "INSERT INTO manutencao (id_tecnico, id_equipamento, data, descricao, status) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE manutencao SET id_tecnico = ?, id_equipamento = ?, data = ?, descricao = ?, status = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM manutencao WHERE id = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM manutencao WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM manutencao";
    private static final String SQL_UPDATE_STATUS = "UPDATE manutencao SET status = ? WHERE id = ?";

    // Construtor da classe ManutencaoDAO
    public ManutencaoDAO() {
        connectionFactory = new ConnectionFactory(); // Inicializa a ConnectionFactory
    }

    // Método para salvar uma nova manutenção no banco de dados
    public void salvar(Manutencao manutencao) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
            pstmt.setInt(1, manutencao.getIdTecnico());
            pstmt.setInt(2, manutencao.getIdEquipamento());
            pstmt.setTimestamp(3, Timestamp.valueOf(manutencao.getData()));
            pstmt.setString(4, manutencao.getDescricao());
            pstmt.setString(5, manutencao.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Considere lançar uma exceção personalizada ou registrar o erro
        }
    }

    // Método para atualizar uma manutenção existente no banco de dados
    public void atualizar(Manutencao manutencao) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {
            pstmt.setInt(1, manutencao.getIdTecnico());
            pstmt.setInt(2, manutencao.getIdEquipamento());
            pstmt.setTimestamp(3, Timestamp.valueOf(manutencao.getData()));
            pstmt.setString(4, manutencao.getDescricao());
            pstmt.setString(5, manutencao.getStatus());
            pstmt.setInt(6, manutencao.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Considere lançar uma exceção personalizada ou registrar o erro
        }
    }

    // Método para deletar uma manutenção do banco de dados pelo ID
    public void deletar(int id) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Considere lançar uma exceção personalizada ou registrar o erro
        }
    }

    // Método para buscar uma manutenção pelo ID
    public Manutencao buscarPorId(int id) {
        Manutencao manutencao = null;
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    manutencao = new Manutencao(
                            rs.getInt("id"),
                            rs.getInt("id_tecnico"),
                            rs.getInt("id_equipamento"),
                            rs.getTimestamp("data").toLocalDateTime(),
                            rs.getString("descricao"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considere lançar uma exceção personalizada ou registrar o erro
        }
        return manutencao;
    }

    // Método para listar todas as manutenções do banco de dados
    public List<Manutencao> listarTodos() {
        List<Manutencao> manutencoes = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Manutencao manutencao = new Manutencao(
                        rs.getInt("id"),
                        rs.getInt("id_tecnico"),
                        rs.getInt("id_equipamento"),
                        rs.getTimestamp("data").toLocalDateTime(),
                        rs.getString("descricao"),
                        rs.getString("status")
                );
                manutencoes.add(manutencao);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considere lançar uma exceção personalizada ou registrar o erro
        }
        return manutencoes;
    }

    // Método para atualizar o status de uma manutenção
    public void atualizarStatus(int id, String status) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_STATUS)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Considere lançar uma exceção personalizada ou registrar o erro
        }
    }
}
