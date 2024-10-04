package com.manutencao.connection; // Pacote onde a classe EquipamentoDAO está localizada

import java.sql.Connection; // Importa a classe Connection da biblioteca java.sql
import java.sql.PreparedStatement; // Importa a classe PreparedStatement da biblioteca java.sql
import java.sql.ResultSet; // Importa a classe ResultSet da biblioteca java.sql
import java.sql.SQLException; // Importa a classe SQLException da biblioteca java.sql
import java.util.ArrayList; // Importa a classe ArrayList da biblioteca java.util
import java.util.List; // Importa a classe List da biblioteca java.util

import com.manutencao.model.Equipamento; // Importa a classe Equipamento do modelo

public class EquipamentoDAO {
    private ConnectionFactory connectionFactory; // Instância de ConnectionFactory para gerenciar conexões com o banco de dados

    // Construtor da classe EquipamentoDAO
    public EquipamentoDAO() {
        connectionFactory = new ConnectionFactory(); // Inicializa a ConnectionFactory
    }

    // Método para salvar um novo equipamento no banco de dados
    public void salvar(Equipamento equipamento) {
        String sql = "INSERT INTO equipamento (aparelho, modelo, local) VALUES (?, ?, ?)"; // SQL para inserção
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setString(1, equipamento.getAparelho()); // Define o valor do primeiro parâmetro
            pstmt.setString(2, equipamento.getModelo()); // Define o valor do segundo parâmetro
            pstmt.setString(3, equipamento.getLocal()); // Define o valor do terceiro parâmetro
            pstmt.executeUpdate(); // Executa a atualização no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para atualizar um equipamento existente no banco de dados
    public void atualizar(Equipamento equipamento) {
        String sql = "UPDATE equipamento SET aparelho = ?, modelo = ?, local = ? WHERE id = ?"; // SQL para atualização
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setString(1, equipamento.getAparelho()); // Define o valor do primeiro parâmetro
            pstmt.setString(2, equipamento.getModelo()); // Define o valor do segundo parâmetro
            pstmt.setString(3, equipamento.getLocal()); // Define o valor do terceiro parâmetro
            pstmt.setInt(4, equipamento.getId()); // Define o valor do quarto parâmetro (ID do equipamento)
            pstmt.executeUpdate(); // Executa a atualização no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para deletar um equipamento do banco de dados pelo ID
    public void deletar(int id) {
        String sql = "DELETE FROM equipamento WHERE id = ?"; // SQL para exclusão
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o valor do parâmetro (ID do equipamento a ser excluído)
            pstmt.executeUpdate(); // Executa a exclusão no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para buscar um equipamento pelo ID
    public Equipamento buscarPorId(int id) {
        Equipamento equipamento = null; // Inicializa a variável que irá armazenar o equipamento encontrado
        String sql = "SELECT * FROM equipamento WHERE id = ?"; // SQL para busca
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o valor do parâmetro (ID do equipamento a ser buscado)
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta
            if (rs.next()) { // Se houver um resultado
                // Cria um novo objeto Equipamento com os dados retornados
                equipamento = new Equipamento(
                        rs.getInt("id"),
                        rs.getString("aparelho"),
                        rs.getString("modelo"),
                        rs.getString("local"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
        return equipamento; // Retorna o equipamento encontrado ou null se não encontrado
    }

    // Método para listar todos os equipamentos do banco de dados
    public List<Equipamento> listarTodos() {
        List<Equipamento> equipamentos = new ArrayList<>(); // Cria uma lista para armazenar os equipamentos
        String sql = "SELECT * FROM equipamento"; // SQL para listar todos os equipamentos
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql); // Prepara a instrução SQL
             ResultSet rs = pstmt.executeQuery()) { // Executa a consulta
            while (rs.next()) { // Enquanto houver resultados
                // Cria um novo objeto Equipamento com os dados retornados
                Equipamento equipamento = new Equipamento(
                        rs.getInt("id"),
                        rs.getString("aparelho"),
                        rs.getString("modelo"),
                        rs.getString("local"));
                equipamentos.add(equipamento); // Adiciona o equipamento à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
        return equipamentos; // Retorna a lista de equipamentos
    }
}
