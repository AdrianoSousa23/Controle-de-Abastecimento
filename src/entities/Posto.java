package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConectaMySQL;

public class Posto extends Veiculo{
    private String nome;
    private String localizacao;

    public Posto() {
    }

    public Posto(String placaDoCarro,String nome, String localizacao) {
        super(placaDoCarro);
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void cadastrarPosto() {
        try {
            ConectaMySQL conexao = new ConectaMySQL(); 
            Connection cn = conexao.openDB();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Posto (veiculo_placaDoCarro, nome,  localizacao)"
            + "VALUES (?,?,?)");

            ps.setString(1, getPlacaDoCarro()); //nome
            ps.setString(2, nome); //localizacao
            ps.setString(3, localizacao); //veiculo_placaDoCarro

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Posto cadastrado com sucesso.");

            ps.close();
            cn.close();

            System.out.println("Conexão encerrada");
            
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a operação.");
            e.printStackTrace();
        }

    }
    
}
