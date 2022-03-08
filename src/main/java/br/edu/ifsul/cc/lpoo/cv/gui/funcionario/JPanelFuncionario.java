package br.edu.ifsul.cc.lpoo.cv.gui.funcionario;

import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
import java.awt.*;

public class JPanelFuncionario extends JPanel {
    private CardLayout cardLayout;
    private Controle controle;

    private JPanelFuncionarioListagem funcionarios_listagem;
    private JPanelFuncionarioFormulario funcionarios_formulario;

    public JPanelFuncionario (Controle controle) {
        this.controle = controle;
        initComponents();
    }

    public void initComponents() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        funcionarios_listagem = new JPanelFuncionarioListagem(this, controle);
        funcionarios_formulario = new JPanelFuncionarioFormulario(this, controle);

        this.add(funcionarios_listagem, "tela_funcionarios_listagem");
        this.add(getFormulario(), "tela_funcionarios_formulario");
    }

    public void showTela(String nomeTela){

        if(nomeTela.equals("tela_funcionarios_listagem")){
            funcionarios_listagem.populaTable();
        } else if(nomeTela.equals("tela_funcionarios_formulario")){

            getFormulario().populaCombocargo();
        }
        cardLayout.show(this, nomeTela);
    }

    public Controle getControle() {
        return controle;
    }

    public JPanelFuncionarioFormulario getFormulario() {
        return funcionarios_formulario;
    }
}
