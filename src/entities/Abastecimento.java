package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import connection.ConectaMySQL;

public class Abastecimento extends Veiculo {
    private Date dataDeAbastecimento;
    private String tipoCombustivel;
    private Double precoPago;
    private Double quantidadeDeLitros;
    private Double distanciaPercorrida;
    private Double mediaPorLitro;

    public Abastecimento() {
    }
    
    public Abastecimento(String placaDoCarro, Date dataDeAbastecimento,
            String tipoCombustivel, Double precoPago, Double quantidadeDeLitros, Double distanciaPercorrida,
            Double mediaPorLitro) {
        super(placaDoCarro);
        this.dataDeAbastecimento = dataDeAbastecimento;
        this.tipoCombustivel = tipoCombustivel;
        this.precoPago = precoPago;
        this.quantidadeDeLitros = quantidadeDeLitros;
        this.distanciaPercorrida = distanciaPercorrida;
        this.mediaPorLitro = mediaPorLitro;
    }

    public Date getDataDeAbastecimento() {
        return dataDeAbastecimento;
    }

    public void setDataDeAbastecimento(Date dataDeAbastecimento) {
        this.dataDeAbastecimento = dataDeAbastecimento;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Double getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(Double precoPago) {
        this.precoPago = precoPago;
    }

    public Double getQuantidadeDeLitros() {
        return quantidadeDeLitros;
    }

    public void setQuantidadeDeLitros(Double quantidadeDeLitros) {
        this.quantidadeDeLitros = quantidadeDeLitros;
    }

    public Double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(Double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public Double getMediaPorLitro() {
        return getDistanciaPercorrida()/getQuantidadeDeLitros();
    }

    public void CadastrarAbastecimento(){
        try {
            ConectaMySQL conexao = new ConectaMySQL(); 
            Connection cn = conexao.openDB();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Abastecimento (veiculo_placaDoCarro, dataDeAbastecimento, tipoCombustivel, precoPago, quantidadeDeLitros, distanciaPercorrida, mediaPorLitro)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, getPlacaDoCarro()); //placaDoCarro
            ps.setDate(2, new java.sql.Date(dataDeAbastecimento.getTime())); //dataDeAbastecimento
            ps.setString(3, tipoCombustivel); //tipoCombustivel
            ps.setDouble(4, precoPago); //precoPago
            ps.setDouble(5, quantidadeDeLitros); //quantidadeDeLitros
            ps.setDouble(6, distanciaPercorrida); //distanciaPercorrida
            ps.setDouble(7, mediaPorLitro);
            

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Abastecimento cadastrado com sucesso.");

            ps.close();
            cn.close();

            System.out.println("Conexão encerrada");
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a operação.");
            e.printStackTrace();
        }

    }



}
