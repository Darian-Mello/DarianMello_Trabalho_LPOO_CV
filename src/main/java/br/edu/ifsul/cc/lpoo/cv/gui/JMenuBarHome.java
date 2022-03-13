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
    private JMenuItem menuItemFuncionario;

    private Controle controle;

    public JMenuBarHome(Controle controle){

        this.controle = controle;

        initComponents();
    }

    private void initComponents(){

        menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A);
        menuArquivo.setToolTipText("Arquivo");
        menuArquivo.setFocusable(true);

        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setToolTipText("Sair");
        menuItemSair.setFocusable(true);

        menuCadastro = new JMenu("Cadastros");
        menuCadastro.setMnemonic(KeyEvent.VK_C);
        menuCadastro.setToolTipText("Cadastro");
        menuCadastro.setFocusable(true);

        menuItemSair.addActionListener(this);
        menuItemSair.setActionCommand("menu_sair");
        menuArquivo.add(menuItemSair);

        menuItemPessoa = new JMenuItem("Medicos");
        menuItemPessoa.setToolTipText("Pessoa");
        menuItemPessoa.setFocusable(true);

        menuItemPessoa.addActionListener(this);
        menuItemPessoa.setActionCommand("menu_medico");
        menuCadastro.add(menuItemPessoa);

        menuItemFuncionario = new JMenuItem("Funcionarios");
        menuItemFuncionario.setToolTipText("Funcionario");
        menuItemFuncionario.setFocusable(true);

        menuItemFuncionario.addActionListener(this);
        menuItemFuncionario.setActionCommand("menu_funcionario");
        menuCadastro.add(menuItemFuncionario);

        this.add(menuArquivo);
        this.add(menuCadastro);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(menuItemSair.getActionCommand())) {

            int d = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema? ", "Sair", JOptionPane.YES_NO_OPTION);
            if (d == 0) {
                controle.fecharBD();
                System.exit(0);
            }

        } else if(e.getActionCommand().equals(menuItemPessoa.getActionCommand())){

            controle.showTela("tela_medicos_listagem");
        } else if(e.getActionCommand().equals(menuItemFuncionario.getActionCommand())){

            controle.showTela("tela_funcionarios_listagem");
        }

    }
}
