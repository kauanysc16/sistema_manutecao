package com.manutencao.connection;

import java.sql.Connection; // Importa a classe Connection para estabelecer a conexão com o banco de dados
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para executar comandos SQL
import java.sql.ResultSet; // Importa a classe ResultSet para manipular os resultados das consultas SQL
import java.sql.SQLException; // Importa a classe SQLException para tratar exceções relacionadas a SQL
import java.time.LocalDate; // Importa a classe LocalDate para manipulação de datas
import java.time.format.DateTimeFormatter; // Importa a classe DateTimeFormatter para formatação de datas
import java.util.ArrayList; // Importa a classe ArrayList para uso de listas dinâmicas
import java.util.List; // Importa a interface List para declaração de listas

import com.manutencao.model.Manutencao; // Importa a classe Manutencao que representa a entidade de manutenção

public class ManutencaoDAO {

    // Define o formato da data como "yyyy-MM-dd"
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

    // Método para salvar uma nova manutenção no banco de dados
    public void salvar(Manutencao manutencao) {
        // Comando SQL para inserir uma nova manutenção
        String sql = "INSERT INTO manutencao (equipamentoId, tipo, descricao, dataManutencao, status, pecasSubstituidas, tempoInatividade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Tenta estabelecer uma conexão e executar o comando SQL
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL

            // Define os parâmetros da consulta
            stmt.setInt(1, manutencao.getEquipamentoId()); // ID do equipamento
            stmt.setString(2, manutencao.getTipo()); // Tipo de manutenção
            stmt.setString(3, manutencao.getDescricao()); // Descrição da manutenção
            stmt.setString(4, manutencao.getDataManutencao().format(formatter)); // Data da manutenção formatada
            stmt.setString(5, manutencao.getStatus()); // Status da manutenção
            stmt.setString(6, manutencao.getPecasSubstituidas()); // Peças substituídas
            stmt.setInt(7, manutencao.getTempoInatividade()); // Tempo de inatividade
            stmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace(); // Imprime a stack trace para depuração
        }
    }

    // Método para atualizar uma manutenção existente no banco de dados
    public void atualizar(Manutencao manutencao) {
        // Comando SQL para atualizar a manutenção
        String sql = "UPDATE manutencao SET equipamentoId = ?, tipo = ?, descricao = ?, dataManutencao = ?, status = ?, pecasSubstituidas = ?, tempoInatividade = ? WHERE id = ?";
        
        // Tenta estabelecer uma conexão e executar o comando SQL
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            stmt.setInt(1, manutencao.getEquipamentoId());
            stmt.setString(2, manutencao.getTipo());
            stmt.setString(3, manutencao.getDescricao());
            stmt.setString(4, manutencao.getDataManutencao().format(formatter)); // Data formatada
            stmt.setString(5, manutencao.getStatus());
            stmt.setString(6, manutencao.getPecasSubstituidas());
            stmt.setInt(7, manutencao.getTempoInatividade());
            stmt.setInt(8, manutencao.getId()); // ID da manutenção que será atualizada
            stmt.executeUpdate(); // Executa a atualização
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
    }

    // Método para deletar uma manutenção do banco de dados pelo ID
    public void deletar(int id) {
        // Comando SQL para deletar uma manutenção
        String sql = "DELETE FROM manutencao WHERE id = ?";
        
        // Tenta estabelecer uma conexão e executar o comando SQL
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Define o ID da manutenção a ser deletada
            stmt.executeUpdate(); // Executa a deleção
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
    }

    // Método para buscar uma manutenção pelo ID
    public Manutencao buscarPorId(int id) {
        // Comando SQL para buscar a manutenção
        String sql = "SELECT * FROM manutencao WHERE id = ?";
        
        // Tenta estabelecer uma conexão e executar o comando SQL
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Define o ID da manutenção a ser buscada
            ResultSet rs = stmt.executeQuery(); // Executa a consulta
            if (rs.next()) { // Se houver resultados
                return mapResultSetToManutencao(rs); // Mapeia o resultado para um objeto Manutencao
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
        return null; // Retorna null se não encontrar a manutenção
    }

    // Método para listar todas as manutenções
    public List<Manutencao> listarTodos() {
        // Comando SQL para selecionar todas as manutenções
        String sql = "SELECT * FROM manutencao";
        List<Manutencao> manutencoes = new ArrayList<>(); // Cria uma lista para armazenar as manutenções
        
        // Tenta estabelecer uma conexão e executar o comando SQL
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) { // Itera sobre os resultados
                manutencoes.add(mapResultSetToManutencao(rs)); // Adiciona cada manutenção à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
        return manutencoes; // Retorna a lista de manutenções
    }

    // Método para listar manutenções por ID do equipamento
    public List<Manutencao> listarPorEquipamento(int equipamentoId) {
        // Comando SQL para selecionar manutenções de um equipamento específico
        String sql = "SELECT * FROM manutencao WHERE equipamentoId = ?";
        List<Manutencao> manutencoes = new ArrayList<>(); // Cria uma lista para armazenar as manutenções
        
        // Tenta estabelecer uma conexão e executar o comando SQL
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, equipamentoId); // Define o ID do equipamento
            ResultSet rs = stmt.executeQuery(); // Executa a consulta
            while (rs.next()) { // Itera sobre os resultados
                manutencoes.add(mapResultSetToManutencao(rs)); // Adiciona cada manutenção à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
        return manutencoes; // Retorna a lista de manutenções
    }

    // Método para listar manutenções por status
    public List<Manutencao> listarPorStatus(String status) {
        // Comando SQL para selecionar manutenções com um status específico
        String sql = "SELECT * FROM manutencao WHERE status = ?";
        List<Manutencao> manutencoes = new ArrayList<>(); // Cria uma lista para armazenar as manutenções
        
        // Tenta estabelecer uma conexão e executar o comando SQL
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status); // Define o status das manutenções a serem buscadas
            ResultSet rs = stmt.executeQuery(); // Executa a consulta
            while (rs.next()) { // Itera sobre os resultados
                manutencoes.add(mapResultSetToManutencao(rs)); // Adiciona cada manutenção à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
        return manutencoes; // Retorna a lista de manutenções
    }

    // Método para calcular o MTTR (Mean Time To Repair) das manutenções corretivas
    public double calcularMTTR() {
        // Comando SQL para calcular a média do tempo de inatividade das manutenções corretivas
        String sql = "SELECT AVG(tempoInatividade) AS mttr FROM manutencao WHERE tipo = 'Corretiva'";
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) { // Se houver resultados
                return rs.getDouble("mttr"); // Retorna o MTTR calculado
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
        return 0; // Retorna 0 se não houver resultados
    }

    // Método para calcular o MTBF (Mean Time Between Failures) dos equipamentos
    public double calcularMTBF() {
        // Comando SQL para calcular a média do tempo ativo dos equipamentos
        String sql = "SELECT (SUM(tempoAtivo) / COUNT(*)) AS mtbf FROM equipamento";
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) { // Se houver resultados
                return rs.getDouble("mtbf"); // Retorna o MTBF calculado
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceções SQL
        }
        return 0; // Retorna 0 se não houver resultados
    }

    // Método privado para mapear o ResultSet para um objeto Manutencao
    private Manutencao mapResultSetToManutencao(ResultSet rs) throws SQLException {
        Manutencao manutencao = new Manutencao(); // Cria um novo objeto Manutencao
        manutencao.setId(rs.getInt("id")); // Define o ID da manutenção
        manutencao.setEquipamentoId(rs.getInt("equipamentoId")); // Define o ID do equipamento
        manutencao.setTipo(rs.getString("tipo")); // Define o tipo de manutenção
        manutencao.setDescricao(rs.getString("descricao")); // Define a descrição da manutenção
        // Converte a data do formato String para LocalDate
        manutencao.setDataManutencao(LocalDate.parse(rs.getString("dataManutencao"), formatter)); 
        manutencao.setStatus(rs.getString("status")); // Define o status da manutenção
        manutencao.setPecasSubstituidas(rs.getString("pecasSubstituidas")); // Define as peças substituídas
        manutencao.setTempoInatividade(rs.getInt("tempoInatividade")); // Define o tempo de inatividade
        return manutencao; // Retorna o objeto Manutencao mapeado
    }
}
