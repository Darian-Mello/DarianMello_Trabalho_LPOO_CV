package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_medico")
@DiscriminatorValue("medico")
public class Medico extends Pessoa{

    @Column(nullable = false)
    private String numero_crmv;

    Medico() {}

    public void setNumero_crmv(String numero_crmv) {
        this.numero_crmv = numero_crmv;
    }

    public String getNumero_crmv() {
        return numero_crmv;
    }
}
