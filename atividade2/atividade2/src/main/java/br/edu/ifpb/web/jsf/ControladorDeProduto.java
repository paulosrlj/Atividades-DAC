/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.produto.Produto;
import br.edu.ifpb.domain.produto.Produtos;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.List;
import javax.inject.Inject;

@Named("produtobean")
@SessionScoped
public class ControladorDeProduto implements Serializable {

    private List<Produto> produtoList = new ArrayList<>();

    private Produto produto = new Produto();

    @Inject
    private Produtos produtoJDBC;

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produtos getProdutoJDBC() {
        return produtoJDBC;
    }

    public void setProdutoJDBC(Produtos produtoJDBC) {
        this.produtoJDBC = produtoJDBC;
    }

    public void limparProduto() {
        this.setProduto(new Produto());
    }

    public String criarProduto() {
        this.produtoJDBC.novo(produto);
        limparProduto();
        return "listar.xhtml?faces-redirect=true";
    }

    public String redirecionarEditarProduto(Produto produto) {
        this.setProduto(produto);
        return "editar?faces-redirect=true";
    }

    public String editarProduto() {
        this.produtoJDBC.atualizar(produto, produto.getId());
        limparProduto();
        return "listar.xhtml?faces-redirect=true";
    }

    public String excluirProduto(Produto produto) {
        this.produtoJDBC.deletar(produto.getId());
        limparProduto();
        return "listar.xhtml?faces-redirect=true";
    }

    public List<Produto> listarProdutos() {
        this.setProdutoList(produtoJDBC.todos());
        return this.produtoList;
    }

    public void listarProdutoPorDescricao() {
        this.setProduto(produtoJDBC.localizarPorDescricao(produto.getDescricao()));
    }
}
