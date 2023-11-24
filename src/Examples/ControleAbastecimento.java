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
    private List<Veiculo> veiculos;

    private JDateChooser abastecimentoDateChooser;


    private JFrame frame;
    private JTextField veiculoPlacaField, veiculoModeloField, veiculoAnoField, postoNomeField, postoLocalizacaoField, abastecimentoDataField, abastecimentoPrecoLitroField,
            abastecimentoQuantidadeLitrosField, abastecimentoDistanciaPercorridaField;
    private JComboBox<String> tipoCombustivelComboBox;
    private JTextArea resultadoTextArea;
    private JTable veiculosTable;

    public ControleAbastecimento() {
        abastecimentos = new ArrayList<>();
        veiculos = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Controle de Abastecimento");
        frame.setSize(800, 600);  // Definindo tamanho inicial
        frame.setBounds(100, 100, 1800, 800);
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

        JButton cadastrarVeiculoButton = new JButton("Cadastrar Abastecimento");
        cadastrarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarVeiculo();
                CadastrarAbastecimento();
                //cadastrarPosto();

            }
        });

        JButton excluirVeiculoButton = new JButton("Excluir Abastecimento");
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
        model.addColumn("Placa");
        model.addColumn("Modelo");
        model.addColumn("Ano de Fabricação");

        // Mostrará ao usuário a lista de carros adicionado
        try {
            Connection con = ConectaMySQL.openDB();
            Statement statement = con.createStatement();
    
            // Executar a consulta SQL para obter veículos
            ResultSet rs = statement.executeQuery("SELECT * FROM Veiculo");
    
            // Preencher modelo com dados dos veículos
            while (rs.next()) {
                String placa = rs.getString("placaDoCarro");
                String modelo = rs.getString("modeloDoCarro");
                int ano = rs.getInt("anoDoCarro");
    
                model.addRow(new Object[]{placa, modelo, ano});
            }
    
            // Configurar tabela
            veiculosTable.setModel(model);
            ((DefaultTableModel) veiculosTable.getModel()).fireTableDataChanged();
    
            rs.close();
            statement.close();
            ConectaMySQL.closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Falha ao listar veículos.");
        }

        // // Preencher modelo com dados dos veículos
        // for (Veiculo veiculo : veiculos) {
        //     model.addRow(new Object[]{veiculo.getPlacaDoCarro(), veiculo.getModeloDoCarro(), veiculo.getAnoDoCarro()});
        // }

        // // Configurar tabela
        // veiculosTable.setModel(model);

        // ((DefaultTableModel) veiculosTable.getModel()).fireTableDataChanged();
    }

//modelo que queremos usar

//    private void listarVeiculos() {
//        // Configurar modelo da tabela
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Modelo");
//        model.addColumn("Data de Abastecimento");
//        model.addColumn("Média por litro");
//        model.addColumn("Posto");
//
//        // Preencher modelo com dados dos veículos
//        for (Abastecimento abastecimento : abastecimentos) {
//            Veiculo veiculo = new Veiculo();
//            Posto posto = new Posto();
//            veiculo.getModeloDoCarro();
//            model.addRow(new Object[]{veiculo.getModeloDoCarro(),abastecimento.getDataDeAbastecimento(), abastecimento.getMediaPorLitro(), posto.getNome()});
//        }
//
//        // Configurar tabela
//        veiculosTable.setModel(model);
//    }

    private double calcularMediaPorLitro(double totalKm , double totalLitros) {
        double mediaKmPorLitro = totalKm / totalLitros;
        // Exibir resultado
        resultadoTextArea.setText("Média de Km por Litro: " + String.format("%.2f", mediaKmPorLitro));
        return mediaKmPorLitro;
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

            JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso!");

            Veiculo veiculoCadastrado = new Veiculo(
                    veiculoPlacaField.getText(),
                    veiculoModeloField.getText(),
                    Integer.parseInt(veiculoAnoField.getText())
            );

            veiculos.add(veiculoCadastrado);

            ps.close();
            cn.close();

            //Atualizar a tabela de veículos
            listarVeiculos();

            System.out.println("Conexão encerrada");
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a operação.");
            e.printStackTrace();
        }
    }

    public void CadastrarAbastecimento(){
        double distanciaPercorrida = Double.parseDouble(abastecimentoDistanciaPercorridaField.getText());
        double combustivelGasto = Double.parseDouble(abastecimentoQuantidadeLitrosField.getText());
        double media = Double.parseDouble(String.format("%.2f", calcularMediaPorLitro(distanciaPercorrida,combustivelGasto)));
        try {
            ConectaMySQL conexao = new ConectaMySQL();
            Connection cn = conexao.openDB();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Abastecimento (veiculo_placaDoCarro, dataDeAbastecimento, tipoCombustivel, precoPago, quantidadeDeLitros, distanciaPercorrida, mediaPorLitro)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, veiculoPlacaField.getText()); //placaDoCarro
            ps.setDate(2, new java.sql.Date(abastecimentoDateChooser.getDate().getTime())); // dataDeAbastecimento
            ps.setString(3,String.valueOf(tipoCombustivelComboBox.getSelectedItem())); //tipoCombustivel
            ps.setDouble(4, Double.parseDouble(abastecimentoPrecoLitroField.getText())); //precoPago
            ps.setDouble(5, Double.parseDouble(abastecimentoQuantidadeLitrosField.getText())); //quantidadeDeLitros
            ps.setDouble(6, Double.parseDouble(abastecimentoDistanciaPercorridaField.getText())); //distanciaPercorrida
            ps.setDouble(7, media); // médiaPorLitro


            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Abastecimento cadastrado com sucesso.");

        // Adicionar o abastecimento à lista
        Abastecimento novoAbastecimento = new Abastecimento(
                veiculoPlacaField.getText(),
                abastecimentoDateChooser.getDate(),
                tipoCombustivelComboBox.getSelectedItem().toString(),
                Double.parseDouble(abastecimentoPrecoLitroField.getText()),
                Double.parseDouble(abastecimentoQuantidadeLitrosField.getText()),
                Double.parseDouble(abastecimentoDistanciaPercorridaField.getText()),
                0.0 // Você pode calcular a média posteriormente, se necessário
        );
        
        abastecimentos.add(novoAbastecimento);

            ps.close();
            cn.close();

            System.out.println("Conexão encerrada");
        } catch (SQLException e) {
            System.out.println("Falha ao realizar a operação.");
            e.printStackTrace();
        }

    }

    public void cadastrarPosto() {
        try {
            ConectaMySQL conexao = new ConectaMySQL();
            Connection cn = conexao.openDB();
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Posto (veiculo_placaDoCarro, nome,  localizacao)"
                    + "VALUES (?,?,?)");

            ps.setString(1, veiculoPlacaField.getText()); //nome
            ps.setString(2, postoNomeField.getText()); //localizacao
            ps.setString(3, postoLocalizacaoField.getText()); //veiculo_placaDoCarro

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Posto cadastrado com sucesso.");

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
        ResultSet rs = null;
    
        try {
            cn = ConectaMySQL.openDB();
            st = cn.createStatement();
    
            // Consultar todos os carros existentes
            rs = st.executeQuery("SELECT placaDoCarro FROM Veiculo");
            
            List<String> opcoes = new ArrayList<>();
            while (rs.next()) {
                opcoes.add(rs.getString("placaDoCarro"));
            }
    
            // Mostrar opções para o usuário
            Object[] opcoesArray = opcoes.toArray();
            String placaSelecionada = (String) JOptionPane.showInputDialog(null,
                    "Escolha o carro a ser excluído:", "Excluir Carro",
                    JOptionPane.PLAIN_MESSAGE, null, opcoesArray, opcoesArray[0]);
    
            if (placaSelecionada != null) {
                Veiculo excluido = consultarPlaca(placaSelecionada);
                int resp = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do carro com a placa: \n" + excluido);
    
                if (resp == 0) {
                    st.executeUpdate("DELETE FROM Abastecimento WHERE veiculo_placaDoCarro='"
                            + excluido.getPlacaDoCarro() + "'");
    
                    st.executeUpdate("DELETE FROM Posto WHERE veiculo_placaDoCarro='"
                            + excluido.getPlacaDoCarro() + "'");
    
                    st.executeUpdate("DELETE FROM Veiculo WHERE placaDoCarro='"
                            + excluido.getPlacaDoCarro() + "'");
    
                    JOptionPane.showMessageDialog(null, "O Carro com a placa " +
                            excluido.getPlacaDoCarro() + " foi excluído com sucesso!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "O Carro com a placa " +
                            excluido.getPlacaDoCarro() + " não foi excluído");
                }
            }
        } catch (SQLException e) {
            throw new Exception("Falha ao acessar base de dados. \n" + e.getMessage());
        } finally {
            // Certifique-se de fechar o ResultSet além de Statement e Connection
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public Veiculo consultarPlaca(String nome) throws Exception {
        Veiculo veiculo = null;
        String querycmd = "select * from veiculo where " + "placaDoCarro like ? ";

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