package br.edu.ifsul.cc.lpoo.cv.gui.funcionario;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Cargo;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class JPanelFuncionarioFormulario extends JPanel implements ActionListener {
    private JPanelFuncionario pnlFuncionario;
    private Controle controle;

    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosCadastrais;

    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblCpf;
    private JTextField txfCpf;

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

    private JLabel lblCargo;
    private JComboBox cbxCargo;

    private JLabel lblNumeroCtps;
    private JTextField txfNumeroCtps;

    private JLabel lblNumeroPis;
    private JTextField txfNumeroPis;

    private JLabel lblDataCadastro;
    private JTextField txfDataCadastro;

    private JLabel lblTipo;
    private JTextField txfTipo;

    private Funcionario funcionario;
    private SimpleDateFormat format;

    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;

    public JPanelFuncionarioFormulario (JPanelFuncionario pnlFuncionaio, Controle controle){

        this.pnlFuncionario = pnlFuncionaio;
        this.controle = controle;

        initComponents();

    }

    public void populaCombocargo(){

        cbxCargo.removeAllItems();

        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxCargo.getModel();

        model.addElement("Selecione");
        try {

            ArrayList a = new ArrayList<Cargo>(Arrays.asList(Cargo.values()));
            String cargo;

            for (int i = 0; i < a.size(); i++){
                cargo = a.get(i).toString();
                model.addElement(cargo);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Enderecos -:"+ex.getLocalizedMessage(), "Endereços", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    public Funcionario getFuncionariobyFormulario() throws Exception {

        String mensagem = "";
        Calendar dtNascimento = Calendar.getInstance();

        if (funcionario == null && txfCpf.getText().trim().length() == 11) {
            Pessoa p = new Pessoa();
            PersistenciaJDBC persistencia = new PersistenciaJDBC();
            p = persistencia.verificaPessoaExiste(txfCpf.getText());

            if (p != null) {
                mensagem = "Esse CPF já está cadastrado!";
                JOptionPane.showMessageDialog(this, mensagem, "Edição", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        }

        if(txfCpf.getText().trim().length() < 11 ){
            txfCpf.requestFocus();
            mensagem = "Por favor, insira o CPF com os 11 caracteres (apenas os números).";

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
            mensagem = "Por favor, insira uma senha com no mínimo 8 caracteres.";

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

        }else if (txfNumeroCtps.getText().trim().length() < 4) {
            txfNumeroCtps.requestFocus();
            mensagem = "Por favor, insira o número CTPS com no mínimo 4 caractertes.";

        } else if (txfNumeroPis.getText().trim().length() < 4) {
            txfNumeroPis.requestFocus();
            mensagem = "Por favor, insira o número PIS com no mínimo 4 caractertes.";

        } else if (cbxCargo.getSelectedIndex() == 0) {
            mensagem = "Por favor, selecione um cargo para esse funcionario.";

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

        Funcionario f = new Funcionario();
        f.setCpf(txfCpf.getText().trim());
        f.setSenha(new String(txfSenha.getPassword()).trim());
        f.setNome(txfNome.getText().trim());
        f.setRg(txfRg.getText().trim());
        f.setNumero_celular(txfNumeroCelular.getText().trim());
        f.setEmail(txfEmail.getText().trim());
        f.setCep(txfCep.getText().trim());
        f.setEndereco(txfEndereco.getText().trim());
        f.setComplemento(txfComplemento.getText().trim());
        f.setData_nascimento(dtNascimento);
        f.setNumero_ctps(txfNumeroCtps.getText().trim());
        f.setNumero_pis(txfNumeroPis.getText().trim());

        String s = cbxCargo.getSelectedItem().toString();
        Cargo cargo = Cargo.valueOf(s);
        f.setCargo(cargo);

        if(funcionario != null) {
            f.setData_cadastro(funcionario.getData_cadastro());
        }

        return f;
    }

    public void setFuncionarioFormulario(Funcionario f){

        if(f == null){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            txfCpf.setText("");
            txfSenha.setText("");
            txfTipo.setText("funcionario");
            txfNome.setText("");
            txfRg.setText("");
            txfNumeroCelular.setText("");
            txfEmail.setText("");
            txfDataNascimento.setText("");
            txfCep.setText("");
            txfEndereco.setText("");
            txfComplemento.setText("");
            txfNumeroCtps.setText("");
            txfNumeroPis.setText("");
            cbxCargo.setSelectedIndex(0);
            txfDataCadastro.setText(df.format(c.getTime()));
            txfCpf.setEditable(true);
            txfDataCadastro.setEditable(false);
            txfTipo.setEditable(false);
            funcionario = null;
        }else{
            funcionario = f;
            txfCpf.setEditable(false);
            txfDataCadastro.setEditable(false);
            txfTipo.setEditable(false);
            txfCpf.setText(funcionario.getCpf());
            txfSenha.setText(funcionario.getSenha());
            txfEndereco.setText(funcionario.getEndereco());
            txfTipo.setText("funcionario");
            txfNome.setText(funcionario.getNome());
            txfRg.setText(funcionario.getRg());
            txfNumeroCelular.setText(funcionario.getNumero_celular());
            txfEmail.setText(funcionario.getEmail());
            txfDataNascimento.setText(format.format(funcionario.getData_nascimento().getTime()));
            txfCep.setText(funcionario.getCep());
            txfNumeroCtps.setText(funcionario.getNumero_ctps());
            txfNumeroPis.setText(funcionario.getNumero_pis());
            cbxCargo.getModel().setSelectedItem(funcionario.getCargo());
            txfComplemento.setText(funcionario.getComplemento());
            txfDataCadastro.setText(format.format(f.getData_cadastro().getTime()));
            if(f.getData_nascimento() != null)
                txfDataNascimento.setText(format.format(f.getData_nascimento().getTime()));
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

        lblDataCadastro = new JLabel("Data de Cadastro:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 11;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblDataCadastro, posicionador);

        txfDataCadastro = new JTextField(20);
        txfDataCadastro.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 11;
        posicionador.gridx = 1;
        pnlDadosCadastrais.add(txfDataCadastro, posicionador);

        lblCep = new JLabel("Cep:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 12;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblCep, posicionador);

        txfCep = new JTextField(20);
        txfDataCadastro.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 12;
        posicionador.gridx = 1;
        pnlDadosCadastrais.add(txfCep, posicionador);

        lblNumeroCtps = new JLabel("Número CTPS: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 13;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblNumeroCtps, posicionador);

        txfNumeroCtps = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 13;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfNumeroCtps, posicionador);

        lblNumeroPis = new JLabel("Número PIS: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 14;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblNumeroPis, posicionador);

        txfNumeroPis = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 14;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfNumeroPis, posicionador);

        lblCargo = new JLabel("Cargo:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 15;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblCargo, posicionador);

        cbxCargo = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 15;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(cbxCargo, posicionador);

        tbpAbas.addTab("Dados do funcionario", pnlDadosCadastrais);

        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);
        btnGravar.setToolTipText("btnGravarFuncionario");
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_funcionario");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);
        btnCancelar.setToolTipText("btnCancelarFuncionario");
        btnCancelar.setActionCommand("botao_cancelar_formulario_funcionario");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

        format = new SimpleDateFormat("dd/MM/yyyy");

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {


        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){

            try {
                Funcionario f = getFuncionariobyFormulario();

                if(f != null){

                        pnlFuncionario.getControle().getConexaoJDBC().persist(f);

                        JOptionPane.showMessageDialog(this, "O Funcionario foi salvo com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                        pnlFuncionario.showTela("tela_funcionarios_listagem");

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar Funcionario! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            pnlFuncionario.showTela("tela_funcionarios_listagem");
        }
    }
}
