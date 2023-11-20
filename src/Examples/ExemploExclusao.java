package Examples;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.ConectaMySQL;
import entities.Veiculo;

public class ExemploExclusao {
    public static void main(String[] args) {
        try {
            realizaOperacao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void realizaOperacao() throws Exception{
        Connection cn = null;
        Statement st = null;

        try {
            cn = ConectaMySQL.openDB();
            st = cn.createStatement();
            String placa = JOptionPane.showInputDialog("Digite a placa do carro a ser excluido");
            ExemploConsultaCarro consulta = new ExemploConsultaCarro();
            Veiculo excluido = consulta.consultarPlaca(placa);
            int resp = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do carro com a placa: \n" + excluido);

            if(resp == 0) {
                st.executeUpdate("DELETE FROM Veiculos WHERE placaDoCarro='" + 
                    excluido.getPlacaDoCarro() + "'");
                JOptionPane.showMessageDialog(null, "O Carro com a placa " +
                    excluido.getPlacaDoCarro() + " foi excluído com sucesso!!!");
            } else {
                JOptionPane.showMessageDialog(null, "O Carro com a placa " +
                    excluido.getPlacaDoCarro() + " não foi excluído");
            }
        } catch (SQLException e) {
            throw new Exception("Falha ao acessar base de dados. \n" + e.getMessage());
        } finally {
            st.close();
            cn.close();
        }
    }
}