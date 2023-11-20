import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import entities.Abastecimento;
import entities.Posto;
import entities.Veiculo;

public class ControleAbastecimento {
    private List<Abastecimento> abastecimentos;

    private JFrame frame;
    private JTextField veiculoPlacaField, veiculoModeloField, veiculoAnoField, postoNomeField, postoLocalizacaoField, abastecimentoDataField, abastecimentoPrecoLitroField,
            abastecimentoQuantidadeLitrosField, abastecimentoDistanciaPercorridaField;
    private JComboBox<String> tipoCombustivelComboBox;
    private JTextArea resultadoTextArea;
    private JTable veiculosTable;

    public ControleAbastecimento() {
        abastecimentos = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Controle de Abastecimento");
        frame.setSize(800, 600);  // Definindo tamanho inicial
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Painel para cadastro
        JPanel cadastroPanel = new JPanel(new GridBagLayout());

        JPanel veiculoPanel = criarPainelVeiculo();
        JPanel combustivelPanel = criarPainelCombustivel();
        JPanel calcularPanel = criarPainelCalcular();

        adicionarComponente(cadastroPanel, veiculoPanel, gbc);
        gbc.gridy++;
        adicionarComponente(cadastroPanel, combustivelPanel, gbc);
        gbc.gridy++;
        adicionarComponente(cadastroPanel, calcularPanel, gbc);

        adicionarComponente(panel, cadastroPanel, gbc);

        // Área de exibição de resultados
        JPanel resultadoPanel = new JPanel(new BorderLayout());
        resultadoTextArea = new JTextArea();
        resultadoPanel.add(new JScrollPane(resultadoTextArea), BorderLayout.CENTER);
        gbc.gridy = 0;
        adicionarComponente(panel, resultadoPanel, gbc);

        // Tabela de veículos
        JPanel tablePanel = new JPanel(new BorderLayout());
        veiculosTable = new JTable();
        tablePanel.add(new JScrollPane(veiculosTable), BorderLayout.CENTER);
        gbc.gridy++;
        adicionarComponente(panel, tablePanel, gbc);

        // Botão para listar e ordenar abastecimentos
        JButton listarButton = new JButton("Listar Veículos");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarVeiculos();
            }
        });
        tablePanel.add(listarButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void adicionarComponente(Container container, Component componente, GridBagConstraints gbc) {
        container.add(componente, gbc);
    }

    private JPanel criarPainelVeiculo() {
        JPanel veiculoPanel = new JPanel();
        veiculoPanel.setBorder(BorderFactory.createTitledBorder("Veículo"));
        veiculoPlacaField = new JTextField(10);
        veiculoModeloField = new JTextField(10);
        veiculoAnoField = new JTextField(10);
        veiculoPanel.add(new JLabel("Placa:"));
        veiculoPanel.add(veiculoPlacaField);
        veiculoPanel.add(new JLabel("Modelo:"));
        veiculoPanel.add(veiculoModeloField);
        veiculoPanel.add(new JLabel("Ano de Fabricação:"));
        veiculoPanel.add(veiculoAnoField);
        return veiculoPanel;
    }

    private JPanel criarPainelCombustivel() {
        JPanel combustivelPanel = new JPanel();
        combustivelPanel.setBorder(BorderFactory.createTitledBorder("Combustível"));
        postoNomeField = new JTextField(10);
        postoLocalizacaoField = new JTextField(10);
        abastecimentoDataField = new JTextField(10);
        abastecimentoPrecoLitroField = new JTextField(10);
        abastecimentoQuantidadeLitrosField = new JTextField(10);
        abastecimentoDistanciaPercorridaField = new JTextField(10);
        tipoCombustivelComboBox = new JComboBox<>(new String[]{"Gasolina", "Etanol", "Diesel"});
        combustivelPanel.add(new JLabel("Nome do Posto:"));
        combustivelPanel.add(postoNomeField);
        combustivelPanel.add(new JLabel("Localização do Posto:"));
        combustivelPanel.add(postoLocalizacaoField);
        combustivelPanel.add(new JLabel("Data do Abastecimento:"));
        combustivelPanel.add(abastecimentoDataField);
        combustivelPanel.add(new JLabel("Preço por Litro:"));
        combustivelPanel.add(abastecimentoPrecoLitroField);
        combustivelPanel.add(new JLabel("Quantidade de Litros:"));
        combustivelPanel.add(abastecimentoQuantidadeLitrosField);
        combustivelPanel.add(new JLabel("Distância Percorrida:"));
        combustivelPanel.add(abastecimentoDistanciaPercorridaField);
        combustivelPanel.add(new JLabel("Tipo de Combustível:"));
        combustivelPanel.add(tipoCombustivelComboBox);
        return combustivelPanel;
    }

    private JPanel criarPainelCalcular() {
        JPanel calcularPanel = new JPanel();
        Veiculo veiculo = new Veiculo();
        Abastecimento abastecimento = new Abastecimento();
        calcularPanel.setBorder(BorderFactory.createTitledBorder("Calcular"));

        JButton cadastrarVeiculoButton = new JButton("Cadastrar Veículo");
        cadastrarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                veiculo.cadastrarVeiculo();
                abastecimento.CadastrarAbastecimento();
                calcularMediaPorLitro();
            }
        });


        calcularPanel.add(cadastrarVeiculoButton);

        return calcularPanel;
    }

    private void listarVeiculos() {
        // Configurar modelo da tabela
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Modelo");
        model.addColumn("Data de Abastecimento");
        model.addColumn("Média por litro");
        model.addColumn("Posto");

        // Preencher modelo com dados dos veículos
        for (Abastecimento abastecimento : abastecimentos) {
            Veiculo veiculo = new Veiculo();
            Posto posto = new Posto();
            veiculo.getModeloDoCarro();
            model.addRow(new Object[]{veiculo.getModeloDoCarro(),abastecimento.getDataDeAbastecimento(), abastecimento.getMediaPorLitro(), posto.getNome()});
        }

        // Configurar tabela
        veiculosTable.setModel(model);
    }

    private void calcularMediaPorLitro() {
        if (abastecimentos.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há abastecimentos cadastrados para calcular a média.");
            return;
        }

        // Calcular média por litro
        double totalKm = 0.0;
        double totalLitros = 0.0;

        for (Abastecimento abastecimento : abastecimentos) {
            totalKm += abastecimento.getDistanciaPercorrida();
            totalLitros += abastecimento.getQuantidadeDeLitros();
        }

        double mediaKmPorLitro = totalKm / totalLitros;

        // Exibir resultado
        resultadoTextArea.setText("Média de Km por Litro: " + String.format("%.2f", mediaKmPorLitro));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControleAbastecimento();
            }
        });
    }
}