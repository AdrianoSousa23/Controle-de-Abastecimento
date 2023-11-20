import entities.Abastecimento;
import entities.Combustivel;
import entities.Posto;
import entities.Veiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        calcularPanel.setBorder(BorderFactory.createTitledBorder("Calcular"));

        JButton cadastrarVeiculoButton = new JButton("Cadastrar Veículo");
        cadastrarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarVeiculo();
            }
        });

        JButton cadastrarAbastecimentoButton = new JButton("Cadastrar Abastecimento");
        cadastrarAbastecimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAbastecimento();
            }
        });

        JButton calcularMediaButton = new JButton("Calcular Média por Litro");
        calcularMediaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularMediaPorLitro();
            }
        });

        calcularPanel.add(cadastrarVeiculoButton);
        calcularPanel.add(cadastrarAbastecimentoButton);
        calcularPanel.add(calcularMediaButton);

        return calcularPanel;
    }

    private void cadastrarAbastecimento() {
        try {
            // Obter dados do veículo
            String placa = veiculoPlacaField.getText();
            String modelo = veiculoModeloField.getText();
            int ano = Integer.parseInt(veiculoAnoField.getText());
            Veiculo veiculo = new Veiculo(placa, modelo, ano);

            // Obter dados do posto
            String nomePosto = postoNomeField.getText();
            String localizacaoPosto = postoLocalizacaoField.getText();
            Posto posto = new Posto(nomePosto, localizacaoPosto);

            // Obter dados do abastecimento
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date data = dateFormat.parse(abastecimentoDataField.getText());
            Double precoLitro = Double.parseDouble(abastecimentoPrecoLitroField.getText());
            Double quantidadeLitros = Double.parseDouble(abastecimentoQuantidadeLitrosField.getText());
            Double distanciaPercorrida = Double.parseDouble(abastecimentoDistanciaPercorridaField.getText());
            String tipoCombustivel = (String) tipoCombustivelComboBox.getSelectedItem();

            // Criar objeto Combustivel
            Combustivel combustivel = new Combustivel(data, precoLitro, tipoCombustivel, distanciaPercorrida,
                    quantidadeLitros, veiculo, tipoCombustivel, 0.0, 0.0, 0.0);

            // Adicionar abastecimento à lista
            Abastecimento abastecimento = new Abastecimento(data, precoLitro, tipoCombustivel, distanciaPercorrida,
                    quantidadeLitros, veiculo);
            abastecimentos.add(abastecimento);

            // Limpar campos após o cadastro
            veiculoPlacaField.setText("");
            veiculoModeloField.setText("");
            veiculoAnoField.setText("");
            postoNomeField.setText("");
            postoLocalizacaoField.setText("");
            abastecimentoDataField.setText("");
            abastecimentoPrecoLitroField.setText("");
            abastecimentoQuantidadeLitrosField.setText("");
            abastecimentoDistanciaPercorridaField.setText("");

            // Exibir mensagem de sucesso
            JOptionPane.showMessageDialog(frame, "Abastecimento cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao cadastrar abastecimento. Verifique os dados informados.");
        }
    }

    private void cadastrarVeiculo() {
        try {
            // Obter dados do veículo
            String placa = veiculoPlacaField.getText();
            String modelo = veiculoModeloField.getText();
            int ano = Integer.parseInt(veiculoAnoField.getText());

            // Criar objeto Veiculo
            Veiculo veiculo = new Veiculo(placa, modelo, ano);

            // Adicionar veículo à lista (opcional)
            // veiculos.add(veiculo);

            // Limpar campos após o cadastro
            veiculoPlacaField.setText("");
            veiculoModeloField.setText("");
            veiculoAnoField.setText("");

            // Exibir mensagem de sucesso
            JOptionPane.showMessageDialog(frame, "Veículo cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao cadastrar veículo. Verifique os dados informados.");
        }
    }

    private void listarVeiculos() {
        // Configurar modelo da tabela
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Placa");
        model.addColumn("Modelo");
        model.addColumn("Ano");

        // Preencher modelo com dados dos veículos
        for (Abastecimento abastecimento : abastecimentos) {
            Veiculo veiculo = abastecimento.getVeiculo();
            model.addRow(new Object[]{veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAnoDeFabricacao()});
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
