package entities;
import java.util.ArrayList;
import java.util.List;

public class Posto extends Veiculo{
    private String nome;
    private String localizacao;
    private static List<Posto> posto;

    public Posto() {
    }

    public Posto(String placaDoCarro,String nome, String localizacao) {
        super(placaDoCarro);
        this.nome = nome;
        this.localizacao = localizacao;
        posto = new ArrayList<>();
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

    public void adicionarPosto(Posto postos) {
        posto.add(postos);
    }
}
