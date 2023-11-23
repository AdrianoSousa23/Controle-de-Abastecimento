package Examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConectaMySQL;
import entities.Veiculo;

//Consulta pelo nome completo do aluno registrado no Banco de dados
public class ExemploConsultaCarro{
    static Connection con = null;

    public static void main(String[] args) throws Exception {
        Veiculo veiculo = new ExemploConsultaCarro().consultarPlaca(JOptionPane.showInputDialog("Digite a placa a ser consultado: "));
    }

    static Veiculo consultarPlaca(String nome) throws Exception {
        Veiculo veiculo = null;
        String querycmd = "select * from veiculo where " + "placaDoCarro like ? ";

        try {
            con = ConectaMySQL.openDB();
            PreparedStatement ps1 = con.prepareStatement(querycmd);
            ps1.setString(1, (nome != null ? nome.trim() : ""));
            ResultSet rs = ps1.executeQuery();
            while(rs.next()) {
                String placaDoCarro = rs.getString("placaDoCarro");
               veiculo = new Veiculo(placaDoCarro);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            ConectaMySQL.closeDB();
        }
        return veiculo;
    }
}