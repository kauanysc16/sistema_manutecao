package com.manutencao.connection; // Pacote onde a classe EquipamentoDAO está localizada

import java.sql.Connection; // Importa a classe Connection da biblioteca java.sql
import java.sql.PreparedStatement; // Importa a classe PreparedStatement para executar instruções SQL
import java.sql.ResultSet; // Importa a classe ResultSet para armazenar resultados de consultas SQL
import java.sql.SQLException; // Importa a classe SQLException para tratamento de erros SQL
import java.util.ArrayList; // Importa a classe ArrayList para manipulação de listas
import java.util.List; // Importa a interface List para listas

import com.manutencao.model.Equipamento; // Importa a classe Equipamento do modelo

public class EquipamentoDAO {

    private Connection connection; // Declara uma variável de conexão com o banco de dados

    // Construtor da classe EquipamentoDAO
    public EquipamentoDAO() {
        this.connection = new ConnectionFactory().getConnection(); // Obtém uma nova conexão usando a ConnectionFactory
    }

    // Método para salvar um novo equipamento no banco de dados
    public void salvar(Equipamento equipamento) throws SQLException {
        String sql = "INSERT INTO equipamento (aparelho, modelo, local) VALUES (?, ?, ?)"; // Comando SQL para inserir dados

        try (PreparedStatement stmt = connection.prepareStatement(sql)) { // Prepara a instrução SQL
            stmt.setString(1, equipamento.getAparelho()); // Define o valor do primeiro parâmetro
            stmt.setString(2, equipamento.getModelo()); // Define o valor do segundo parâmetro
            stmt.setString(3, equipamento.getLocal()); // Define o valor do terceiro parâmetro
            stmt.execute(); // Executa a instrução SQL
        }
    }

    // Método para atualizar um equipamento existente no banco de dados
    public void atualizar(Equipamento equipamento) {
        String sql = "UPDATE equipamento SET aparelho = ?, modelo = ?, local = ?, especificacoesTecnicas = ?, dataAquisicao = ? WHERE id = ?"; // Comando SQL para atualizar dados
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém uma nova conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            stmt.setString(1, equipamento.getAparelho()); // Define o valor do primeiro parâmetro
            stmt.setString(2, equipamento.getModelo()); // Define o valor do segundo parâmetro
            stmt.setString(3, equipamento.getLocal()); // Define o valor do terceiro parâmetro
            stmt.setString(4, equipamento.getEspecificacoesTecnicas()); // Define o valor do quarto parâmetro
            stmt.setDate(5, java.sql.Date.valueOf(equipamento.getDataAquisicao())); // Converte LocalDate para java.sql.Date e define o valor do quinto parâmetro
            stmt.setInt(6, equipamento.getId()); // Define o valor do sexto parâmetro (ID do equipamento)
            stmt.executeUpdate(); // Executa a instrução SQL de atualização
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para deletar um equipamento do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM equipamento WHERE id = ?"; // Comando SQL para deletar dados
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém uma nova conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            stmt.setInt(1, id); // Define o valor do parâmetro (ID do equipamento)
            stmt.executeUpdate(); // Executa a instrução SQL de deleção
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
    }

    // Método para buscar um equipamento pelo ID
    public Equipamento buscarPorId(int id) {
        String sql = "SELECT * FROM equipamento WHERE id = ?"; // Comando SQL para selecionar dados
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém uma nova conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            stmt.setInt(1, id); // Define o valor do parâmetro (ID do equipamento)
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém os resultados
            if (rs.next()) { // Se houver resultados
                Equipamento equipamento = new Equipamento(); // Cria uma nova instância de Equipamento
                equipamento.setId(rs.getInt("id")); // Define o ID do equipamento
                equipamento.setAparelho(rs.getString("aparelho")); // Define o aparelho do equipamento
                equipamento.setModelo(rs.getString("modelo")); // Define o modelo do equipamento
                equipamento.setLocal(rs.getString("local")); // Define o local do equipamento
                equipamento.setEspecificacoesTecnicas(rs.getString("especificacoesTecnicas")); // Define as especificações técnicas do equipamento
                equipamento.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate()); // Converte java.sql.Date para LocalDate e define a data de aquisição
                return equipamento; // Retorna o equipamento encontrado
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
        return null; // Retorna null se nenhum equipamento for encontrado
    }

    // Método para listar todos os equipamentos
    public List<Equipamento> listarTodos() {
        String sql = "SELECT * FROM equipamento"; // Comando SQL para selecionar todos os equipamentos
        List<Equipamento> equipamentos = new ArrayList<>(); // Cria uma lista para armazenar os equipamentos
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém uma nova conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém os resultados
            while (rs.next()) { // Para cada resultado
                Equipamento equipamento = new Equipamento(); // Cria uma nova instância de Equipamento
                equipamento.setId(rs.getInt("id")); // Define o ID do equipamento
                equipamento.setAparelho(rs.getString("aparelho")); // Define o aparelho do equipamento
                equipamento.setModelo(rs.getString("modelo")); // Define o modelo do equipamento
                equipamento.setLocal(rs.getString("local")); // Define o local do equipamento
                equipamento.setEspecificacoesTecnicas(rs.getString("especificacoesTecnicas")); // Define as especificações técnicas do equipamento
                equipamento.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate()); // Converte java.sql.Date para LocalDate e define a data de aquisição
                equipamentos.add(equipamento); // Adiciona o equipamento à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
        return equipamentos; // Retorna a lista de equipamentos
    }

    // Método para buscar equipamentos por local
    public List<Equipamento> buscarPorLocal(String local) {
        String sql = "SELECT * FROM equipamento WHERE local = ?"; // Comando SQL para selecionar equipamentos por local
        List<Equipamento> equipamentos = new ArrayList<>(); // Cria uma lista para armazenar os equipamentos
        try (Connection conn = new ConnectionFactory().getConnection(); // Obtém uma nova conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            stmt.setString(1, local); // Define o valor do parâmetro (local do equipamento)
            ResultSet rs = stmt.executeQuery(); // Executa a consulta e obtém os resultados
            while (rs.next()) { // Para cada resultado
                Equipamento equipamento = new Equipamento(); // Cria uma nova instância de Equipamento
                equipamento.setId(rs.getInt("id")); // Define o ID do equipamento
                equipamento.setAparelho(rs.getString("aparelho")); // Define o aparelho do equipamento
                equipamento.setModelo(rs.getString("modelo")); // Define o modelo do equipamento
                equipamento.setLocal(rs.getString("local")); // Define o local do equipamento
                equipamento.setEspecificacoesTecnicas(rs.getString("especificacoesTecnicas")); // Define as especificações técnicas do equipamento
                equipamento.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate()); // Converte java.sql.Date para LocalDate e define a data de aquisição
                equipamentos.add(equipamento); // Adiciona o equipamento à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
        }
        return equipamentos; // Retorna a lista de equipamentos encontrados
    }
}
