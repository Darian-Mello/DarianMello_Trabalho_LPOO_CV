package br.edu.ifsul.cc.lpoo.cv.gui;

import br.edu.ifsul.cc.lpoo.cv.Controle;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JPanelHome extends JPanel {
    private JLabel lblMensagem;
    private JLabel lblImagem;
    private JLabel lblData;
    private BorderLayout layoutGeo;

    private Controle controle;

    public JPanelHome(Controle controle){

        this.controle = controle;
        initComponents();
    }

    private void initComponents(){

        layoutGeo = new BorderLayout();
        this.setLayout(layoutGeo);//seta o gerenciador de layout para este painel.

        lblMensagem = new JLabel("Tela de Boas Vindas!");
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblMensagem, BorderLayout.NORTH);

        lblImagem = new JLabel(new ImageIcon(JPanelHome.class.getResource("/images/logo_ifsul_color.png")));
        this.add(lblImagem, BorderLayout.CENTER);//adiciona a imagem na parte central deste painel.

        Calendar c = Calendar.getInstance();//recupera a data atual do computador.
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        lblData = new JLabel(df.format(c.getTime()));
        lblData.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        lblData.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblData, BorderLayout.SOUTH); //adiciona o rotulo para a data na parte inferior deste painel.

    }
}
