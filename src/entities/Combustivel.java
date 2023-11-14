package entities;

import java.util.Date;

public class Combustivel extends Abastecimento{
    private String tipoDeCombustivel;

    public Combustivel() {
    }

    public Combustivel(Date data, Double precoDeLitro, String tipoDeCombustivel, Double quantidadeDeLitros, Veiculo veiculo, String tipoDeCombustivel1) {
        super(data, precoDeLitro, tipoDeCombustivel, quantidadeDeLitros, veiculo);
        this.tipoDeCombustivel = tipoDeCombustivel1;
    }

    @Override
    public String getTipoDeCombustivel() {
        return tipoDeCombustivel;
    }

    @Override
    public void setTipoDeCombustivel(String tipoDeCombustivel) {
        this.tipoDeCombustivel = tipoDeCombustivel;
    }

    public void cadastrarCombustivel() {

    }
}
