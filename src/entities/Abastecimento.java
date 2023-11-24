package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
//import com.toedter.calendar.JDateChooser;


import javax.swing.JOptionPane;

import connection.ConectaMySQL;

public class Abastecimento extends Veiculo {
    private Date dataDeAbastecimento;
    private String tipoCombustivel;
    private Double precoPago;
    private Double quantidadeDeLitros;
    private Double distanciaPercorrida; 
    private static List<Abastecimento> abastecimentos;
//    private JDateChooser abastecimentoDateChooser;

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
        abastecimentos = new ArrayList<>();
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
        if (quantidadeDeLitros != null && quantidadeDeLitros != 0 && distanciaPercorrida != null) {
            return distanciaPercorrida / quantidadeDeLitros;
        }
        return 0.0;
    }

    public void adicionarAbastecimento(Abastecimento abastecimento) {
        // Adiciona o abastecimento à lista
        abastecimentos.add(abastecimento);
    }

}
