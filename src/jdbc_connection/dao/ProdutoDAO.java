/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_connection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc_connection.model.*;
import jdbc_connection.util.ConnectionFactory;

/**
 *
 * @author patrick
 */
public class ProdutoDAO {
    
    private Connection con;
    private PreparedStatement st;
    private String sql;
    private EspecificacaoDAO especificacaoDAO;
    
    public ProdutoDAO() {
        this.especificacaoDAO = new EspecificacaoDAO();
    }
    
    public void inserir(Produto produto) throws SQLException {
        int codigoEspecificacao = especificacaoDAO.inserir(produto.getEspecificacao());
        con = ConnectionFactory.getConnection();
                    
        sql = "insert into produto(nome, preco, especificacao) values (?, ?, ?)";
        
        st = con.prepareStatement(sql);
        
        st.setString(1, produto.getNome());
        st.setDouble(2, produto.getPreco());
        st.setInt(3, codigoEspecificacao);
        
        st.executeUpdate();
        
        con.close();
    }
    
    public void editar(Produto produto) throws SQLException{
        sql = "update produto nome=?, preco=?, especificacao=? where codigo=?";
        con = ConnectionFactory.getConnection();
        
        st = con.prepareStatement(sql);
        st.setString(1, produto.getNome());
        st.setDouble(2, produto.getPreco());
        st.setInt(3, produto.getEspecificacao().getCodigo());
        st.setInt(4, produto.getCodigo());
        
        st.executeUpdate();
        
        con.close();
        
        especificacaoDAO.editar(produto.getEspecificacao());
    }
    
    public Produto buscarPorCodigo(int codigo) throws SQLException {
        sql = "select * from produto where codigo=?";
        con = ConnectionFactory.getConnection();
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, codigo);

        ResultSet rs = st.executeQuery();
        Produto produtoRetorno = null;
        if(rs.next()) {
            String nome = rs.getString(2);
            double preco = rs.getDouble(3);
            Especificacao especificacao = especificacaoDAO.buscarPorCodigo(rs.getInt(4));
            produtoRetorno = new Produto(codigo, nome, preco, especificacao);
        }
        
        con.close();
        return produtoRetorno;
    }
    
    public List<Produto> listar() throws SQLException {
        sql = "select * from produto";
        con = ConnectionFactory.getConnection();
        
        st = con.prepareStatement(sql);
        
        ResultSet rs = st.executeQuery();
        List<Produto> listaRetorno = new ArrayList<>();
        while(rs.next()) {
            int codigo = rs.getInt(1);
            String nome = rs.getString(2);
            double preco = rs.getDouble(3);
            Especificacao especificacaoTemp = especificacaoDAO.buscarPorCodigo(rs.getInt(4));
            
            Produto produtoTemp = new Produto(codigo, nome, preco, especificacaoTemp);
            listaRetorno.add(produtoTemp);
        }
        con.close();
        return listaRetorno;
    }
    
    public void deletar(int codigo) throws SQLException {
        sql = "delete from produto where codigo=?";
        con = ConnectionFactory.getConnection();
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, codigo);
        
        st.executeUpdate();
        
        con.close();
    }
}
