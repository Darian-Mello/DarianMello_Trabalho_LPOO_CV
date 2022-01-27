package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Pet;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TestPersistenciaJDBC {
    //@Test
    public void testConexao() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JDBC");

            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }

    }

    @Test
    public void testPersistenciaCV() throws Exception {
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {

            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            List<Medico> lista_medicos = persistencia.listMedicos();

            if (!lista_medicos.isEmpty()) {
                int i = 0;
                for (Medico m : lista_medicos) {
                    i++;
                    System.out.println("\n Medico" + i + ": "
                            + "\nCPF: " + m.getCpf()
                            + "\n RG: " + m.getRg()
                            + "\nNumero CRMV: " + m.getNumero_crmv()
                            + "\nNome: " + m.getNome()
                            + "\nSenha: " + m.getSenha()
                            + "\nE-mail: " + m.getEmail()
                            + "\nData de Nascimento: " + formato.format(m.getData_nascimento().getTime())
                            + "\nData de Cadastro: " + formato.format(m.getData_cadastro().getTime())
                            + "\nNúmero de Celular: " + m.getNumero_celular()
                            + "\nCEP: " + m.getCep()
                            + "\nEndereço: " + m.getEndereco()
                            + "\nComplemento: " + m.getComplemento()
                            + "\n-----------------------------------------------------------------------------------------------\n");

                    List<Consulta> listaConsultasMedico = persistencia.listConsultasDeUmMedico(m);

                    if (!listaConsultasMedico.isEmpty()) {
                        System.out.println("Consultas desse médico: ");
                        int j = 0;
                        for (Consulta c : listaConsultasMedico) {
                            j++;
                            System.out.println("\n" + j + "º Consulta:"
                                    + "\nid: " + c.getId()
                                    + "\nData: " + formato.format(c.getData().getTime())
                                    + "\nData de retorno: " + formato.format(c.getData_retorno().getTime())
                                    + "\nObservacao: " + c.getObservacao()
                                    + "\nValor: " + c.getValor()
                                    + "\nMédico: " + c.getMedico().getCpf());

                            List<Receita> r = c.getReceitas();
                            System.out.println("\nReceitas dessa consulta: ");
                            byte k = 0;
                            for (Receita res : r) {
                                k++;
                                System.out.println("Receita " + k + ":");
                                System.out.println("Id: " + res.getId()
                                        + "\nOrientação: " + res.getOrientacao());
                            }
                        }
                    }
                    System.out.println("O médico com CPF = "+ m.getCpf() + " será removido do banco de dados!");
                    persistencia.remover(m);
                }

            } else {
                System.out.println("Nenhum Médico foi encontrado! ");
                Calendar dtNascimento = Calendar.getInstance();

                Medico m = new Medico();
                m.setCpf("123.456.789-10");
                m.setNome("Pedro da Silva");
                m.setEmail("pedro@silva.com");
                m.setSenha("1234");
                m.setNumero_celular("55 991234678");
                m.setRg("987654321");
                m.setCep("123456789");
                m.setEndereco("Av. Brasil");
                m.setComplemento("Ap.");
                m.setNumero_crmv("4321");
                dtNascimento.setTime(formato.parse("19/08/1998"));
                m.setData_nascimento(dtNascimento);

                persistencia.persist(m);

                List<Consulta> lista_cosultas = persistencia.listConsultas();

                if (lista_cosultas.isEmpty()) {
                    System.out.println("Nenhuma Consulta foi encontrada! ");
                    Calendar dt = Calendar.getInstance();
                    Float valor = 2000.00f;

                    Consulta c = new Consulta();

                    dt.setTime(formato.parse("10/05/2022"));
                    c.setData_retorno(dt);
                    c.setObservacao("Voltar em maio");
                    c.setValor(valor);
                    c.setMedico(m);
                    Pet p = new Pet();
                    p.setId(1);
                    c.setPet(p);

                    persistencia.persist(c);

                    List<Receita> lista = persistencia.listReceitas();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma Receita foi encontrada! ");

                        Receita r = new Receita();

                        r.setOrientacao("Cuidar do cachorro");
                        r.setConsulta(c);
                        persistencia.persist(r);

                        Receita re = new Receita();
                        re.setOrientacao("Não estressar o cachorro.");
                        re.setConsulta(c);
                        persistencia.persist(re);
                    }
                }
            }
        } else {
            System.out.println("\nNão abriu a conexão com o BD via JDBC\n");
        }
    }

    //@Test
    public void testListPersistenciaMedico() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {

            List<Medico> lista = persistencia.listMedicos();

            if (!lista.isEmpty()) {
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Medico(s) encontrado(s):");

                int i = 0;
                for (Medico m : lista) {
                    i++;
                    System.out.println("\n" + i + "º Medico: \n"
                            + "CPF: " + m.getCpf()
                            + "\n RG: " + m.getRg()
                            + "\nNumero CRMV: " + m.getNumero_crmv()
                            + "\nNome: " + m.getNome()
                            + "\nSenha: " + m.getSenha()
                            + "\nE-mail: " + m.getEmail()
                            + "\nData de Nascimento: " + formato.format(m.getData_nascimento().getTime())
                            + "\nData de Cadastro: " + formato.format(m.getData_cadastro().getTime())
                            + "\nNúmero de Celular: " + m.getNumero_celular()
                            + "\nCEP: " + m.getCep()
                            + "\nEndereço: " + m.getEndereco()
                            + "\nComplemento: " + m.getComplemento()
                            + "\n-----------------------------------------------------------------------------------------------\n");

                    System.out.println("O médico com CPF = "+ m.getCpf() + " será removido do banco de dados!");
                    persistencia.remover(m);
                }
            } else {
                System.out.println("Nenhum Médico foi encontrado! ");

                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Calendar dtNascimento = Calendar.getInstance();

                Medico m = new Medico();

                m.setCpf("123.456.789-10");
                m.setNome("Pedro da Silva");
                m.setEmail("pedro@silva.com");
                m.setSenha("1234");
                m.setNumero_celular("55 991234678");
                m.setRg("987654321");
                m.setCep("123456789");
                m.setEndereco("Av. Brasil");
                m.setComplemento("Ap.");
                m.setNumero_crmv("4321");
                dtNascimento.setTime(formato.parse("19/08/1998"));
                m.setData_nascimento(dtNascimento);

                persistencia.persist(m);
            }

            persistencia.fecharConexao();
        } else {
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }

    //@Test
    public void testListPersistenciaConsulta() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {

            List<Consulta> lista = persistencia.listConsultas();

            if (!lista.isEmpty()) {
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Consulta(s) cadastrada(s):");

                int i = 0;
                for (Consulta c : lista) {
                    i++;
                    System.out.println("\n" + i + "º Consulta: \n"
                            + "id: " + c.getId()
                            + "\nData: " + formato.format(c.getData().getTime())
                            + "\nData de retorno: " + formato.format(c.getData_retorno().getTime())
                            + "\nObservacao: " + c.getObservacao()
                            + "\nValor: " + c.getValor()
                            + "\nMédico: " + c.getMedico().getCpf());

                    List<Receita> r = c.getReceitas();
                    System.out.println("Receitas: ");
                    byte k=0;
                    for (Receita res : r) {
                        k++;
                        System.out.println("Receita " + k + ":");
                        System.out.println("Id: " + res.getId()
                                + "\nOrientação: " + res.getOrientacao());
                    }

                    System.out.println("O Consulta com código = "+ c.getId() + " será removida do banco de dados!");
                    persistencia.remover(c);
                }
            } else {
                System.out.println("Nenhuma Consulta foi encontrada! ");

                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Calendar dt = Calendar.getInstance();
                Float valor = 2000.00f;

                Consulta c = new Consulta();

                dt.setTime(formato.parse("10/05/2022"));
                c.setData_retorno(dt);
                c.setObservacao("Voltar em maio");
                c.setValor(valor);
                Pet p = new Pet();
                p.setId(1);
                c.setPet(p);
                Medico med = new Medico();
                med.setCpf("123.456.789-10");
                c.setMedico(med);

                persistencia.persist(c);
            }

            persistencia.fecharConexao();
        } else {
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }

    //@Test
    public void testListPersistenciaReceita() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {

            List<Receita> lista = persistencia.listReceitas();

            if (!lista.isEmpty()) {

                int i = 0;
                for (Receita r : lista) {
                    i++;
                    System.out.println("\n" + i + "º Receita: \n"
                            + "id: " + r.getId()
                            + "\nOrientação: " + r.getOrientacao()
                            + "\nConsulta: " + r.getConsulta().getId()
                            + "\n-----------------------------------------------------------------------------------------------\n");

                    System.out.println("A receita com id = "+ r.getId() + " será removida do banco de dados!");
                    persistencia.remover(r);
                }
            } else {
                System.out.println("Nenhuma Receita foi encontrada! ");

                Receita r = new Receita();

                r.setOrientacao("Cuidar do cachorro");
                Consulta c = new Consulta();
                c.setId(2);
                r.setConsulta(c);
                persistencia.persist(r);

                Receita re = new Receita();
                re.setOrientacao("Não estressar o cachorro.");
                Consulta con = new Consulta();
                con.setId(2);
                re.setConsulta(con);
                persistencia.persist(re);
            }

            persistencia.fecharConexao();
        } else {
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }

}
