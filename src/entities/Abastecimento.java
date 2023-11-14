package entities;

import java.util.Date;

public class Abastecimento {
    private Date dataDeAbastecimento;
    private Double precoDeLitro;
    private Double quantidadeDeLitros;
    private Veiculo veiculo;

    public Abastecimento() {
    }

    public Abastecimento(Date dataDeAbastecimento, Double precoDeLitro, String tipoDeCombustivel, Double quantidadeDeLitros, Veiculo veiculo) {
        this.dataDeAbastecimento = dataDeAbastecimento;
        this.precoDeLitro = precoDeLitro;
        this.quantidadeDeLitros = quantidadeDeLitros;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void calculoPorLitro() {

    }
}
