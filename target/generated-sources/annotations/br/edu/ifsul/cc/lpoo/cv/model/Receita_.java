package br.edu.ifsul.cc.lpoo.cv.model;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-01-23T20:45:28")
@StaticMetamodel(Receita.class)
public class Receita_ { 

    public static volatile SingularAttribute<Receita, String> orientacao;
    public static volatile ListAttribute<Receita, Produto> produtos;
    public static volatile SingularAttribute<Receita, Integer> id;
    public static volatile SingularAttribute<Receita, Consulta> consulta;

}