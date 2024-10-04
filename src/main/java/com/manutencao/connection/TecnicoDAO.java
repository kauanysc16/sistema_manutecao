package com.manutencao.connection; // Pacote onde a classe TecnicoDAO está localizada

import com.manutencao.model.Tecnico; // Importa a classe Tecnico do modelo

import java.sql.*; // Importa todas as classes da biblioteca java.sql
import java.util.ArrayList; // Importa a classe ArrayList da biblioteca java.util
import java.util.List; // Importa a classe List da biblioteca java.util

public class TecnicoDAO {
    private ConnectionFactory connectionFactory; // Instância de ConnectionFactory para gerenciar conexões com o banco de dados

    // Construtor da classe TecnicoDAO
    public TecnicoDAO() {
        connectionFactory = new ConnectionFactory(); // Inicializa a ConnectionFactory
    }

    // Método para salvar um novo técnico no banco de dados
    public void salvar(Tecnico tecnico) {
        String sql = "INSERT INTO tecnico (nome, especialidade) VALUES (?, ?)"; // SQL para inserção
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setString(1, tecnico.getNome()); // Define o valor do primeiro parâmetro (nome do técnico)
            pstmt.setString(2, tecnico.getEspecialidade()); // Define o valor do segundo parâmetro (especialidade do técnico)
            pstmt.executeUpdate(); // Executa a atualização no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para atualizar um técnico existente no banco de dados
    public void atualizar(Tecnico tecnico) {
        String sql = "UPDATE tecnico SET nome = ?, especialidade = ? WHERE id = ?"; // SQL para atualização
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setString(1, tecnico.getNome()); // Define o valor do primeiro parâmetro (nome do técnico)
            pstmt.setString(2, tecnico.getEspecialidade()); // Define o valor do segundo parâmetro (especialidade do técnico)
            pstmt.setInt(3, tecnico.getId()); // Define o valor do terceiro parâmetro (ID do técnico)
            pstmt.executeUpdate(); // Executa a atualização no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para deletar um técnico do banco de dados pelo ID
    public void deletar(int id) {
        String sql = "DELETE FROM tecnico WHERE id = ?"; // SQL para exclusão
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o valor do parâmetro (ID do técnico a ser excluído)
            pstmt.executeUpdate(); // Executa a exclusão no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para buscar um técnico pelo ID
    public Tecnico buscarPorId(int id) {
        Tecnico tecnico = null; // Inicializa a variável que irá armazenar o técnico encontrado
        String sql = "SELECT * FROM tecnico WHERE id = ?"; // SQL para busca
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            pstmt.setInt(1, id); // Define o valor do parâmetro (ID do técnico a ser buscado)
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta
            if (rs.next()) { // Se houver um resultado
                // Cria um novo objeto Tecnico com os dados retornados
                tecnico = new Tecnico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
        return tecnico; // Retorna o técnico encontrado ou null se não encontrado
    }

    // Método para listar todos os técnicos do banco de dados
    public List<Tecnico> listarTodos() {
        List<Tecnico> tecnicos = new ArrayList<>(); // Cria uma lista para armazenar os técnicos
        String sql = "SELECT * FROM tecnico"; // SQL para listar todos os técnicos
        try (Connection conn = connectionFactory.getConnection(); // Obtém a conexão
             PreparedStatement pstmt = conn.prepareStatement(sql); // Prepara a instrução SQL
             ResultSet rs = pstmt.executeQuery()) { // Executa a consulta
            while (rs.next()) { // Enquanto houver resultados
                // Cria um novo objeto Tecnico com os dados retornados
                Tecnico tecnico = new Tecnico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"));
                tecnicos.add(tecnico); // Adiciona o técnico à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
        return tecnicos; // Retorna a lista de técnicos
    }
}
