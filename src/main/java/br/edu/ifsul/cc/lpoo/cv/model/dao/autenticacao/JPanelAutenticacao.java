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

    private JLabel lblCpf;
    private JLabel lblSenha;
    private JTextField txfCpf;
    private JPasswordField psfSenha;
    private JButton btnLogar;

    public JPanelAutenticacao(Controle controle){

        this.controle = controle;
        initComponents();
    }

    private void initComponents(){
        gridLayout = new GridBagLayout();
        this.setLayout(gridLayout);

        lblCpf = new JLabel("CPF: ");
        lblCpf.setToolTipText("lblCpf");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 0;
        this.add(lblCpf, posicionador);

        txfCpf = new JTextField(10);
        txfCpf.setFocusable(true);
        txfCpf.setToolTipText("txfCpf");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 1;
        this.add(txfCpf, posicionador);

        lblSenha = new JLabel("Senha: ");
        lblSenha.setToolTipText("lblSenha");

        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 0;
        this.add(lblSenha, posicionador);

        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);
        psfSenha.setToolTipText("psfSenha");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 1;
        this.add(psfSenha, posicionador);

        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);
        btnLogar.setToolTipText("btnLogar");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 1;
        btnLogar.addActionListener(this);
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);
    }

    public void requestFocus(){
        txfCpf.requestFocus();
    }

    public void cleanForm(){

        txfCpf.setText("");
        psfSenha.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(btnLogar.getActionCommand())){

            if(txfCpf.getText().trim().length() == 11){

                if(new String(psfSenha.getPassword()).trim().length() >= 4 ){

                    controle.autenticar(txfCpf.getText().trim(), new String(psfSenha.getPassword()).trim());

                }else{
                    JOptionPane.showMessageDialog(this, "Informe Senha com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);

                    psfSenha.requestFocus();
                }

            }else{
                JOptionPane.showMessageDialog(this, "Informe o CPF com 11 caracteres (apenas os números)!", "Autenticação", JOptionPane.ERROR_MESSAGE);
                txfCpf.requestFocus();
            }
        }

    }
}
