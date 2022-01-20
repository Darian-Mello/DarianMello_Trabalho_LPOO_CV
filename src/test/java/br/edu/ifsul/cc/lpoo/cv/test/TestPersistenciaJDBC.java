package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Medico;
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

}
