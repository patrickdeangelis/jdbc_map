/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_connection.model;

/**
 *
 * @author patrick
 */
public class Especificacao {
    private int codigo;
    private String fabricante;
    private String cor;
    private String sistema;
    private String detalhes;

    public Especificacao(int codigo, String fabricante, String cor, String sistema, String detalhes) {
        this.codigo = codigo;
        this.fabricante = fabricante;
        this.cor = cor;
        this.sistema = sistema;
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        return String.format("CODIGO: %d | FABRICANTE: %s | COR: %s | SISTEMA: %s | DETALHES: %s", 
                this.codigo, this.fabricante, this.cor, this.sistema, this.detalhes);
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
}
