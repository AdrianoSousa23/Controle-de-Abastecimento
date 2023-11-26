package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectaMySQL {
    private final static String url = "jdbc:mysql://localhost:3306/AbastecimentosVeiculos";
    private final static String username = "root";
    private final static String password = "2022"; // verifica a senha
    private static Connection con;
    private static Statement stmt;
    private ResultSet rs;

    public static void main(String args[]){
        ConectaMySQL b = new ConectaMySQL();
        b.openDB();
        b.closeDB();
    }

    public static Connection openDB(){
        try{
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            System.out.println("\nConexão estabelecida com sucesso!\n");
        }catch(Exception e){
            System.out.println("\nNão foi possível estabelecer conexão " + e + "\n");
            System.exit(1);
        }
        return con;
    }
    
    public static void closeDB(){
        try{
            con.close();
        }catch(Exception e){
            System.out.println("\nNão foi possível fechar conexão " + e + "\n");
            System.exit(1);
        }
    }
}