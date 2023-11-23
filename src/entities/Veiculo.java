package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.ConectaMySQL;

public class Veiculo {
    private String placaDoCarro;
    private String modeloDoCarro;
    private int anoDoCarro;


    public Veiculo() {
    }

    public Veiculo(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public Veiculo(String placaDoCarro, String modeloDoCarro, int anoDoCarro) {
        this.placaDoCarro = placaDoCarro;
        this.modeloDoCarro = modeloDoCarro;
        this.anoDoCarro = anoDoCarro;
    }

    public String getPlacaDoCarro() {
        return this.placaDoCarro;
    }

    public void setPlacaDoCarro(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public String getModeloDoCarro() {
        return this.modeloDoCarro;
    }

    public void setModeloDoCarro(String modeloDoCarro) {
        this.modeloDoCarro = modeloDoCarro;
    }

    public int getAnoDoCarro() {
        return this.anoDoCarro;
    }

    public void setAnoDoCarro(int anoDoCarro) {
        this.anoDoCarro = anoDoCarro;
    }

    @Override
    public String toString() {
        return "placaDoCarro='" + placaDoCarro + '\'';
    }

    public void cadastrarVeiculo() {
        try {
            ConectaMySQL conexao = new ConectaMySQL(); 
            Connection cn = conexao.openDB();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Veiculo (placa,  modelo, anoDeFabricacao)"
            + "VALUES (?, ?, ?)");

            ps.setString(1, placaDoCarro); //placa
            ps.setString(2, modeloDoCarro); //modelo
            ps.setInt(3, anoDoCarro); //anoDeFabricacao

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

    public void excluirVeiculo() throws Exception {
        Connection cn = null;
        Statement st = null;

        try {
            cn = ConectaMySQL.openDB();
            st = cn.createStatement();
            String placa = JOptionPane.showInputDialog("Digite a placa do carro a ser excluido");
            Veiculo excluido = consultarPlaca(placa);
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

    public Veiculo consultarPlaca(String nome) throws Exception {
        Veiculo veiculo = null;
        String querycmd = "select * from veiculos where " + "placaDoCarro like ? ";

        try {
            Connection con = ConectaMySQL.openDB();
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
