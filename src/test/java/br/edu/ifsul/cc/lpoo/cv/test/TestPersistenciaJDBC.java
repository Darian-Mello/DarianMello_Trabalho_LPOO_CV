package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Pet;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import javassist.expr.Instanceof;
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
                int cont_medico = 0;
                for (Medico m : lista_medicos) {
                    cont_medico++;
                    System.out.println("\nMEDICO " + cont_medico + ": "
                            + "\nCPF: " + m.getCpf()
                            + "\nRG: " + m.getRg()
                            + "\nNumero CRMV: " + m.getNumero_crmv()
                            + "\nNome: " + m.getNome()
                            + "\nSenha: " + m.getSenha()
                            + "\nE-mail: " + m.getEmail()
                            + "\nData de Nascimento: " + formato.format(m.getData_nascimento().getTime())
                            + "\nData de Cadastro: " + formato.format(m.getData_cadastro().getTime())
                            + "\nNúmero de Celular: " + m.getNumero_celular()
                            + "\nCEP: " + m.getCep()
                            + "\nEndereço: " + m.getEndereco()
                            + "\nComplemento: " + m.getComplemento());

                    List<Consulta> listaConsultasMedico = persistencia.listConsultasDeUmMedico(m);

                    if (!listaConsultasMedico.isEmpty()) {
                        System.out.println("\nCONSULTAS DO MÉDICO " + cont_medico + ":\n");
                        int cont_consulta = 0;
                        for (Consulta c : listaConsultasMedico) {
                            cont_consulta++;
                            System.out.println("CONSULTA " + cont_consulta + ":"
                                    + "\nid: " + c.getId()
                                    + "\nData: " + formato.format(c.getData().getTime())
                                    + "\nData de retorno: " + formato.format(c.getData_retorno().getTime())
                                    + "\nObservacao: " + c.getObservacao()
                                    + "\nValor: " + c.getValor()
                                    + "\nPet: " + c.getPet().getId()
                                    + "\nMédico: " + c.getMedico().getNome() + ", Id:  " + c.getMedico().getCpf());

                            List<Receita> r = c.getReceitas();

                            if(r != null && !r.isEmpty()) {
                                System.out.println("\nRECEITAS DESSA CONSULTA:\n");
                                byte cont_receita = 0;
                                for (Receita res : r) {
                                    cont_receita++;
                                    System.out.println("RECEITA " + cont_receita + ":"
                                        + "\nId: " + res.getId()
                                        + "\nOrientação: " + res.getOrientacao());
                                }
                            }
                        }
                    }
                    if (cont_medico == 1) {
                        System.out.print("\n");
                        persistencia.remover(m);
                    }

                }

            } else {
                System.out.println("\nNenhum Médico foi encontrado! ");
                Calendar dtNascimento = Calendar.getInstance();
                Medico query_medico = new Medico();

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
                query_medico = (Medico) persistencia.find(m.getClass(), m.getCpf());
                System.out.println("1º Medico inserido: " + query_medico.getNome());

                Medico me = new Medico();
                me.setCpf("010.987.654-32");
                me.setNome("Joana Ramos");
                me.setEmail("joana@ramos.com");
                me.setSenha("4321");
                me.setNumero_celular("55 999999999");
                me.setRg("123456789");
                me.setCep("789456123");
                me.setEndereco("7 de Setemobro");
                me.setComplemento("Casa");
                me.setNumero_crmv("1234");
                dtNascimento.setTime(formato.parse("15/04/1990"));
                me.setData_nascimento(dtNascimento);

                persistencia.persist(me);
                query_medico = (Medico) persistencia.find(me.getClass(), me.getCpf());
                System.out.println("2° Medico inserido: " + query_medico.getNome());

                List<Consulta> lista_cosultas = persistencia.listConsultas();

                if (lista_cosultas.isEmpty()) {
                    System.out.println("\nNenhuma Consulta foi encontrada! ");
                    Calendar dt = Calendar.getInstance();
                    Float valor = 2000.00f;
                    Consulta query_consulta = new Consulta();

                    Pet p = new Pet();
                    p.setId(1);

                    Consulta c = new Consulta();
                    dt.setTime(formato.parse("10/05/2022"));
                    c.setData_retorno(dt);
                    c.setObservacao("Essa consulta NÃO possui receitas");
                    c.setValor(valor);
                    c.setMedico(m);
                    c.setPet(p);

                    persistencia.persist(c);
                    query_consulta = (Consulta) persistencia.find(c.getClass(), c.getId());
                    System.out.println("A consulta " + query_consulta.getId() + " foi inserida, pelo médico "
                            + query_consulta.getMedico().getNome() + ", CPF = " + query_consulta.getMedico().getCpf());

                    c = new Consulta();
                    dt.setTime(formato.parse("10/05/2022"));
                    c.setData_retorno(dt);
                    c.setObservacao("Essa consulta possui receitas");
                    c.setValor(valor);
                    c.setMedico(me);
                    c.setPet(p);

                    persistencia.persist(c);
                    query_consulta = (Consulta) persistencia.find(c.getClass(), c.getId());
                    System.out.println("A consulta " + query_consulta.getId() + " foi inserida, pelo médico "
                            + query_consulta.getMedico().getNome() + ", CPF = " + query_consulta.getMedico().getCpf());

                    List<Receita> lista = persistencia.listReceitas();

                    if (lista.isEmpty()) {
                        System.out.println("\nNenhuma Receita foi encontrada! ");
                        Receita query_receita = new Receita();

                        Receita r = new Receita();

                        r.setOrientacao("Cuidar do cachorro");
                        r.setConsulta(c);
                        persistencia.persist(r);
                        query_receita = (Receita) persistencia.find(r.getClass(), r.getId());
                        System.out.println("A receita " + query_receita.getId() + " foi inserida, pela consulta com id = "
                                + query_receita.getConsulta().getId());

                        r = new Receita();
                        r.setOrientacao("Não estressar o cachorro.");
                        r.setConsulta(c);
                        persistencia.persist(r);
                        query_receita = (Receita) persistencia.find(r.getClass(), r.getId());
                        System.out.println("A receita " + query_receita.getId() + " foi inserida, pela consulta com id = "
                                + query_receita.getConsulta().getId());
                    }
                }
            }
        } else {
            System.out.println("\nNão abriu a conexão com o BD via JDBC\n");
        }
    }

    @Test
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
                            + "\nRG: " + m.getRg()
                            + "\nNumero CRMV: " + m.getNumero_crmv()
                            + "\nNome: " + m.getNome()
                            + "\nSenha: " + m.getSenha()
                            + "\nE-mail: " + m.getEmail()
                            + "\nData de Nascimento: " + formato.format(m.getData_nascimento().getTime())
                            + "\nData de Cadastro: " + formato.format(m.getData_cadastro().getTime())
                            + "\nNúmero de Celular: " + m.getNumero_celular()
                            + "\nCEP: " + m.getCep()
                            + "\nEndereço: " + m.getEndereco()
                            + "\nComplemento: " + m.getComplemento());

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
            List<Medico> lista_medico = persistencia.listMedicos();

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
                            + "\nMédico: " + c.getMedico().getNome() + ", Id = " + c.getMedico().getCpf());

                    List<Receita> r = c.getReceitas();

                    if(r != null && !r.isEmpty()) {
                        System.out.println("\nRECEITAS DESSA CONSULTA:\n");
                        byte cont_receita = 0;
                        for (Receita res : r) {
                            cont_receita++;
                            System.out.println("RECEITA " + cont_receita + ":"
                                    + "\nId: " + res.getId()
                                    + "\nOrientação: " + res.getOrientacao());
                        }
                    }

                    persistencia.remover(c);
                }
            } else if (!lista_medico.isEmpty()) {
                System.out.println("Nenhuma Consulta foi encontrada! ");

                Medico med = new Medico();
                for (Medico m : lista_medico) {
                    med.setCpf(m.getCpf());
                    break;
                }


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
            List<Consulta> lista_consulta = persistencia.listConsultas();

            if (!lista.isEmpty()) {

                int i = 0;
                for (Receita r : lista) {
                    i++;
                    System.out.println("\n" + i + "º Receita: \n"
                            + "id: " + r.getId()
                            + "\nOrientação: " + r.getOrientacao()
                            + "\nConsulta: " + r.getConsulta().getId());

                    persistencia.remover(r);
                }
            } else if (!lista_consulta.isEmpty()) {
                System.out.println("Nenhuma Receita foi encontrada! ");

                Receita r = new Receita();
                Consulta c = new Consulta();
                for (Consulta con : lista_consulta) {
                    c.setId(con.getId());
                    break;
                }

                r.setOrientacao("Cuidar do cachorro");
                r.setConsulta(c);
                persistencia.persist(r);
            }

            persistencia.fecharConexao();
        } else {
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }

}
