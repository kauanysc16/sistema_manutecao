package com.manutencao.connection;

import java.sql.Connection; // Importa a classe Connection para estabelecer a conexão com o banco de dados
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para executar comandos SQL
import java.sql.ResultSet; // Importa a classe ResultSet para manipular os resultados das consultas SQL
import java.sql.SQLException; // Importa a classe SQLException para tratar exceções relacionadas a SQL
import java.util.ArrayList; // Importa a classe ArrayList para uso de listas dinâmicas
import java.util.List; // Importa a interface List para declaração de listas

import com.manutencao.model.Tecnico; // Importa a classe Tecnico que representa a entidade de técnico

public class TecnicoDAO {
    private Connection connection; // Declaração da conexão com o banco de dados

    // Construtor da classe que inicializa a conexão com o banco de dados
    public TecnicoDAO() {
        connection = new ConnectionFactory().getConnection(); // Inicializa a conexão com o banco de dados
    }

    // Método para salvar um novo técnico no banco de dados
    public void salvar(Tecnico tecnico) {
        String sql = "INSERT INTO tecnico (nome, especialidade, disponibilidade) VALUES (?, ?, ?)"; // Comando SQL para inserir um técnico
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL

            // Define os parâmetros da consulta
            stmt.setString(1, tecnico.getNome()); // Nome do técnico
            stmt.setString(2, tecnico.getEspecialidade()); // Especialidade do técnico
            stmt.setBoolean(3, tecnico.isDisponivel()); // Disponibilidade do técnico
            stmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
    }

    // Método para atualizar um técnico existente no banco de dados
    public void atualizar(Tecnico tecnico) {
        String sql = "UPDATE tecnico SET nome = ?, especialidade = ?, disponibilidade = ? WHERE id = ?"; // Comando SQL para atualizar um técnico
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL

            // Define os parâmetros da consulta
            stmt.setString(1, tecnico.getNome()); // Nome do técnico
            stmt.setString(2, tecnico.getEspecialidade()); // Especialidade do técnico
            stmt.setBoolean(3, tecnico.isDisponivel()); // Disponibilidade do técnico
            stmt.setInt(4, tecnico.getId()); // ID do técnico a ser atualizado
            stmt.executeUpdate(); // Executa a atualização
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
    }

    // Método para deletar um técnico do banco de dados pelo ID
    public void deletar(int id) {
        String sql = "DELETE FROM tecnico WHERE id = ?"; // Comando SQL para deletar um técnico
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL

            stmt.setInt(1, id); // Define o ID do técnico a ser deletado
            stmt.executeUpdate(); // Executa a deleção
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
    }

    // Método para buscar um técnico pelo ID
    public Tecnico buscarPorId(int id) {
        String sql = "SELECT * FROM tecnico WHERE id = ?"; // Comando SQL para buscar um técnico
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL

            stmt.setInt(1, id); // Define o ID do técnico a ser buscado
            ResultSet rs = stmt.executeQuery(); // Executa a consulta
            if (rs.next()) { // Se houver resultados
                Tecnico tecnico = new Tecnico(); // Cria um novo objeto Tecnico
                tecnico.setId(rs.getInt("id")); // Define o ID do técnico
                tecnico.setNome(rs.getString("nome")); // Define o nome do técnico
                tecnico.setEspecialidade(rs.getString("especialidade")); // Define a especialidade do técnico
                tecnico.setDisponivel(rs.getBoolean("disponibilidade")); // Define a disponibilidade do técnico
                return tecnico; // Retorna o objeto Tecnico encontrado
            }
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
        return null; // Retorna null se não encontrar o técnico
    }

    // Método para listar todos os técnicos
    public List<Tecnico> listarTodos() {
        String sql = "SELECT * FROM tecnico"; // Comando SQL para selecionar todos os técnicos
        List<Tecnico> tecnicos = new ArrayList<>(); // Cria uma lista para armazenar os técnicos
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL

            ResultSet rs = stmt.executeQuery(); // Executa a consulta
            while (rs.next()) { // Itera sobre os resultados
                Tecnico tecnico = new Tecnico(); // Cria um novo objeto Tecnico
                tecnico.setId(rs.getInt("id")); // Define o ID do técnico
                tecnico.setNome(rs.getString("nome")); // Define o nome do técnico
                tecnico.setEspecialidade(rs.getString("especialidade")); // Define a especialidade do técnico
                tecnico.setDisponivel(rs.getBoolean("disponibilidade")); // Define a disponibilidade do técnico
                tecnicos.add(tecnico); // Adiciona o técnico à lista
            }
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
        return tecnicos; // Retorna a lista de técnicos
    }

    // Método para listar técnicos pela sua disponibilidade
    public List<Tecnico> listarPorDisponibilidade(boolean disponibilidade) {
        String sql = "SELECT * FROM tecnico WHERE disponibilidade = ?"; // Comando SQL para selecionar técnicos pela disponibilidade
        List<Tecnico> tecnicos = new ArrayList<>(); // Cria uma lista para armazenar os técnicos
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL

            stmt.setBoolean(1, disponibilidade); // Define o parâmetro de disponibilidade
            ResultSet rs = stmt.executeQuery(); // Executa a consulta
            while (rs.next()) { // Itera sobre os resultados
                Tecnico tecnico = new Tecnico(); // Cria um novo objeto Tecnico
                tecnico.setId(rs.getInt("id")); // Define o ID do técnico
                tecnico.setNome(rs.getString("nome")); // Define o nome do técnico
                tecnico.setEspecialidade(rs.getString("especialidade")); // Define a especialidade do técnico
                tecnico.setDisponivel(rs.getBoolean("disponibilidade")); // Define a disponibilidade do técnico
                tecnicos.add(tecnico); // Adiciona o técnico à lista
            }
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
        return tecnicos; // Retorna a lista de técnicos
    }

    // Método para verificar a disponibilidade dos técnicos
    public List<Tecnico> verificarDisponibilidade() {
        List<Tecnico> tecnicosDisponiveis = new ArrayList<>(); // Lista para armazenar técnicos disponíveis
        String sql = "SELECT * FROM tecnico WHERE disponivel = true"; // Consulta SQL para selecionar técnicos disponíveis

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
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
        return tecnicosDisponiveis; // Retorna a lista de técnicos disponíveis
    }

    // Método para buscar técnicos por especialidade
    public List<Tecnico> buscarPorEspecialidade(String especialidade) {
        List<Tecnico> tecnicos = new ArrayList<>(); // Lista para armazenar técnicos encontrados
        String sql = "SELECT * FROM tecnico WHERE especialidade = ?"; // Consulta SQL para selecionar técnicos pela especialidade

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
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
        return tecnicos; // Retorna a lista de técnicos encontrados
    }
}
