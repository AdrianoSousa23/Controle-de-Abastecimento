package Examples;

import connection.Conectabanco;
import connection.ConectaMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ExemploPreparedStatement {
    public static void main(String[] args) {
        try {
            ConectaMySQL conexao = new ConectaMySQL(); 
            Connection cn = conexao.openDB();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Veiculo (placa,  modelo, anoDeFabricacao)"
            + "VALUES (?, ?, ?)");

            ps.setString(1, "EUVI123"); //placa
            ps.setString(2, "Corsa"); //modelo
            ps.setInt(3, Integer.parseInt("2014")); //anoDeFabricacao

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");

            ps.close();
            cn.close();

            System.out.println("Conexão encerrada");
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a operação.");
            e.printStackTrace();
        }
    }
}