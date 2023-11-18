package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conectabanco {
    private final static String url = "jdbc:mysql://localhost:3306/AbastecimentosVeiculos";
    private final static String username = "root";
    private final static String password = "2022";
    static Connection con;

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Conectabanco cb = new Conectabanco();
        con = cb.getConexao();
    }

    public static Connection getConexao() throws SQLException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        try {
            con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar com a base de dados" + e.getMessage());
        }
    }

    public static void fechaConexao(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Fechada a conexao com o banco de dados");
            }
        } catch (Exception e) {
            System.out.println("Nao foi possivel fechar a conexao com o banco de dados " + e.getMessage());
        }
    }

    public static void fechaConexao(Connection conn, PreparedStatement stmt) {
        try {
            if (conn != null) {
                fechaConexao(conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Nao foi possavel fechar o statement " + e.getMessage());
        }
    }

    public static void fechaConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (conn != null || stmt != null) {
                fechaConexao(conn, stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Nao foi possivel fechar o ResultSet " + e.getMessage());
        }
    }

    public static Connection getConexao(String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado");
            return con;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com a base de dados" + e.getMessage());
        }
        return null;
    }
}