package Pizzaria;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    private Tamanho tamanho;

    @ManyToOne
    private Borda borda;

    @ManyToMany
    private List<Sabor> sabores;

    @ManyToOne
    private Pedido pedido;


    public Pizza () {}

    public Pizza(int quantidade, Tamanho tamanho, Borda borda, List<Sabor> sabores, Pedido pedido) {
        this.quantidade = quantidade;
        this.tamanho = tamanho;
        this.borda = borda;
        this.sabores = sabores;
        this.pedido = pedido;
    }

    public Pizza(int quantidade, Tamanho tamanho, Borda borda, Sabor sabor, Pedido pedido) {
        this.quantidade = quantidade;
        this.tamanho = tamanho;
        this.borda = borda;
        this.sabores = new ArrayList<>();
        this.sabores.add(sabor);
        this.pedido = pedido;
    }

    public Pizza(int quantidade, Tamanho tamanho, Borda borda, Sabor sabor1, Sabor sabor2, Pedido pedido) {
        this.quantidade = quantidade;
        this.tamanho = tamanho;
        this.borda = borda;
        this.sabores = new ArrayList<>();
        this.sabores.add(sabor1);
        this.sabores.add(sabor2);
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Borda getBorda() {
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public List<Sabor> getSabores() {
        return sabores;
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setSaboresFirst(Sabor sabor) {
        this.sabores.set(0,sabor);
    }
    public void setSaboresLast(Sabor sabor) {
        this.sabores.set(1,sabor);
    }

    public Double getPreco () {
        Double precoSabores = 0.0;

        for (int i = 0; i < this.getSabores().size(); i++) {
            precoSabores += this.getSabores().get(i).getPreco()/12 * this.getTamanho().getTamanho();
        }

        if (this.borda != null) {
            return ((this.borda.getPreco()/12*this.getTamanho().getTamanho()) + precoSabores) * this.getQuantidade();
        }

        return precoSabores * this.getQuantidade();

    }
}
