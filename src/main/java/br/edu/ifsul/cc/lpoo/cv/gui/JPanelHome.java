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
        this.setLayout(layoutGeo);

        lblMensagem = new JLabel("Tela de Boas Vindas!");
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblMensagem, BorderLayout.NORTH);

        lblImagem = new JLabel(new ImageIcon(JPanelHome.class.getResource("/images/clinica_veterinaria.jpg")));
        this.add(lblImagem, BorderLayout.CENTER);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        lblData = new JLabel(df.format(c.getTime()));
        lblData.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        lblData.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblData, BorderLayout.SOUTH);

    }
}
