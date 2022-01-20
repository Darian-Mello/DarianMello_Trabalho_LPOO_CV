package br.edu.ifsul.cc.lpoo.cv.model;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Raca;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-01-19T22:29:30")
@StaticMetamodel(Pet.class)
public class Pet_ { 

    public static volatile SingularAttribute<Pet, Cliente> cliente;
    public static volatile SingularAttribute<Pet, String> observacao;
    public static volatile SingularAttribute<Pet, Raca> reca;
    public static volatile SingularAttribute<Pet, Calendar> data_nascimento;
    public static volatile SingularAttribute<Pet, String> nome;
    public static volatile SingularAttribute<Pet, Integer> id;

}