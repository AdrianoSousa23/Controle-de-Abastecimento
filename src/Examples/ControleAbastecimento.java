package Examples;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.toedter.calendar.JDateChooser;

import connection.ConectaMySQL;
import entities.Abastecimento;
import entities.Posto;
import entities.Veiculo;

public class ControleAbastecimento {
    private List<Abastecimento> abastecimentos;

    private JDateChooser abastecimentoDateChooser;


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
        frame.setBounds(100, 100, 1920, 1080);
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

        // Substitua o JTextField por JDateChooser para a data de abastecimento
        abastecimentoDateChooser = new JDateChooser();
        abastecimentoDateChooser.setDateFormatString("dd/MM/yyyy");

        abastecimentoPrecoLitroField = new JTextField(10);
        abastecimentoQuantidadeLitrosField = new JTextField(10);
        abastecimentoDistanciaPercorridaField = new JTextField(10);
        tipoCombustivelComboBox = new JComboBox<>(new String[]{"Gasolina", "Etanol", "Diesel"});

        combustivelPanel.add(new JLabel("Nome do Posto:"));
        combustivelPanel.add(postoNomeField);
        combustivelPanel.add(new JLabel("Localização do Posto:"));
        combustivelPanel.add(postoLocalizacaoField);
        combustivelPanel.add(new JLabel("Data do Abastecimento:"));
        combustivelPanel.add(abastecimentoDateChooser);
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
        Posto posto = new Posto();
        calcularPanel.setBorder(BorderFactory.createTitledBorder("Calcular"));

        JButton cadastrarVeiculoButton = new JButton("Cadastrar Veículo");
        cadastrarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarVeiculo();
                abastecimento.CadastrarAbastecimento();
                posto.cadastrarPosto();
                calcularMediaPorLitro();
            }
        });

        JButton excluirVeiculoButton = new JButton("Excluir veiculo");
        excluirVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    excluirVeiculo();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                listarVeiculos();
            }
        });

        calcularPanel.add(cadastrarVeiculoButton);
        calcularPanel.add(excluirVeiculoButton);


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

public void cadastrarVeiculo() {
    try {
        ConectaMySQL conexao = new ConectaMySQL(); 
        Connection cn = conexao.openDB();
        PreparedStatement ps = cn.prepareStatement("INSERT INTO Veiculo (placaDoCarro,  modeloDoCarro, anoDoCarro)"
        + "VALUES (?, ?, ?)");

        ps.setString(1, veiculoPlacaField.getText()); //placa
        ps.setString(2, veiculoModeloField.getText()); //modelo
        ps.setInt(3, Integer.parseInt(veiculoAnoField.getText())); //anoDeFabricacao

        ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Carro Cadastrado com sucesso!");

        ps.close();
        cn.close();

        System.out.println("Conexão encerrada");
    } catch (SQLException e) {
        System.out.println("Falha ao realizar a operação.");
        e.printStackTrace();
    }
}

    public void excluirVeiculo() throws Exception {
        Connection cn = null;
        Statement st = null;

        try {
            cn = ConectaMySQL.openDB();
            st = cn.createStatement();
            String placa = JOptionPane.showInputDialog("Digite a placa do carro a ser excluido");
            Veiculo excluido = consultarPlaca(placa);
            int resp = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do carro com a placa: \n" + excluido);

            if(resp == 0) {
                st.executeUpdate("DELETE FROM Veiculos WHERE placaDoCarro='" + 
                    excluido.getPlacaDoCarro() + "'");
                JOptionPane.showMessageDialog(null, "O Carro com a placa " +
                    excluido.getPlacaDoCarro() + " foi excluído com sucesso!!!");
            } else {
                JOptionPane.showMessageDialog(null, "O Carro com a placa " +
                    excluido.getPlacaDoCarro() + " não foi excluído");
            }
        } catch (SQLException e) {
            throw new Exception("Falha ao acessar base de dados. \n" + e.getMessage());
        } finally {
            st.close();
            cn.close();
        }
    }

    public Veiculo consultarPlaca(String nome) throws Exception {
        Veiculo veiculo = null;
        String querycmd = "select * from veiculos where " + "placaDoCarro like ? ";

        try {
            Connection con = ConectaMySQL.openDB();
            PreparedStatement ps1 = con.prepareStatement(querycmd);
            ps1.setString(1, (nome != null ? nome.trim() : ""));
            ResultSet rs = ps1.executeQuery();
            while(rs.next()) {
                String placaDoCarro = rs.getString("placaDoCarro");
               veiculo = new Veiculo(placaDoCarro);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            ConectaMySQL.closeDB();
        }
        return veiculo;
    }
}