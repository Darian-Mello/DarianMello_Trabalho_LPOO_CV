package br.edu.ifsul.cc.lpoo.cv.model.dao;

public interface InterfacePersistencia {
    public Boolean conexaoAberta();

    public void fecharConexao();
}
