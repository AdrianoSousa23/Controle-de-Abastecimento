package entities;

import java.sql.Connection;
import java.util.Date;

import com.mysql.cj.xdevapi.PreparableStatement;

import connection.ConectaMySQL;
import connection.Conectabanco;

public class Abastecimento {
    private Date dataDeAbastecimento;
    private Double precoDeLitro;
    private Double quantidadeDeLitros;
    private Double distanciaPercorrida;
    private Veiculo veiculo;

    public Abastecimento() {
    }

    public Abastecimento(Date dataDeAbastecimento, Double precoDeLitro, String tipoDeCombustivel, Double distanciaPercorrida ,Double quantidadeDeLitros, Veiculo veiculo) {
        this.dataDeAbastecimento = dataDeAbastecimento;
        this.precoDeLitro = precoDeLitro;
        this.quantidadeDeLitros = quantidadeDeLitros;
        this.distanciaPercorrida = distanciaPercorrida;
        this.veiculo = veiculo;
    }

    public Date getData() {
        return dataDeAbastecimento;
    }

    public void setData(Date dataDeAbastecimento) {
        this.dataDeAbastecimento = dataDeAbastecimento;
    }

    public Double getPrecoDeLitro() {
        return precoDeLitro;
    }

    public void setPrecoDeLitro(Double precoDeLitro) {
        this.precoDeLitro = precoDeLitro;
    }

    public Double getQuantidadeDeLitros() {
        return quantidadeDeLitros;
    }

    public void setQuantidadeDeLitros(Double quantidadeDeLitros) {
        this.quantidadeDeLitros = quantidadeDeLitros;
    }

    public Double getDistanciaPercorrida() {
        return this.distanciaPercorrida;
    }

    public void setDistanciaPercorrida(Double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Double calculoPorLitro() {
        double mediaKm = getDistanciaPercorrida()/getQuantidadeDeLitros();
        return mediaKm;
    }

    // public void cadastrarAbastecimento(){
    //     Connection con = null;
    //     PreparableStatement stmt = null;

    //     try {
    //         con = Conectabanco.getConexao();

    //         String sql = "INSERT INTO Abastecimento (dataDeAbastecimento, precoDeLitro, tipoDeCombustivel, distanciaPercorrida, quantidadeDeLitros, veiculo_id)" +
    //                     "VALUES (?, ?, ?, ?, ?, ?)";
            
    //         stmt = (PreparableStatement) con.prepareStatement(sql);
    //     }
        
    // }
}
