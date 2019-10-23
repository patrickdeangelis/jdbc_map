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
import jdbc_connection.model.Especificacao;
import jdbc_connection.util.ConnectionFactory;

/**
 *
 * @author patrick
 */
public class EspecificacaoDAO {
    
    private Connection con;
    private PreparedStatement st;
    private String sql;
    
    public int inserir(Especificacao especificacao) throws SQLException {
        con = ConnectionFactory.getConnection();
        
        sql = "insert into especificacao(fabricante, cor, sistema, detalhes) values (?, ?, ?, ?)";
        
        st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        st.setString(1, especificacao.getFabricante());
        st.setString(2, especificacao.getCor());
        st.setString(3, especificacao.getSistema());
        st.setString(4, especificacao.getDetalhes());    
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        int codigoEspecificacao = 0;
        if (rs.next()) {
            codigoEspecificacao = rs.getInt(1);
        }        
        
        con.close();
        return codigoEspecificacao;
    }
    
    public void editar(Especificacao especificacao) throws SQLException {
        con = ConnectionFactory.getConnection();
        sql = "update especificacao fabricante=?, cor=?, sistema=?, detalhes=? where codigo=?";
        
        st = con.prepareStatement(sql);
        
        st.setString(1, especificacao.getFabricante());
        st.setString(2, especificacao.getFabricante());
        st.setString(3, especificacao.getCor());
        st.setString(4, especificacao.getSistema());
        st.setInt(5, especificacao.getCodigo());
        
        st.executeUpdate();
        
        con.close();        
    }
    
    public Especificacao buscarPorCodigo(int codigo) throws SQLException{
        con = ConnectionFactory.getConnection();
        sql = "select * from especificacao where codigo=?";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, codigo);
        
        ResultSet rs = st.executeQuery();
        Especificacao especificacaoRetorno = null;
        if (rs.next()) {
            String fabricante = rs.getString(2);
            String cor = rs.getString(3);
            String sistema = rs.getString(4);
            String detalhes = rs.getString(5);
            
            especificacaoRetorno = new Especificacao(codigo, fabricante, cor, sistema, detalhes);
        }    
        
        return especificacaoRetorno;
    }
    
    public List<Especificacao> listar() throws SQLException {
        con = ConnectionFactory.getConnection();
        sql = "select * from especificacao";
        
        st = con.prepareStatement(sql);
        
        
        ResultSet rs = st.executeQuery();
        List<Especificacao> listaEspecificacoes = new ArrayList<Especificacao>();
        Especificacao especificacaoTemp = null;
        
        while(rs.next()){
            int codigo = rs.getInt(1);
            String fabricante = rs.getString(2);
            String cor = rs.getString(3);
            String sistema = rs.getString(4);
            String detalhes = rs.getString(5);
            
            especificacaoTemp = new Especificacao(codigo, fabricante, cor, sistema, detalhes);
            listaEspecificacoes.add(especificacaoTemp);
        }
        
        con.close();
        
        return listaEspecificacoes;
    }
    
    public void deletar(int codigo) throws SQLException {
        con = ConnectionFactory.getConnection();
        sql = "delete from especificacao where codigo=?";
        
        st = con.prepareStatement(sql);
        
        st.setInt(1, codigo);
        
        st.executeUpdate();
      
        con.close();
    }
    
}
