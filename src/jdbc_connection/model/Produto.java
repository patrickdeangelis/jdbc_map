/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_connection.model;

/**
 * codigo, nome, preco, especificacao
 * @author patrick
 */
public class Produto {
    
    private int codigo;
    private String nome;
    private double preco;
    private Especificacao especificacao;

    public Produto(int codigo, String nome, double preco, Especificacao especificacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.especificacao = especificacao;
    }     

    @Override
    public String toString() {
        return String.format("CÓDIGO: %d | NOME: %S | PREÇO R$ %.2f\n   ESPECIFICAÇÃO: (%s)",
                this.codigo, this.nome, this.preco, this.especificacao.toString());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }    
}
