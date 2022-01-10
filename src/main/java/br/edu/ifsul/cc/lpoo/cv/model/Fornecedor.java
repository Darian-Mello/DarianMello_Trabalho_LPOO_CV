package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tb_fornecedor")
@DiscriminatorValue("fornecedor")
public class Fornecedor extends Pessoa{

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String ie;

    Fornecedor() {}

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getIe() {
        return ie;
    }
}
