package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Medico;

import java.util.List;

public interface InterfacePersistencia {
    public Boolean conexaoAberta();

    public void fecharConexao();

    public Object find(Class c, Object id) throws Exception;

    public void persist(Object o) throws Exception;

    public void remover(Object o) throws Exception;

    public List<Medico> listMedicos() throws Exception;
}
