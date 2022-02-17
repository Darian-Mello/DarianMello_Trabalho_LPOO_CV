package br.edu.ifsul.cc.lpoo.cv.model.dao.autenticacao;

import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
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
        this.add(lblEmail, posicionador);//o add adiciona o rotulo no painel

        txfEmail = new JTextField(10);
        txfEmail.setFocusable(true);    //acessibilidade
        txfEmail.setToolTipText("txfNickname"); //acessibilidade
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


    @Override
    public void actionPerformed(ActionEvent e) {

        //testa para verificar se o botão btnLogar foi clicado.
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){

            //validacao do formulario.
            if(txfEmail.getText().trim().length() > 4 && new String(psfSenha.getPassword()).trim().length() > 3 ){

                controle.autenticar(txfEmail.getText().trim(), new String(psfSenha.getPassword()).trim());

            }
            else if(txfEmail.getText().trim().length() <= 4) {
                JOptionPane.showMessageDialog(this, "Erro, informe um email com mais de 4 caracteres");
            }
            else if(new String(psfSenha.getPassword()).trim().length() <= 3){
                JOptionPane.showMessageDialog(this, "Erro, informe uma senha com mais de 3 digitos");
            }
            else {
                JOptionPane.showMessageDialog(this, "Informe os dados para Email e Senha!", "Autenticação", JOptionPane.ERROR_MESSAGE);

            }
        }

    }
}
