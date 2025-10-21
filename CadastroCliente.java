import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class CadastroCliente extends JFrame {

    private JTextField txtNome, txtEndereco, txtCidade;
    private JFormattedTextField txtTelefone;
    private JComboBox<String> cbEstado;
    private JRadioButton rbAtivo, rbInativo;
    private ButtonGroup grupoStatus;
    private JButton btnGravar, btnCancelar;

    public CadastroCliente() {
        // Titulo e aparencia geral
        setTitle("Cadastro de Clientes");
        setSize(480, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 247, 250)); // fundo leve

        // Layout com espacamento
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 15, 10, 15);
        c.fill = GridBagConstraints.HORIZONTAL;

        Font fonte = new Font("Segoe UI", Font.PLAIN, 14);

        // Cabecalho bonito
        JLabel lblTitulo = new JLabel("Cadastrar Novo Cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(33, 37, 41));
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        add(lblTitulo, c);
        c.gridwidth = 1;

        // Nome
        c.gridx = 0; c.gridy = 1;
        add(criarLabel("Nome Completo:"), c);
        c.gridx = 1;
        txtNome = criarCampoTexto();
        add(txtNome, c);

        // Endereco
        c.gridx = 0; c.gridy = 2;
        add(criarLabel("Endereco:"), c);
        c.gridx = 1;
        txtEndereco = criarCampoTexto();
        add(txtEndereco, c);

        // Cidade
        c.gridx = 0; c.gridy = 3;
        add(criarLabel("Cidade:"), c);
        c.gridx = 1;
        txtCidade = criarCampoTexto();
        add(txtCidade, c);

        // Estado
        c.gridx = 0; c.gridy = 4;
        add(criarLabel("Estado:"), c);
        c.gridx = 1;
        cbEstado = new JComboBox<>(new String[]{"Selecione", "DF", "SP", "RJ", "MG", "GO", "BA"});
        cbEstado.setFont(fonte);
        cbEstado.setBackground(Color.WHITE);
        add(cbEstado, c);

        // Telefone (com mascara)
        c.gridx = 0; c.gridy = 5;
        add(criarLabel("Telefone:"), c);
        c.gridx = 1;
        txtTelefone = criarCampoTelefone();
        add(txtTelefone, c);

        // Status
        c.gridx = 0; c.gridy = 6;
        add(criarLabel("Status:"), c);
        c.gridx = 1;
        rbAtivo = new JRadioButton("Ativo");
        rbInativo = new JRadioButton("Inativo");
        grupoStatus = new ButtonGroup();
        grupoStatus.add(rbAtivo);
        grupoStatus.add(rbInativo);

        rbAtivo.setFont(fonte);
        rbInativo.setFont(fonte);
        rbAtivo.setBackground(new Color(245, 247, 250));
        rbInativo.setBackground(new Color(245, 247, 250));

        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        painelStatus.setBackground(new Color(245, 247, 250));
        painelStatus.add(rbAtivo);
        painelStatus.add(rbInativo);
        add(painelStatus, c);

        // Botoes
        c.gridx = 0; c.gridy = 7; c.gridwidth = 2;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setBackground(new Color(245, 247, 250));

        btnGravar = criarBotao("Gravar Dados", new Color(46, 134, 222));
        btnCancelar = criarBotao("Cancelar Cadastro", new Color(231, 76, 60));

        painelBotoes.add(btnGravar);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, c);

        // Acoes com validacao
        btnGravar.addActionListener(e -> {
            if (validarCampos()) {
                JOptionPane.showMessageDialog(this,
                        "Cadastro realizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            limparCampos();
            JOptionPane.showMessageDialog(this,
                    "Cadastro cancelado.",
                    "Cancelado",
                    JOptionPane.WARNING_MESSAGE);
        });
    }

    // Criacao de componentes
    private JLabel criarLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(52, 58, 64));
        return lbl;
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField(20);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return campo;
    }

    // Campo de telefone formatado
    private JFormattedTextField criarCampoTelefone() {
        try {
            MaskFormatter mask = new MaskFormatter("(##) #####-####");
            mask.setPlaceholderCharacter('_');
            JFormattedTextField campo = new JFormattedTextField(mask);
            campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            campo.setColumns(20);
            campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)
            ));
            return campo;
        } catch (ParseException e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        return botao;
    }

    private boolean validarCampos() {
        if (txtNome.getText().trim().isEmpty() ||
            txtEndereco.getText().trim().isEmpty() ||
            txtCidade.getText().trim().isEmpty() ||
            txtTelefone.getText().contains("_") ||
            cbEstado.getSelectedIndex() == 0 ||
            (!rbAtivo.isSelected() && !rbInativo.isSelected())) {

            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos corretamente!",
                    "Erro de Validacao",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtNome.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtTelefone.setValue(null);
        cbEstado.setSelectedIndex(0);
        grupoStatus.clearSelection();
    }

    // Tema moderno Nimbus
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new CadastroCliente().setVisible(true));
    }
}
