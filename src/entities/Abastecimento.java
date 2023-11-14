package entities;

import java.util.Date;

public class Abastecimento {
    private Date data;
    private Double precoDeLitro;
    private String tipoDeCombustivel;
    private Double quantidadeDeLitros;
    private Veiculo veiculo;

    public Abastecimento() {
    }

    public Abastecimento(Date data, Double precoDeLitro, String tipoDeCombustivel, Double quantidadeDeLitros, Veiculo veiculo) {
        this.data = data;
        this.precoDeLitro = precoDeLitro;
        this.tipoDeCombustivel = tipoDeCombustivel;
        this.quantidadeDeLitros = quantidadeDeLitros;
        this.veiculo = veiculo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getPrecoDeLitro() {
        return precoDeLitro;
    }

    public void setPrecoDeLitro(Double precoDeLitro) {
        this.precoDeLitro = precoDeLitro;
    }

    public String getTipoDeCombustivel() {
        return tipoDeCombustivel;
    }

    public void setTipoDeCombustivel(String tipoDeCombustivel) {
        this.tipoDeCombustivel = tipoDeCombustivel;
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
