package entities;

public class Veiculo {
    private String placa;
    private String modelo;
    private int anoDeFabricacao;

    public Veiculo() {
    }

    public Veiculo(String placa, String modelo, int anoDeFabricacao) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public void cadastrarVeiculo() {
        

    }
}
