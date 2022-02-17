package br.edu.ifsul.cc.lpoo.cv;

import javax.swing.*;

public class CVMain {
    private Controle controle;

    public CVMain(){

        try {
            controle = new Controle();

            if(controle.conectarBD()){
                controle.initComponents();
            }else{
                JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar no Banco de Dados: "+ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        new CVMain();
    }
}
