package br.edu.ifsul.cc.lpoo.cv.gui;

import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class JMenuBarHome extends JMenuBar implements ActionListener {
    private JMenu menuArquivo;
    private JMenuItem menuItemSair;

    private JMenu menuCadastro;
    private JMenuItem menuItemPessoa;

    private Controle controle;

    public JMenuBarHome(Controle controle){

        this.controle = controle;

        initComponents();
    }

    private void initComponents(){

        menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A);//ativa o ALT + A para acessar esse menu - acessibilidade
        menuArquivo.setToolTipText("Arquivo"); //acessibilidade
        menuArquivo.setFocusable(true); //acessibilidade


        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setToolTipText("Sair"); //acessibilidade
        menuItemSair.setFocusable(true);     //acessibilidade

        menuItemSair.addActionListener(this);
        menuItemSair.setActionCommand("menu_sair");
        menuArquivo.add(menuItemSair);

        menuCadastro = new JMenu("Cadastros");
        menuCadastro.setMnemonic(KeyEvent.VK_C);//ativa o ALT + C para acessar esse menu - acessibilidade
        menuCadastro.setToolTipText("Cadastro"); //acessibilidade
        menuCadastro.setFocusable(true); //acessibilidade

        menuItemPessoa = new JMenuItem("Pessoa");
        menuItemPessoa.setToolTipText("Pessoa"); //acessibilidade
        menuItemPessoa.setFocusable(true); //acessibilidade

        menuItemPessoa.addActionListener(this);
        menuItemPessoa.setActionCommand("menu_jogador");
        menuCadastro.add(menuItemPessoa);

        this.add(menuArquivo);
        this.add(menuCadastro);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(menuItemSair.getActionCommand())){

            //se o usuario clicou no menuitem Sair
            int d = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema? ", "Sair", JOptionPane.YES_NO_OPTION);
            if(d == 0){
                controle.fecharBD();//fecha a conexao com o banco de dados.
                System.exit(0);//finaliza o processo do programa.
            }


        }else if(e.getActionCommand().equals(menuItemPessoa.getActionCommand())){

            //se o usuario clicou no menuitem Usuario
            controle.showTela("tela_jogador");
        }

    }
}
