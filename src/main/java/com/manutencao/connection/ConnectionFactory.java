package com.manutencao.connection; // Pacote onde a classe ConnectionFactory está localizada

import java.sql.Connection; // Importa a classe Connection da biblioteca java.sql
import java.sql.DriverManager; // Importa a classe DriverManager da biblioteca java.sql
import java.sql.SQLException; // Importa a classe SQLException da biblioteca java.sql

public class ConnectionFactory {
    // URL de conexão com o banco de dados PostgreSQL
    private final String URL = "jdbc:postgresql://localhost:5432/sistema_manutencao"; // Define o endereço do banco de dados
    private final String USER = "postgres"; // Define o usuário do banco de dados
    private final String PASSWORD = "postgres"; // Define a senha do usuário

    // Construtor da classe ConnectionFactory
    public ConnectionFactory() {
        try {
            // Carrega o driver do PostgreSQL
            Class.forName("org.postgresql.Driver"); // Tenta carregar a classe do driver
        } catch (ClassNotFoundException e) {
            // Caso a classe do driver não seja encontrada, imprime a stack trace do erro
            e.printStackTrace();
        }
    }

    // Método para obter uma nova conexão com o banco de dados
    public Connection getConnection() {
        try {
            // Retorna uma nova conexão usando a URL, usuário e senha
            return DriverManager.getConnection(URL, USER, PASSWORD); 
        } catch (SQLException e) {
            // Caso ocorra um erro ao tentar obter a conexão, imprime a stack trace do erro
            e.printStackTrace();
            return null; // Retorna null se a conexão não puder ser estabelecida
        }
    }
}
