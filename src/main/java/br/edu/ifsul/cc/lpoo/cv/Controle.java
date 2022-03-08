package br.edu.ifsul.cc.lpoo.cv;

import br.edu.ifsul.cc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.cv.gui.JMenuBarHome;
import br.edu.ifsul.cc.lpoo.cv.gui.JPanelHome;
import br.edu.ifsul.cc.lpoo.cv.gui.funcionario.JPanelFuncionario;
import br.edu.ifsul.cc.lpoo.cv.gui.medico.JPanelMedico;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import br.edu.ifsul.cc.lpoo.cv.model.dao.autenticacao.JPanelAutenticacao;

import javax.swing.*;

public class Controle {
    private PersistenciaJDBC conexaoJDBC;
    private JFramePrincipal frame;
    private JPanelAutenticacao pnlAutenticacao;
    private JMenuBarHome menuBar;
    private JPanelHome pnlHome;
    private JPanelMedico pnlMedico;
    private JPanelFuncionario pnlFuncionario;

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

        pnlFuncionario = new JPanelFuncionario(this);

        frame.addTela(pnlAutenticacao, "tela_autenticacao");
        frame.addTela(pnlHome, "tela_home");
        frame.addTela(pnlMedico, "tela_medicos_listagem");
        frame.addTela(pnlFuncionario, "tela_funcionarios_listagem");

        frame.showTela("tela_autenticacao");

        frame.setVisible(true);
    }

    public void autenticar(String email, String senha) {

        try{

            Pessoa p =  conexaoJDBC.doLogin(email, senha);

            if(p != null){

                JOptionPane.showMessageDialog(pnlAutenticacao, "Funcionário(a) " + p.getNome() + " autenticado(a) com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                frame.setJMenuBar(menuBar);
                frame.showTela("tela_home");

            }else{
                JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
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

        } else if(nomeTela.equals("tela_funcionarios_listagem")){

            pnlFuncionario.showTela("tela_funcionarios_listagem");
            frame.showTela(nomeTela);

        }
    }

    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
}
