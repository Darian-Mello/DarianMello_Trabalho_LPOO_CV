package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;

import java.util.List;

public interface InterfacePersistencia {
    public Boolean conexaoAberta();

    public void fecharConexao();

    public Object find(Class c, Object id) throws Exception;

    public void persist(Object o) throws Exception;

    public void remover(Object o) throws Exception;

    public List<Medico> listMedicos() throws Exception;

    public List<Medico> listMedicosFiltro(String nome) throws Exception;

    public List<Consulta> listConsultas() throws Exception;

    public List<Receita> listReceitas() throws Exception;

    public List<Consulta> listConsultasDeUmMedico(Object o) throws Exception;

    public Pessoa doLogin(String email, String senha) throws Exception;
}
