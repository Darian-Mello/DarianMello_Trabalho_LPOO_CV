package br.edu.ifsul.cc.lpoo.cv.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
