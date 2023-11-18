package entities;
import java.util.Date;

public class Combustivel extends Abastecimento {
    private String tipoDeCombustivel;
    private Double precoGasolina;
    private Double precoEtanol;
    private Double precoDiesel;

    public Combustivel() {
    }

    public Combustivel(Date dataDeAbastecimento, Double precoDeLitro, String tipoDeCombustivel,
            Double distanciaPercorrida, Double quantidadeDeLitros, Veiculo veiculo, String tipoDeCombustivel2,
            Double precoGasolina, Double precoEtanol, Double precoDiesel) {
        super(dataDeAbastecimento, precoDeLitro, tipoDeCombustivel, distanciaPercorrida, quantidadeDeLitros, veiculo);
        tipoDeCombustivel = tipoDeCombustivel2;
        this.precoGasolina = precoGasolina;
        this.precoEtanol = precoEtanol;
        this.precoDiesel = precoDiesel;
    }

    public Double getPrecoGasolina() {
        return this.precoGasolina;
    }

    public void setPrecoGasolina(Double precoGasolina) {
        this.precoGasolina = 5.60;
    }

    public Double getPrecoEtanol() {
        return this.precoEtanol;
    }

    public void setPrecoEtanol(Double precoEtanol) {
        this.precoEtanol = 2.40;
    }

    public Double getPrecoDiesel() {
        return this.precoDiesel;
    }

    public void setPrecoDiesel(Double precoDiesel) {
        this.precoDiesel = 6.20;
    }

    public String getTipoDeCombustivel() {
        return tipoDeCombustivel;
    }

    public void setTipoDeCombustivel(String tipoDeCombustivel) {
        this.tipoDeCombustivel = tipoDeCombustivel;
    }

    public void cadastrarCombustivel() {
    }
}
