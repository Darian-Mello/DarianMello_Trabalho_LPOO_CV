package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersistenciaJDBC implements InterfacePersistencia {
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/DarianMello_Trabalho_LPOO_CV";
    private Connection con = null;

    public PersistenciaJDBC() throws Exception {
        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com : " + URL + " ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
    }

    @Override
    public Boolean conexaoAberta() {
        try {
            if (con != null)
                return !con.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {

        if (c == Medico.class) {

            PreparedStatement ps = this.con.prepareStatement("SELECT p.cpf, p.rg, p.nome, p.senha, p.numero_celular,"
                    + " p.email, p.data_cadastro, p.data_nascimento, p.cep, p.endereco, p.complemento, m.numero_crmv"
                    + " FROM tb_pessoa AS p INNER JOIN tb_medico AS m ON p.cpf = m.cpf WHERE p.cpf = ?;");

            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Medico m = new Medico();

                m.setCpf(rs.getString("cpf"));
                m.setRg(rs.getString("rg"));
                m.setNome(rs.getString("nome"));
                m.setSenha(rs.getString("senha"));
                m.setNumero_celular(rs.getString("numero_celular"));
                m.setEmail(rs.getString("email"));
                m.setEndereco(rs.getString("endereco"));
                m.setCep(rs.getString("cep"));
                m.setComplemento(rs.getString("complemento"));
                m.setNumero_crmv(rs.getString("numero_crmv"));

                Calendar dtNascimento = Calendar.getInstance();
                dtNascimento.setTimeInMillis(rs.getDate("data_nascimento").getTime());
                m.setData_nascimento(dtNascimento);

                Calendar dtCadastro = Calendar.getInstance();
                dtCadastro.setTimeInMillis(rs.getDate("data_cadastro").getTime());
                m.setData_cadastro(dtCadastro);

                ps.close();
                return m;
            }
        } else if (c == Consulta.class) {

        }
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {
        if (o instanceof Medico) {
            Medico m = (Medico) o;

            if (m.getData_cadastro() == null) {
                PreparedStatement ps_pessoa = this.con.prepareStatement("insert into tb_pessoa"
                        + " (cpf, rg, nome, senha, numero_celular, email, cep, endereco, complemento, data_cadastro, data_nascimento, tipo)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, 'medico') ");
                ps_pessoa.setString(1, m.getCpf());
                ps_pessoa.setString(2, m.getRg());
                ps_pessoa.setString(3, m.getNome());
                ps_pessoa.setString(4, m.getSenha());
                ps_pessoa.setString(5, m.getNumero_celular());
                ps_pessoa.setString(6, m.getEmail());
                ps_pessoa.setString(7, m.getCep());
                ps_pessoa.setString(8, m.getEndereco());
                ps_pessoa.setString(9, m.getComplemento());
                ps_pessoa.setTimestamp(10, new Timestamp(m.getData_nascimento().getTimeInMillis()));

                ps_pessoa.execute();

                PreparedStatement ps_medico = this.con.prepareStatement("insert into tb_medico "
                        + "(numero_crmv, cpf) values (?, ?) ");
                ps_medico.setString(1, m.getNumero_crmv());
                ps_medico.setString(2, m.getCpf());

                ps_medico.execute();

                System.out.println("O Medico com CPF = " + m.getCpf() + " foi cadastrado com sucesso!\n");
            } else {

                PreparedStatement ps_pessoa = this.con.prepareStatement("update tb_pessoa set"
                        + " rg = ?, nome = ?, senha = ?, numero_celular = ?, email = ?, cep= ?,"
                        + " endereco = ?, complemento = ?, data_nascimento = ?, tipo = 'medico' where cpf = ?");
                ps_pessoa.setString(1, m.getRg());
                ps_pessoa.setString(2, m.getNome());
                ps_pessoa.setString(3, m.getSenha());
                ps_pessoa.setString(4, m.getNumero_celular());
                ps_pessoa.setString(5, m.getEmail());
                ps_pessoa.setString(6, m.getCep());
                ps_pessoa.setString(7, m.getEndereco());
                ps_pessoa.setString(8, m.getComplemento());
                ps_pessoa.setTimestamp(9, new Timestamp(m.getData_nascimento().getTimeInMillis()));
                ps_pessoa.setString(10, m.getCpf());

                ps_pessoa.execute();

                PreparedStatement ps_medico = this.con.prepareStatement("update tb_medico set numero_crmv = ? where cpf = ?; ");
                ps_medico.setString(1, m.getNumero_crmv());
                ps_medico.setString(2, m.getCpf());

                ps_medico.execute();

                System.out.println("O Registro do Medico com CPF = " + m.getCpf() + " foi alterado com sucesso!\n");
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {

        if(o instanceof Medico){

            Medico m = (Medico) o;

            PreparedStatement ps_medico = this.con.prepareStatement("delete from tb_medico where cpf = ?;");
            ps_medico.setString(1, m.getCpf());
            ps_medico.execute();
            PreparedStatement ps_pessoa = this.con.prepareStatement("delete from tb_pessoa where cpf = ?;");
            ps_pessoa.setString(1, m.getCpf());
            ps_pessoa.execute();

        }else if(o instanceof Consulta){

            Consulta c = (Consulta) o;

        }
    }

    @Override
    public List<Medico> listMedicos() throws Exception {
        List<Medico> lista = null;

        PreparedStatement ps = this.con.prepareStatement("SELECT p.cpf, p.rg, p.nome, p.senha, p.numero_celular,"
                                     +  " p.email, p.data_cadastro, p.data_nascimento, p.cep, p.endereco, p.complemento, m.numero_crmv"
                                     +  " FROM tb_pessoa AS p INNER JOIN tb_medico AS m ON p.cpf = m.cpf;");
        ResultSet rs = ps.executeQuery();

        lista = new ArrayList();
        while(rs.next()){
            Medico m = new Medico();

            m.setCpf(rs.getString("cpf"));
            m.setRg(rs.getString("rg"));
            m.setNome(rs.getString("nome"));
            m.setSenha(rs.getString("senha"));
            m.setNumero_celular(rs.getString("numero_celular"));
            m.setEmail(rs.getString("email"));
            m.setCep(rs.getString("cep"));
            m.setEndereco(rs.getString("endereco"));
            m.setComplemento(rs.getString("complemento"));
            m.setNumero_crmv(rs.getString("numero_crmv"));

            Calendar dtCadastro = Calendar.getInstance();
            dtCadastro.setTimeInMillis(rs.getDate("data_cadastro").getTime());
            m.setData_cadastro(dtCadastro);

            Calendar dtNascimento = Calendar.getInstance();
            dtNascimento.setTimeInMillis(rs.getDate("data_nascimento").getTime());
            m.setData_nascimento(dtNascimento);

            lista.add(m);
        }
        return lista;
    }

}
