/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_connection.cli;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import jdbc_connection.dao.ProdutoDAO;
import jdbc_connection.model.Especificacao;
import jdbc_connection.model.Produto;

/**
 *
 * @author patrick
 */
public class CoreCLI {
    ProdutoDAO produtoDAO;
    Scanner scan;
    
    public CoreCLI() {
        this.produtoDAO = new ProdutoDAO();
        this.scan = new Scanner(System.in);
    }
    
    public void main() {
        while(true) {            
            System.out.println("SISTEMA CADASTRO DE PRODUTOS\n"
                         + "    1 - Listar Produtos\n"
                         + "    2 - Buscar produto por codigo\n"
                         + "    3 - Inserir produto\n"
                         + "    4 - Excluir produto\n"
                         + "    5 - Sair"
                         + "--------------------------------"
                         + "Selecione sua opção    ");
            int valorMenu = scan.nextInt();
            switch(valorMenu) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    buscarProdutoPoCodigo();
                    break;
                case 3:
                    inserirProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Valor inválido, tente novamente.");
                    break;
            }
        }
    }

    private void listarProdutos() {
        try {
            List<Produto> listaProdutos = produtoDAO.listar();
            
            if(listaProdutos.size() <= 0) {
                System.out.println("Sem produtos cadastrados");
            } else {
                for(Produto produto: listaProdutos) {
                    System.out.println(produto.toString());                
                }
            }            
        } catch(Exception err) {
            System.out.println("Error: " + err.getMessage());
        }
        System.out.println("");
        
    }

    private void buscarProdutoPoCodigo() {
        System.out.println("Digite o código: ");
        int codigo = scan.nextInt();
        
        try {
            Produto produto = produtoDAO.buscarPorCodigo(codigo);
            if(produto != null) {
                System.out.println(produto);
            } else {
                System.out.println("Produto não encontrado");
            }
        } catch (SQLException err) {
            System.out.println("Error: " + err.getMessage());
        }
        System.out.println("");
    }

    private void inserirProduto() {
        scan.nextLine();
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Preço: ");
        double preco = scan.nextDouble();
        scan.nextLine();
        System.out.print("Fabricante: ");
        String fabricante = scan.nextLine();

        
        System.out.print("Cor: ");
        String cor = scan.nextLine();

        System.out.print("Sistema: ");
        String sistema = scan.nextLine();

        System.out.print("Detalhes: ");
        String detalhes = scan.nextLine();
        
        Especificacao esp = new Especificacao(0, fabricante, cor, sistema, detalhes);
        Produto prod = new Produto(0, nome, preco, esp);
        
        try {
            produtoDAO.inserir(prod);
            System.out.println("Produto cadastrado");
        } catch(SQLException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }

    private void excluirProduto() {
        System.out.println("Digite o código: ");
        int codigo = scan.nextInt();
        
        try {
            produtoDAO.deletar(codigo);
            System.out.println("Produto Excluido!");
        } catch (SQLException err) {
            System.out.println("Error: " + err.getMessage());
        }
        System.out.println("");
    }
}
