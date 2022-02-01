package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Pet;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;

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
            System.out.println("\nFechou conexao JDBC\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {

        if (c == Medico.class) {

            PreparedStatement ps = this.con.prepareStatement("SELECT p.cpf, p.rg, p.nome, p.senha, p.numero_celular,"
                    + " p.email, p.data_cadastro, p.data_nascimento, p.cep, p.endereco, p.complemento, m.numero_crmv"
                    + " FROM tb_pessoa p INNER JOIN tb_medico m ON p.cpf = m.cpf WHERE p.cpf = ?;");

            ps.setString(1, id.toString());

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
            PreparedStatement ps_consulta = this.con.prepareStatement("select id, data, data_retorno, observacao,"
                    + " valor, medico_id, pet_id from tb_consulta where id = ?;");

            ps_consulta.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps_consulta.executeQuery();

            if(rs.next()){

                Consulta con = new Consulta();
                con.setId(rs.getInt("id"));

                Calendar dtCad = Calendar.getInstance();
                dtCad.setTimeInMillis(rs.getDate("data").getTime());
                con.setData(dtCad);

                Calendar dtRet = Calendar.getInstance();
                dtRet.setTimeInMillis(rs.getDate("data_retorno").getTime());
                con.setData_retorno(dtRet);

                con.setObservacao(rs.getString("observacao"));
                con.setValor(rs.getFloat("valor"));

                Medico med = new Medico();
                med.setCpf(rs.getString("medico_id"));
                con.setMedico(med);

                Pet pet = new Pet();
                pet.setId(rs.getInt("pet_id"));
                con.setPet(pet);

                PreparedStatement ps_receita = this.con.prepareStatement("select r.id, r.orientacao from tb_receita r, tb_consulta c where r.consulta_id = c.id and c.id = ?");
                ps_receita.setInt(1, Integer.parseInt(id.toString()));

                ResultSet rs2 = ps_receita.executeQuery();

                while(rs2.next()){

                    Receita r = new Receita();
                    r.setId(rs2.getInt("id"));
                    r.setOrientacao(rs2.getString("orientacao"));

                    con.setReceita(r);
                }
                return con;
            }
        } else if (c == Receita.class) {
            PreparedStatement ps_receita = this.con.prepareStatement("select id, orientacao, consulta_id " +
                    "from tb_receita where id = ?;");

            ps_receita.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = ps_receita.executeQuery();

            if (rs.next()) {

                Receita r = new Receita();
                r.setId(rs.getInt("id"));
                r.setOrientacao(rs.getString("orientacao"));

                Consulta con = new Consulta();
                con.setId(rs.getInt("consulta_id"));
                r.setConsulta(con);

                return r;
            }
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

                //System.out.println("O Medico com CPF = " + m.getCpf() + " foi cadastrado com sucesso!\n");
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

            }
        } else if (o instanceof Consulta) {
            Consulta c = (Consulta) o;

            if (c.getData() == null) {
                PreparedStatement ps_consulta = this.con.prepareStatement("insert into tb_consulta"
                        + " (id, data, data_retorno, observacao, valor, medico_id, pet_id)"
                        + " values (nextval('seq_consulta_id'), now(), ?, ?, ?, ?, ?) returning id");

                ps_consulta.setTimestamp(1, new Timestamp(c.getData_retorno().getTimeInMillis()));
                ps_consulta.setString(2, c.getObservacao());
                ps_consulta.setFloat(3, c.getValor());
                ps_consulta.setString(4, c.getMedico().getCpf());
                ps_consulta.setInt(5, c.getPet().getId());

                ResultSet rs = ps_consulta.executeQuery();

                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }

            } else {
                PreparedStatement ps_consulta = this.con.prepareStatement("update tb_consulta set"
                        + " data_retorno = ?, observacao = ?, valor = ?, medico_id = ?, pet_id = ?  where id = ?; ");

                ps_consulta.setTimestamp(1, new Timestamp(c.getData_retorno().getTimeInMillis()));
                ps_consulta.setString(2, c.getObservacao());
                ps_consulta.setFloat(3, c.getValor());
                ps_consulta.setString(4, c.getMedico().getCpf());
                ps_consulta.setInt(5, c.getPet().getId());
                ps_consulta.setInt(6, c.getId());

            }
        } else if (o instanceof Receita) {
            Receita r = (Receita) o;

            if (r.getId() == null) {
                PreparedStatement ps_receita = this.con.prepareStatement("insert into tb_receita "
                        + "(id, orientacao, consulta_id) values (nextval('seq_receita_id'), ?, ?) returning id");
                ps_receita.setString(1, r.getOrientacao());
                ps_receita.setInt(2, r.getConsulta().getId());

                ResultSet rs = ps_receita.executeQuery();
                if (rs.next()) {
                    r.setId(rs.getInt(1));
                }

            } else {
                PreparedStatement ps_receita = this.con.prepareStatement("update tb_receita set"
                        + " orientacao = ?, consulta_id = ? where id = ?;");

                ps_receita.setString(1, r.getOrientacao());
                ps_receita.setInt(2, r.getConsulta().getId());
                System.out.println("A receita com id = " + r.getId() + " foi atualizada com sucesso!\n");
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {

        if (o instanceof Medico) {

            Medico m = (Medico) o;

            PreparedStatement ps_consulta = this.con.prepareStatement("select id, medico_id from tb_consulta where medico_id = ?;");
            ps_consulta.setString(1, m.getCpf());
            ResultSet rs = ps_consulta.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));

                PreparedStatement ps_remove_receita = this.con.prepareStatement("delete from tb_receita where consulta_id = ?;");
                ps_remove_receita.setInt(1, c.getId());
                ps_remove_receita.execute();
            }

            PreparedStatement ps_remove_consulta = this.con.prepareStatement("delete from tb_consulta where medico_id = ?;");
            ps_remove_consulta.setString(1, m.getCpf());
            ps_remove_consulta.execute();
            System.out.println("Todas as consultas e receitas relacionadas ao médico com CPF = " + m.getCpf() + ", foram removidas do banco de dados." );


            PreparedStatement ps_medico = this.con.prepareStatement("delete from tb_medico where cpf = ?;");
            ps_medico.setString(1, m.getCpf());
            ps_medico.execute();
            PreparedStatement ps_pessoa = this.con.prepareStatement("delete from tb_pessoa where cpf = ?;");
            ps_pessoa.setString(1, m.getCpf());
            ps_pessoa.execute();
            System.out.println("O médico com CPF = "+ m.getCpf() + " foi removido do banco de dados!");

        } else if(o instanceof Consulta) {

            Consulta c = (Consulta) o;

            PreparedStatement ps_receita = this.con.prepareStatement("select id, orientacao, consulta_id from tb_receita where consulta_id = ?;");
            ps_receita.setInt(1, c.getId());
            ResultSet rs = ps_receita.executeQuery();

            if (rs.next()){
                PreparedStatement ps_remove_receita = this.con.prepareStatement("delete from tb_receita where consulta_id = ?;");
                ps_remove_receita.setInt(1, c.getId());
                ps_remove_receita.execute();
                System.out.println("Todas as receitas relacionadas a consulta com id = "+ c.getId() + ", foram removidas do banco de dados!");
            }

            PreparedStatement ps_consulta = this.con.prepareStatement("delete from tb_consulta where id = ?;");
            ps_consulta.setInt(1, c.getId());
            ps_consulta.execute();
            System.out.println("A consulta com ID = "+ c.getId() + " foi removida do banco de dados!");

        } else if (o instanceof Receita) {
            Receita r = (Receita) o;

            PreparedStatement ps_receita = this.con.prepareStatement("delete from tb_receita where id = ?;");
            ps_receita.setInt(1, r.getId());
            ps_receita.execute();
            System.out.println("A receita com ID = "+ r.getId() + " foi removida do banco de dados!");
        }
    }

    @Override
    public List<Medico> listMedicos() throws Exception {
        List<Medico> lista = null;

        PreparedStatement ps = this.con.prepareStatement("SELECT p.cpf, p.rg, p.nome, p.senha, p.numero_celular,"
                                     +  " p.email, p.data_cadastro, p.data_nascimento, p.cep, p.endereco, p.complemento, m.numero_crmv"
                                     +  " FROM tb_pessoa p INNER JOIN tb_medico m ON p.cpf = m.cpf;");
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

    @Override
    public List<Consulta> listConsultas() throws Exception {
        List<Consulta> lista = null;

        PreparedStatement ps_consulta = this.con.prepareStatement("select c.id, c.data, c.data_retorno, c.observacao,"
                + " c.valor, c.medico_id, c.pet_id, p.nome as nome_medico from tb_consulta c "
                + " inner join tb_medico m on c.medico_id = m.cpf"
                + " inner join tb_pessoa p on p.cpf = m.cpf;");

        ResultSet rs = ps_consulta.executeQuery();

        lista = new ArrayList();
        while(rs.next()){

            Consulta con = new Consulta();
            con.setId(rs.getInt("id"));

            Calendar dtCad = Calendar.getInstance();
            dtCad.setTimeInMillis(rs.getDate("data").getTime());
            con.setData(dtCad);

            Calendar dtRet = Calendar.getInstance();
            dtRet.setTimeInMillis(rs.getDate("data_retorno").getTime());
            con.setData_retorno(dtRet);

            con.setObservacao(rs.getString("observacao"));
            con.setValor(rs.getFloat("valor"));

            Medico med = new Medico();
            med.setCpf(rs.getString("medico_id"));
            med.setNome(rs.getString("nome_medico"));
            con.setMedico(med);

            Pet pet = new Pet();
            pet.setId(rs.getInt("pet_id"));
            con.setPet(pet);

            PreparedStatement ps_receita = this.con.prepareStatement("select id, orientacao from tb_receita where consulta_id = ?");
            ps_receita.setInt(1, con.getId());

            ResultSet rs2 = ps_receita.executeQuery();

            while(rs2.next()){
                Receita r = new Receita();
                r.setId(rs2.getInt("id"));
                r.setOrientacao(rs2.getString("orientacao"));

                con.setReceita(r);
            }
            lista.add(con);
        }
        return lista;
    }

    @Override
    public List<Receita> listReceitas() throws Exception {
        List<Receita> lista = null;

        PreparedStatement ps_receita = this.con.prepareStatement("select id, orientacao, consulta_id from tb_receita;");

        ResultSet rs = ps_receita.executeQuery();

        lista = new ArrayList();
        while(rs.next()){
            Receita r = new Receita();
            r.setId(rs.getInt("id"));
            r.setOrientacao(rs.getString("orientacao"));

            Consulta con = new Consulta();
            con.setId(rs.getInt("consulta_id"));
            r.setConsulta(con);

            lista.add(r);
        }
        return lista;
    }

    @Override
    public List<Consulta> listConsultasDeUmMedico(Object o) throws Exception {
        List<Consulta> lista = null;

        lista = new ArrayList();
        if (o instanceof Medico) {
            Medico m = (Medico) o;

            PreparedStatement ps_consulta = this.con.prepareStatement("select c.id, c.data, c.data_retorno, c.observacao,"
                    + " c.valor, c.medico_id, c.pet_id, p.nome as nome_medico from tb_consulta c "
                    + " inner join tb_medico m on c.medico_id = m.cpf"
                    + " inner join tb_pessoa p on m.cpf = p.cpf"
                    + " where medico_id = ?;");

            ps_consulta.setString(1, m.getCpf());
            ResultSet rs = ps_consulta.executeQuery();

            while (rs.next()) {
                Consulta con = new Consulta();
                con.setId(rs.getInt("id"));

                Calendar dtCad = Calendar.getInstance();
                dtCad.setTimeInMillis(rs.getDate("data").getTime());
                con.setData(dtCad);

                Calendar dtRet = Calendar.getInstance();
                dtRet.setTimeInMillis(rs.getDate("data_retorno").getTime());
                con.setData_retorno(dtRet);

                con.setObservacao(rs.getString("observacao"));
                con.setValor(rs.getFloat("valor"));

                Medico med = new Medico();
                med.setCpf(rs.getString("medico_id"));
                med.setNome(rs.getString("nome_medico"));
                con.setMedico(med);

                Pet pet = new Pet();
                pet.setId(rs.getInt("pet_id"));
                con.setPet(pet);

                PreparedStatement ps_receita = this.con.prepareStatement("select id, orientacao, consulta_id from tb_receita where consulta_id = ?");
                ps_receita.setInt(1, con.getId());

                ResultSet rs2 = ps_receita.executeQuery();

                while(rs2.next()){
                    Receita r = new Receita();
                    r.setId(rs2.getInt("id"));
                    r.setOrientacao(rs2.getString("orientacao"));
                    Consulta c = new Consulta();
                    c.setId(rs2.getInt("consulta_id"));
                    r.setConsulta(con);

                    con.setReceita(r);
                }
                lista.add(con);
            }
        }
        else {
            System.out.println("\nO objeto informado não é um médico!\n");
        }
        return lista;
    }
}
