package br.edu.ifsul.cc.lpoo.cv.gui.medico;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JPanelMedicoFormulario extends JPanel implements ActionListener {
    private JPanelMedico pnlMedico;
    private Controle controle;

    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosCadastrais;

    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblCpf;
    private JTextField txfCpf;

    private JLabel lblNumeroCrmv;
    private JTextField txfNumeroCrmv;

    private JLabel lblRg;
    private JTextField txfRg;

    private JLabel lblSenha;
    private JPasswordField txfSenha;

    private JLabel lblNome;
    private JTextField txfNome;

    private JLabel lblNumeroCelular;
    private JTextField txfNumeroCelular;

    private JLabel lblCep;
    private JTextField txfCep;

    private JLabel lblEndereco;
    private JTextField txfEndereco;

    private JLabel lblComplemento;
    private JTextField txfComplemento;

    private JLabel lblDataNascimento;
    private JTextField txfDataNascimento;

    private JLabel lblEmail;
    private JTextField txfEmail;

    private JLabel lblDataCadastro;
    private JTextField txfDataCadastro;

    private JLabel lblTipo;
    private JTextField txfTipo;

    private Medico medico;
    private SimpleDateFormat format;

    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;

    public JPanelMedicoFormulario (JPanelMedico pnlMedico, Controle controle){

        this.pnlMedico = pnlMedico;
        this.controle = controle;

        initComponents();

    }

    public Medico getMedicobyFormulario() throws Exception {

        String mensagem = "";
        Calendar dtNascimento = Calendar.getInstance();

        if (medico == null && txfCpf.getText().trim().length() == 11) {
            Pessoa p = new Pessoa();
            PersistenciaJDBC persistencia = new PersistenciaJDBC();
            p = persistencia.verificaPessoaExiste(txfCpf.getText());

            if (p != null) {
                mensagem = "Esse CPF já está cadastrado!";
                JOptionPane.showMessageDialog(this, mensagem, "Edição", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        }

        if (txfCpf.getText().trim().length() != 11 ) {
            txfCpf.requestFocus();
            mensagem = "Por favor, insira o CPF com os 11 caracteres (apenas os números).";

        } else if (txfNumeroCrmv.getText().trim().length() < 4) {
            txfNumeroCrmv.requestFocus();
            mensagem = "Por favor, insira o númoro CRMV com no míimo 4 caracteres.";

        } else if (txfRg.getText().trim().length() != 10) {
            txfRg.requestFocus();
            mensagem = "Por favor, insira o RG com os 10 caracteres.";

        } else if (txfNome.getText().trim().length() < 4) {
            txfNome.requestFocus();
            mensagem = "Por favor, insira um nome com no mínimo 4 caracteres.";

        } else if (txfNumeroCelular.getText().trim().length() != 11) {
            txfNumeroCelular.requestFocus();
            mensagem = "Por favor, insira o número de telefone no formato: \"55999999999\".";

        } else if (new String(txfSenha.getPassword()).trim().length() < 6) {
            txfSenha.requestFocus();
            mensagem = "Por favor, insira uma senha com no mínimo 6 caracteres.";

        } else if (txfEmail.getText().trim().length() < 4) {
            txfEmail.requestFocus();
            mensagem = "Por favor, insira um email com no mínimo 4 caractertes.";

        } else if (txfEndereco.getText().trim().length() < 4) {
            txfEndereco.requestFocus();
            mensagem = "Por favor, insira um endereço com no mínimo 4 caractertes.";

        } else if (txfComplemento.getText().trim().length() < 4) {
            txfComplemento.requestFocus();
            mensagem = "Por favor, insira um Complemento com no mínimo 4 caractertes.";

        } else if (txfCep.getText().trim().length() < 4) {
            txfCep.requestFocus();
            mensagem = "Por favor, insira um CEP com 8 caractertes (apenas os números).";

        } else {
            try {
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                dtNascimento.setTime(formato.parse(txfDataNascimento.getText().trim()));
            } catch (Exception ex) {
                txfDataNascimento.requestFocus();
                mensagem = "Por favor, insira a data de nascimento no formato: \"dd/MM/yyyy\"";
            }
        }

        if (mensagem != "") {
            JOptionPane.showMessageDialog(this, mensagem, "Edição", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        Medico m = new Medico();
        m.setCpf(txfCpf.getText().trim());
        m.setSenha(new String(txfSenha.getPassword()).trim());
        m.setNome(txfNome.getText().trim());
        m.setRg(txfRg.getText().trim());
        m.setNumero_crmv(txfNumeroCrmv.getText().trim());;
        m.setNumero_celular(txfNumeroCelular.getText().trim());
        m.setEmail(txfEmail.getText().trim());
        m.setCep(txfCep.getText().trim());
        m.setEndereco(txfEndereco.getText().trim());
        m.setComplemento(txfComplemento.getText().trim());
        m.setData_nascimento(dtNascimento);
        if(medico != null) {
            m.setData_cadastro(medico.getData_cadastro());
        }

        return m;
    }

    public void setMedicoFormulario(Medico m){

        if(m == null){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            txfCpf.setText("");
            txfSenha.setText("");
            txfTipo.setText("medico");
            txfNome.setText("");
            txfRg.setText("");
            txfNumeroCrmv.setText("");
            txfNumeroCelular.setText("");
            txfEmail.setText("");
            txfDataNascimento.setText("");
            txfCep.setText("");
            txfEndereco.setText("");
            txfComplemento.setText("");
            txfDataCadastro.setText(df.format(c.getTime()));
            txfCpf.setEditable(true);
            txfDataCadastro.setEditable(false);
            txfTipo.setEditable(false);
            medico = null;
        }else{
            medico = m;
            txfCpf.setEditable(false);
            txfDataCadastro.setEditable(false);
            txfTipo.setEditable(false);
            txfCpf.setText(medico.getCpf());
            txfSenha.setText(medico.getSenha());
            txfEndereco.setText(medico.getEndereco());
            txfTipo.setText("medico");
            txfNome.setText(medico.getNome());
            txfRg.setText(medico.getRg());
            txfNumeroCrmv.setText(medico.getNumero_crmv());
            txfNumeroCelular.setText(medico.getNumero_celular());
            txfEmail.setText(medico.getEmail());
            txfDataNascimento.setText(format.format(medico.getData_nascimento().getTime()));
            txfCep.setText(medico.getCep());
            txfComplemento.setText(medico.getComplemento());
            txfDataCadastro.setText(format.format(m.getData_cadastro().getTime()));
            if(m.getData_nascimento() != null)
                txfDataNascimento.setText(format.format(m.getData_nascimento().getTime()));
        }

    }

    private void initComponents(){

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);

        pnlDadosCadastrais = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosCadastrais.setLayout(gridBagLayoutDadosCadastrais);

        lblCpf = new JLabel("CPF: ");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblCpf, posicionador);

        txfCpf = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfCpf, posicionador);

        lblNumeroCrmv = new JLabel("Número CRMV: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblNumeroCrmv, posicionador);

        txfNumeroCrmv = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfNumeroCrmv, posicionador);

        lblRg = new JLabel("RG: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblRg, posicionador);

        txfRg = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfRg, posicionador);

        lblNome = new JLabel("Nome: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblNome, posicionador);

        txfNome = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfNome, posicionador);

        lblNumeroCelular = new JLabel("Número de celular: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblNumeroCelular, posicionador);

        txfNumeroCelular = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfNumeroCelular, posicionador);

        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblSenha, posicionador);

        txfSenha = new JPasswordField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfSenha, posicionador);

        lblEmail = new JLabel("Email:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblEmail, posicionador);

        txfEmail = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfEmail, posicionador);

        lblDataNascimento = new JLabel("Data de nascimento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblDataNascimento, posicionador);

        txfDataNascimento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfDataNascimento, posicionador);

        lblTipo = new JLabel("Tipo: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblTipo, posicionador);

        txfTipo = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfTipo, posicionador);

        lblEndereco = new JLabel("Endereço: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblEndereco, posicionador);

        txfEndereco = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfEndereco, posicionador);

        lblComplemento = new JLabel("Complemento: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 10;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblComplemento, posicionador);

        txfComplemento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 10;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfComplemento, posicionador);

        lblCep = new JLabel("Cep:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 11;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblCep, posicionador);

        txfCep = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 11;
        posicionador.gridx = 1;
        pnlDadosCadastrais.add(txfCep, posicionador);

        lblDataCadastro = new JLabel("Data de Cadastro:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 12;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblDataCadastro, posicionador);

        txfDataCadastro = new JTextField(20);
        txfDataCadastro.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 12;
        posicionador.gridx = 1;
        pnlDadosCadastrais.add(txfDataCadastro, posicionador);

        tbpAbas.addTab("Dados do médico", pnlDadosCadastrais);

        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);
        btnGravar.setToolTipText("btnGravarMedico");
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_medico");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);
        btnCancelar.setToolTipText("btnCancelarMedico");
        btnCancelar.setActionCommand("botao_cancelar_formulario_medico");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

        format = new SimpleDateFormat("dd/MM/yyyy");

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {


        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){

            try {
                Medico m = getMedicobyFormulario();

                if (m != null) {
                    pnlMedico.getControle().getConexaoJDBC().persist(m);

                    JOptionPane.showMessageDialog(this, "O Medico foi salvo com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlMedico.showTela("tela_medicos_listagem");

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar Médico! : " + ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            pnlMedico.showTela("tela_medicos_listagem");
        }
    }
}
