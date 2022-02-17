package br.edu.ifsul.cc.lpoo.cv.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class JFramePrincipal extends JFrame implements WindowListener {

    public CardLayout cardLayout;
    public JPanel painel;

    public JFramePrincipal() {
        initComponents();
    }


    private void initComponents() {
        this.setTitle("Sistema para a Clinica Veterinaria"); //seta o título do jframe

        this.setMinimumSize(new Dimension(600, 600)); //tamanho minimo quando for reduzido.

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // por padrão abre maximizado.

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// finaliza o processo quando o frame é fechado.

        this.addWindowListener(this);//adiciona o listener no frame

        cardLayout = new CardLayout();//iniciando o gerenciador de layout para esta JFrame
        painel = new JPanel();//inicializacao

        painel.setLayout(cardLayout);//definindo o cardLayout para o paineldeFundo

        this.add(painel);  //adiciona no JFrame o paineldeFundo

    }

    public void addTela(JPanel p, String nome) {

        painel.add(p, nome); //adiciona uma "carta no baralho".
    }

    public void showTela(String nome) {

        cardLayout.show(painel, nome); //localiza a "carta no baralho" e mostra.
    }

    @Override
    public void windowOpened(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowClosing(WindowEvent we) {
        System.out.println("Fechando o jframe ..");
    }

    @Override
    public void windowClosed(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowIconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowActivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
