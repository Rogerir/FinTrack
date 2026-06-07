package model;

import java.time.LocalDate;

public class Transacao {
    private Integer id;
    private String descricao;
    private double valor;
    private TipoTransacao tipo;
    private LocalDate data;

    public Transacao(){

    }

    public Transacao(String descricao, double valor, TipoTransacao tipo, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;


    }

    public Transacao(String descricao, Integer id, double valor,TipoTransacao tipo, LocalDate data ){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;

    }
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public java.time.LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tipo=" + tipo +
                ", data=" + data +
                '}';
    }

}
