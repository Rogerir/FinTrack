package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/fintrack_db?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "Banco_Dados@";

    private static Connection conexao;

    private Conexao() {}

    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                // Carrega o driver JDBC do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                System.out.println("Conexão com o fintrack_db realizada com sucesso!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver do MySQL não encontrado! Verifique suas dependências.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados na porta 3306.");
            e.printStackTrace();
        }
        return conexao;
    }
}