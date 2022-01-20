package br.edu.ifsul.cc.lpoo.cv.model;


import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @SequenceGenerator(name = "seq_pet", sequenceName = "seq_pet_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pet", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = true)
    private String nome;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_nascimento;

    @Column(nullable = true)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "raca_id", nullable = false)
    private Raca reca;

    public Pet() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Calendar data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Raca getReca() {
        return reca;
    }

    public void setReca(Raca reca) {
        this.reca = reca;
    }
}
