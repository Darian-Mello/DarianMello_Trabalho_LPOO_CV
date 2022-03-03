package br.edu.ifsul.cc.lpoo.cv.model.dao.autenticacao;

import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelAutenticacao extends JPanel implements ActionListener {
    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;

    private JLabel lblEmail;
    private JLabel lblSenha;
    private JTextField txfEmail;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;

    //construtor da classe que recebe um parametro.
    public JPanelAutenticacao(Controle controle){

        this.controle = controle;
        initComponents();
    }

    private void initComponents(){
        gridLayout = new GridBagLayout();//inicializando o gerenciador de layout
        this.setLayout(gridLayout);//definie o gerenciador para este painel.

        lblEmail = new JLabel("Email: ");
        lblEmail.setFocusable(true);    //acessibilidade
        lblEmail.setToolTipText("lblEmail"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        //defaultBorder = txfEmail.getBorder();
        this.add(lblEmail, posicionador);//o add adiciona o rotulo no painel

        txfEmail = new JTextField(10);
        txfEmail.setFocusable(true);    //acessibilidade
        txfEmail.setToolTipText("txfEmail"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(txfEmail, posicionador);//o add adiciona o rotulo no painel

        lblSenha = new JLabel("Senha:");
        lblSenha.setFocusable(true);    //acessibilidade
        lblSenha.setToolTipText("lblSenha"); //acessibilidade

        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblSenha, posicionador);//o add adiciona o rotulo no painel

        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);    //acessibilidade
        psfSenha.setToolTipText("psfSenha"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(psfSenha, posicionador);//o add adiciona o rotulo no painel

        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);    //acessibilidade
        btnLogar.setToolTipText("btnLogar"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);//registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);//o add adiciona o rotulo no painel
    }

    public void requestFocus(){

        txfEmail.requestFocus();
    }

    public void cleanForm(){

        txfEmail.setText("");
        psfSenha.setText("");

        txfEmail.setBorder(defaultBorder);
        psfSenha.setBorder(defaultBorder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //testa para verificar se o botão btnLogar foi clicado.
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){

            //validacao do formulario.
            if(txfEmail.getText().trim().length() > 4){

                txfEmail.setBorder(new LineBorder(Color.green,1));

                if(new String(psfSenha.getPassword()).trim().length() > 3 ){

                    psfSenha.setBorder(new LineBorder(Color.green,1));

                    controle.autenticar(txfEmail.getText().trim(), new String(psfSenha.getPassword()).trim());

                }else{

                    JOptionPane.showMessageDialog(this, "Informe Senha com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    psfSenha.setBorder(new LineBorder(Color.red,1));
                    psfSenha.requestFocus();

                }

            }else{

                JOptionPane.showMessageDialog(this, "Informe um Email com 4 dígitos, ou mais!", "Autenticação", JOptionPane.ERROR_MESSAGE);
                txfEmail.setBorder(new LineBorder(Color.red,1));
                txfEmail.requestFocus();
            }
        }

    }
}
