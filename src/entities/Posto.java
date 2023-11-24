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


    
}
