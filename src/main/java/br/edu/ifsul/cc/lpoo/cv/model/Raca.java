package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_raca")
public class Raca implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_raca", sequenceName = "seq_raca_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_raca", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    public Raca() {}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Especie getEspecie() {
        return especie;
    }
}
