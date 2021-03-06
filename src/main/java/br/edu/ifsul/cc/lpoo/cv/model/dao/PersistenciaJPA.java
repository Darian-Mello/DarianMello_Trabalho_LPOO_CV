package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PersistenciaJPA implements InterfacePersistencia {

    public EntityManagerFactory factory;
    public EntityManager entity;

    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("DarianMello_Trabalho_LPOO_CV");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        entity.close();
    }

    @Override
    public Object verificaPessoaExiste(String cpf) throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public void persist(Object o) throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public void remover(Object o) throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public List<Medico> listMedicos() throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public List<Medico> listMedicosFiltro(String nome) throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public List<Funcionario> listFuncionarios() throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public List<Funcionario> listFuncionariosFiltro (String nome) throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public List<Consulta> listConsultas() throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public List<Receita> listReceitas() throws Exception {
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    public List<Consulta> listConsultasDeUmMedico(Object o) throws Exception{
        throw new UnsupportedOperationException("Funcionalidade não disponivel no momento.");
    }

    @Override
    public Pessoa doLogin(String email, String senha) throws Exception {

        List<Pessoa> list = entity.createNamedQuery("Pessoa.login").setParameter("paramN", email).setParameter("paramS", senha).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }

    }
}
