import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface {
    private JFrame frame;

    private JTextField modeloText, placaText, quantidadeText, anoFrabricacao;

    private JButton gravar, listarVeiculos, listarPorData, calcularMedia;


    public static void main(String[] args) {
        
    }

    private void controleAbastecimento(){
        frame = new JFrame("Controle de Abastecimento");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modeloText = new JTextField(15);
        placaText = new JTextField(10);
        anoFrabricacao = new JTextField(4);
        quantidadeText = new JTextField(10);

        gravar = new JButton("Salvar Abastecimentos");

        listarVeiculos = new JButton("Listar por veiculos");

        listarPorData = new JButton("Listar por data");

        calcularMedia = new JButton("Calcular Média");

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        panel.add(new JLabel("Modelo do veiculo: "));
        panel.add(modeloText);
        panel.add(new JLabel("Placa do veiculo: "));
        panel.add(placaText);
        panel.add(new JLabel("Ano de fabricação do veiculo: "));
        panel.add(anoFrabricacao);
        panel.add(new JLabel("quantidade abastecida: "));
        panel.add(quantidadeText);
        
    }
}