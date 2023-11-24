package entities;

import java.util.List;
import java.util.ArrayList;;

public class Veiculo {
    private String placaDoCarro;
    private String modeloDoCarro;
    private int anoDoCarro;
    private static List<Veiculo> veiculo;


    public Veiculo() {
    }

    public Veiculo(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public Veiculo(String placaDoCarro, String modeloDoCarro, int anoDoCarro) {
        this.placaDoCarro = placaDoCarro;
        this.modeloDoCarro = modeloDoCarro;
        this.anoDoCarro = anoDoCarro;
        veiculo = new ArrayList<>();
    }

    public String getPlacaDoCarro() {
        return this.placaDoCarro;
    }

    public void setPlacaDoCarro(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public String getModeloDoCarro() {
        return this.modeloDoCarro;
    }

    public void setModeloDoCarro(String modeloDoCarro) {
        this.modeloDoCarro = modeloDoCarro;
    }

    public int getAnoDoCarro() {
        return this.anoDoCarro;
    }

    public void setAnoDoCarro(int anoDoCarro) {
        this.anoDoCarro = anoDoCarro;
    }

    public void adicionarVeiculo(Veiculo veiculos) {
        // Adiciona o veiculo à lista
        veiculo.add(veiculos);
    }

    @Override
    public String toString() {
        return "placaDoCarro='" + placaDoCarro + '\'';
    }
}
