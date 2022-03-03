package br.edu.ifsul.cc.lpoo.cv.gui.medico;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class JPanelMedicoFormulario extends JPanel {
    private JPanelMedico pnlMedico;
    private Controle controle;

    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosCadastrais;
    private JPanel pnlCentroDadosCadastrais;

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

    /*public void populaComboEndereco(){

        cbxEndereco.removeAllItems();//zera o combo

        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxEndereco.getModel();

        model.addElement("Selecione"); //primeiro item
        try {

            List<Endereco> listCidades = controle.getConexaoJDBC().listEnderecos();
            for(Endereco e : listCidades){
                model.addElement(e);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Enderecos -:"+ex.getLocalizedMessage(), "Endereços", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }


    }*/

    /*public Jogador getJogadorbyFormulario(){

        //validacao do formulario
        if(txfNickname.getText().trim().length() > 4 &&
                new String(txfSenha.getPassword()).trim().length() > 3 &&
                cbxEndereco.getSelectedIndex() > 0){

            Jogador j = new Jogador();
            j.setNickname(txfNickname.getText().trim());
            j.setSenha(new String(txfSenha.getPassword()).trim());
            j.setEndereco((Endereco) cbxEndereco.getSelectedItem());
            j.setPontos(Integer.parseInt(txfPontos.getText().trim()));

            if(jogador != null)
                j.setData_cadastro(jogador.getData_cadastro());

            if(jogador != null)
                j.setData_ultimo_login(jogador.getData_ultimo_login());


            return j;
        }

        return null;
    }*/

    public void setMedicoFormulario(Medico m){

        if(m == null){//se o parametro estiver nullo, limpa o formulario
            txfCpf.setText("");
            txfSenha.setText("");
            txfTipo.setText("");
            txfNome.setText("");
            txfRg.setText("");
            txfNumeroCrmv.setText("");
            txfNumeroCelular.setText("");
            txfEmail.setText("");
            txfDataNascimento.setText("");
            txfCep.setText("");
            txfEndereco.setText("");
            txfComplemento.setText("");
            txfDataCadastro.setText("");
            txfCpf.setEditable(true);
            medico = null;

        }else{
/*
            Medico = m;
            txfNickname.setEditable(false);
            txfNickname.setText(.getNickname());
            txfSenha.setText(jogador.getSenha());
            cbxEndereco.getModel().setSelectedItem(jogador.getEndereco());
            txfPontos.setText(jogador.getPontos().toString());
            txfDataCadastro.setText(format.format(j.getData_cadastro().getTime()));
            if(j.getData_ultimo_login() != null)
                txfDataUltimoLogin.setText(format.format(j.getData_ultimo_login().getTime()));
*/
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
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblCpf, posicionador);//o add adiciona o rotulo no painel

        txfCpf = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfCpf, posicionador);//o add adiciona o rotulo no painel

        lblNumeroCrmv = new JLabel("Número CRMV: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblNumeroCrmv, posicionador);//o add adiciona o rotulo no painel

        txfNumeroCrmv = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfNumeroCrmv, posicionador);//o add adiciona o rotulo no painel

        lblRg = new JLabel("RG: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblRg, posicionador);//o add adiciona o rotulo no painel

        txfRg = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfRg, posicionador);//o add adiciona o rotulo no painel

        lblNome = new JLabel("Nome: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblNome, posicionador);//o add adiciona o rotulo no painel

        txfNome = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfNome, posicionador);//o add adiciona o rotulo no painel

        lblNumeroCelular = new JLabel("Número de celular: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblNumeroCelular, posicionador);//o add adiciona o rotulo no painel

        txfNumeroCelular = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfNumeroCelular, posicionador);//o add adiciona o rotulo no painel

        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblSenha, posicionador);//o add adiciona o rotulo no painel

        txfSenha = new JPasswordField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfSenha, posicionador);//o add adiciona o rotulo no painel

        lblEmail = new JLabel("Email:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblEmail, posicionador);//o add adiciona o rotulo no painel

        txfEmail = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfEmail, posicionador);//o add adiciona o rotulo no painel

        lblDataNascimento = new JLabel("Data de nascimento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblDataNascimento, posicionador);//o add adiciona o rotulo no painel

        txfDataNascimento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfDataNascimento, posicionador);//o add adiciona o rotulo no painel

        lblTipo = new JLabel("Tipo: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblTipo, posicionador);//o add adiciona o rotulo no painel

        txfTipo = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfTipo, posicionador);//o add adiciona o rotulo no painel

        lblEndereco = new JLabel("Endereço: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblEndereco, posicionador);//o add adiciona o rotulo no painel

        txfEndereco = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfEndereco, posicionador);//o add adiciona o rotulo no painel

        lblComplemento = new JLabel("Complemento: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 10;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblComplemento, posicionador);//o add adiciona o rotulo no painel

        txfComplemento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 10;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfComplemento, posicionador);//o add adiciona o rotulo no painel

        lblDataCadastro = new JLabel("Data de Cadastro:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 11;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblDataCadastro, posicionador);//o add adiciona o rotulo no painel

        txfDataCadastro = new JTextField(20);
        txfDataCadastro.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 11;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(txfDataCadastro, posicionador);//o add adiciona o rotulo no painel

        lblCep = new JLabel("Cep:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 12;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblCep, posicionador);//o add adiciona o rotulo no painel

        txfCep = new JTextField(20);
        txfDataCadastro.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 12;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(txfCep, posicionador);//o add adiciona o rotulo no painel

        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);

        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        //btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade
        btnGravar.setToolTipText("btnGravarJogador"); //acessibilidade
        //btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_jogador");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        //btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade
        btnCancelar.setToolTipText("btnCancelarJogador"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_jogador");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

        format = new SimpleDateFormat("dd/MM/yyyy");

    }
/*
    @Override
    public void actionPerformed(ActionEvent arg0) {


        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){

            Jogador j = getJogadorbyFormulario();//recupera os dados do formulÃ¡rio

            if(j != null){

                try {

                    pnlAJogador.getControle().getConexaoJDBC().persist(j);

                    JOptionPane.showMessageDialog(this, "Jogador armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlAJogador.showTela("tela_jogador_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Jogador! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }


        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){


            pnlAJogador.showTela("tela_jogador_listagem");

        }
    }*/
}
