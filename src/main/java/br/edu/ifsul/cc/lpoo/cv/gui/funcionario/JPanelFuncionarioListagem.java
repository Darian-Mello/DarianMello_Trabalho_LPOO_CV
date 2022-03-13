package br.edu.ifsul.cc.lpoo.cv.gui.funcionario;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class JPanelFuncionarioListagem extends JPanel implements ActionListener {
    private JPanelFuncionario pnlfuncionario;
    private Controle controle;

    private BorderLayout borderLayout;
    private JPanel pnlNorte;
    private JLabel lblFiltro;
    private JTextField txfFiltro;
    private JButton btnFiltro;

    private JButton btnLimparFiltros;

    private JPanel pnlCentro;
    private JScrollPane scpListagem;
    private JTable tblListagem;
    private DefaultTableModel modeloTabela;

    private JPanel pnlSul;
    private JButton btnNovo;
    private JButton btnAlterar;
    private JButton btnRemover;

    private SimpleDateFormat format;

    public JPanelFuncionarioListagem(JPanelFuncionario pnlfuncionario, Controle controle){

        this.pnlfuncionario = pnlfuncionario;
        this.controle = controle;

        initComponents();
    }

    public void populaTableFiltro(String nome){
        try {
            DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            model.setRowCount(0);

            btnLimparFiltros.setEnabled(true);
            if (!nome.isEmpty()) {
                java.util.List<Funcionario> listFuncionarios = controle.getConexaoJDBC().listFuncionariosFiltro(nome);
                for(Funcionario f : listFuncionarios){
                    model.addRow(new Object[]{
                            f.getCpf(),
                            f.getRg(),
                            f.getNome(),
                            f.getEmail(),
                            f.getNumero_celular(),
                            formato.format(f.getData_nascimento().getTime()),
                            formato.format(f.getData_cadastro().getTime()),
                            f.getEndereco(),
                            f.getCep(),
                            f.getComplemento(),
                            f.getCargo(),
                            f.getNumero_ctps(),
                            f.getNumero_pis()
                    });
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar os Funcionarios -: "+ex.getLocalizedMessage(), "Funcionarios", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void populaTable(){
        try {
            DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            model.setRowCount(0);

            txfFiltro.setText("");
            btnLimparFiltros.setEnabled(false);
            List<Funcionario> listFuncionarios = controle.getConexaoJDBC().listFuncionarios();
            for(Funcionario f : listFuncionarios){
                model.addRow(new Object[]{
                        f.getCpf(),
                        f.getRg(),
                        f.getNome(),
                        f.getEmail(),
                        f.getNumero_celular(),
                        formato.format(f.getData_nascimento().getTime()),
                        formato.format(f.getData_cadastro().getTime()),
                        f.getEndereco(),
                        f.getCep(),
                        f.getComplemento(),
                        f.getCargo(),
                        f.getNumero_ctps(),
                        f.getNumero_pis()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar os funcionarios -: "+ex.getLocalizedMessage(), "Funcionarios", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void initComponents(){

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());

        lblFiltro = new JLabel("Filtrar Funcionario por Nome:");
        pnlNorte.add(lblFiltro);

        txfFiltro = new JTextField(20);
        pnlNorte.add(txfFiltro);

        btnFiltro = new JButton("Filtrar");
        btnFiltro.addActionListener(this);
        btnFiltro.setFocusable(true);
        btnFiltro.setToolTipText("btnFiltrar");
        btnFiltro.setActionCommand("botao_filtro");
        pnlNorte.add(btnFiltro);

        btnLimparFiltros = new JButton("Limpar Filtros");
        btnLimparFiltros.addActionListener(this);
        btnLimparFiltros.setFocusable(true);
        btnLimparFiltros.setToolTipText("btnLimparFiltros");
        btnLimparFiltros.setActionCommand("botao_limpar_filtros");
        btnLimparFiltros.setEnabled(false);
        pnlNorte.add(btnLimparFiltros);

        this.add(pnlNorte, BorderLayout.NORTH);

        pnlCentro = new JPanel();
        pnlCentro.setLayout(new BorderLayout());


        scpListagem = new JScrollPane();
        tblListagem =  new JTable();

        modeloTabela = new DefaultTableModel(
                new String [] {
                        "CPF",
                        "RG",
                        "Nome",
                        "Email",
                        "Numero de celular",
                        "Data de nescimento",
                        "Data de Cadastro",
                        "Endereço",
                        "Cep",
                        "Complemento",
                        "Cargo",
                        "Número CRMV",
                        "Número PIS"
                }, 0){
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }};

        tblListagem.setModel(modeloTabela);
        scpListagem.setViewportView(tblListagem);

        pnlCentro.add(scpListagem, BorderLayout.CENTER);

        this.add(pnlCentro, BorderLayout.CENTER);

        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setFocusable(true);
        btnNovo.setToolTipText("btnNovo");
        btnNovo.setMnemonic(KeyEvent.VK_N);
        btnNovo.setActionCommand("botao_novo");

        pnlSul.add(btnNovo);

        btnAlterar = new JButton("Editar");
        btnAlterar.addActionListener(this);
        btnAlterar.setFocusable(true);
        btnAlterar.setToolTipText("btnAlterar");
        btnAlterar.setActionCommand("botao_alterar");

        pnlSul.add(btnAlterar);

        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(this);
        btnRemover.setFocusable(true);
        btnRemover.setToolTipText("btnRemvoer");
        btnRemover.setActionCommand("botao_remover");

        pnlSul.add(btnRemover);


        this.add(pnlSul, BorderLayout.SOUTH);

        format = new SimpleDateFormat("dd/MM/yyyy");

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if(arg0.getActionCommand().equals(btnNovo.getActionCommand())){

            pnlfuncionario.showTela("tela_funcionarios_formulario");

            pnlfuncionario.getFormulario().setFuncionarioFormulario(null);

        }else if(arg0.getActionCommand().equals(btnAlterar.getActionCommand())){

            int indice = tblListagem.getSelectedRow();
            if(indice > -1){

                try {
                    DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();

                    Vector linha = (Vector) model.getDataVector().get(indice);

                    String s = (String) linha.get(0);

                    Funcionario f = new Funcionario();
                    PersistenciaJDBC persistencia = new PersistenciaJDBC();
                    f = (Funcionario) persistencia.find(f.getClass(), s);

                    pnlfuncionario.showTela("tela_funcionarios_formulario");
                    pnlfuncionario.getFormulario().setFuncionarioFormulario(f);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Houve um erro -:"+e.getLocalizedMessage(), "Funcionarios", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }


        }else if(arg0.getActionCommand().equals(btnRemover.getActionCommand())){


            int indice = tblListagem.getSelectedRow();
            if(indice > -1){
                try {
                    DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();

                    Vector linha = (Vector) model.getDataVector().get(indice);

                    String s = (String) linha.get(0);

                    Funcionario f = new Funcionario();
                    PersistenciaJDBC persistencia = new PersistenciaJDBC();
                    f = (Funcionario) persistencia.find(f.getClass(), s);

                    pnlfuncionario.getControle().getConexaoJDBC().remover(f);
                    JOptionPane.showMessageDialog(this, "Funcionario removido!", "Funcionario", JOptionPane.INFORMATION_MESSAGE);
                    populaTable();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover o Funcionario -:"+ex.getLocalizedMessage(), "Funcionarios", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(arg0.getActionCommand().equals(btnFiltro.getActionCommand())){
            try{
                String nome;
                nome = txfFiltro.getText();
                populaTableFiltro(nome);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Houve um erro -:"+ex.getLocalizedMessage(), "Funcionarios", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }else if(arg0.getActionCommand().equals(btnLimparFiltros.getActionCommand())){
            btnLimparFiltros.setEnabled(false);
            populaTable();
        }
    }
}
