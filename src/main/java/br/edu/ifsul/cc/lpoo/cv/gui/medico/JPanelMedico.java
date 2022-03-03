package br.edu.ifsul.cc.lpoo.cv.gui.medico;
import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
import java.awt.*;

public class JPanelMedico extends JPanel {
    private CardLayout cardLayout;
    private Controle controle;

    private JPanelMedicoListagem medicos_listagem;
    private JPanelMedicoFormulario medicos_formulario;

    public JPanelMedico (Controle controle) {
        this.controle = controle;
        initComponents();
    }

    public void initComponents() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        medicos_listagem = new JPanelMedicoListagem(this, controle);
        medicos_formulario = new JPanelMedicoFormulario(this, controle);

        this.add(medicos_listagem, "tela_medicos_listagem");
        this.add(getFormulario(), "tela_medico_formulario");
    }

   public void showTela(String nomeTela){

        if(nomeTela.equals("tela_medicos_listagem")){
            medicos_listagem.populaTable();
        }
        cardLayout.show(this, nomeTela);
    }

    public Controle getControle() {
        return controle;
    }

    public JPanelMedicoFormulario getFormulario() {
        return medicos_formulario;
    }
}