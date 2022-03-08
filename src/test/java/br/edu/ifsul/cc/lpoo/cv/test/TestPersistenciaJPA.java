package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJPA;
import org.junit.Test;

public class TestPersistenciaJPA {
    //@Test
    public void testConexaoGeracaoTabelas(){

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");
            persistencia.fecharConexao();

        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

    //@Test
    public void testGeracaoPessoaLogin() throws Exception {

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");

            Pessoa p = persistencia.doLogin("pedro@silva.com", "1234");

            if(p == null){
                p = new Pessoa();
                p.setEmail("pedro@silva.com");
                p.setSenha("1234");
                System.out.println("Cadastrou a nova pessoa!");
            }else{
                System.out.println("Encontrou com a pessoa cadastrada!");
            }

            persistencia.fecharConexao();

        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }
}
