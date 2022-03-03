package br.edu.ifsul.cc.lpoo.cv;

import br.edu.ifsul.cc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.cv.gui.JMenuBarHome;
import br.edu.ifsul.cc.lpoo.cv.gui.JPanelHome;
import br.edu.ifsul.cc.lpoo.cv.gui.medico.JPanelMedico;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import br.edu.ifsul.cc.lpoo.cv.model.dao.autenticacao.JPanelAutenticacao;

import javax.swing.*;

public class Controle {
    private PersistenciaJDBC conexaoJDBC;
    private JFramePrincipal frame;
    private JPanelAutenticacao pnlAutenticacao; //painel para a autenticacao do Jogador.
    private JMenuBarHome menuBar; //menu principal
    private JPanelHome pnlHome; // paine de boas vindas (home)
    private JPanelMedico pnlMedico;

    public Controle() {}

    public boolean conectarBD() throws Exception {

        conexaoJDBC = new PersistenciaJDBC();

        if (conexaoJDBC != null) {
            return conexaoJDBC.conexaoAberta();
        }
        return false;
    }

    public void fecharBD() {

        System.out.println("Fechando conexao com o Banco de Dados");
        conexaoJDBC.fecharConexao();
    }

    public void initComponents() {

        frame = new JFramePrincipal();

        pnlAutenticacao = new JPanelAutenticacao(this);

        menuBar = new JMenuBarHome(this);

        pnlHome = new JPanelHome(this);

        pnlMedico = new JPanelMedico(this);

        frame.addTela(pnlAutenticacao, "tela_autenticacao");//carta 1
        frame.addTela(pnlHome, "tela_home");//carta 2
        frame.addTela(pnlMedico, "tela_medicos_listagem");
        //frame.addTela(pnlMedico, "tela_medico_formulario");

        frame.showTela("tela_autenticacao"); // apreseta a carta cujo nome é "tela_autenticacao"

        frame.setVisible(true); // torna visível o jframe
    }

    public void autenticar(String email, String senha) {

        try{

            Pessoa p =  conexaoJDBC.doLogin(email, senha);

            if(p != null){

                JOptionPane.showMessageDialog(pnlAutenticacao, "email " + p.getEmail() + " autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)

            }else{

                JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void showTela(String nomeTela){
        if(nomeTela.equals("tela_autenticacao")){

            pnlAutenticacao.cleanForm();
            frame.showTela(nomeTela);
            pnlAutenticacao.requestFocus();

        }else if(nomeTela.equals("tela_medicos_listagem")){

            pnlMedico.showTela("tela_medicos_listagem");
            frame.showTela(nomeTela);

        }
    }

    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
}
