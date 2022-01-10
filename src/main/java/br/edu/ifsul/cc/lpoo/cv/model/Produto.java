package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Float valor;

    @Column(nullable = false)
    private Float quantidade;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipo_produto;

    Produto() {}

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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public TipoProduto getTipo_produto() {
        return tipo_produto;
    }

    public void setTipo_produto(TipoProduto tipo_produto) {
        this.tipo_produto = tipo_produto;
    }
}
