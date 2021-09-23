/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.cliente.Cliente;
import br.edu.ifpb.domain.venda.*;
import br.edu.ifpb.domain.produto.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Named("carrinhobean")
@SessionScoped
//@Stateful
public class ControladorDeCarrinho implements Serializable {

    private List<Produto> produtosList = new ArrayList<>();
    private List<Produto> produtosDeVenda = new ArrayList<>();
    private Cliente cliente = new Cliente();
    private Venda venda = new Venda();
    private List<Venda> listaVendas = new ArrayList<>();

    @Inject
    private Vendas vendasJDBC;

    public List<Venda> getListaVendas() {
        return listaVendas;
    }

    public void setListaVendas(List<Venda> listaVendas) {
        this.listaVendas = listaVendas;
    }

    public List<Produto> getProdutosList() {
        return produtosList;
    }

    public void setProdutosList(List<Produto> produtosList) {
        this.produtosList = produtosList;
    }

    public void limparProdutos() {
        this.setProdutosList(new ArrayList<>());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Vendas getVendasJDBC() {
        return vendasJDBC;
    }

    public void setVendasJDBC(Vendas vendasJDBC) {
        this.vendasJDBC = vendasJDBC;
    }

    public List<Produto> getProdutosDeVenda() {
        return produtosDeVenda;
    }

    public void setProdutosDeVenda(List<Produto> produtosDeVenda) {
        this.produtosDeVenda = produtosDeVenda;
    }

    public Double calculaTotalCarrinho() {
        Double total = 0.0;

        total = this.produtosList
                .stream()
                .map(p -> p.getValor().doubleValue())
                .reduce(total, (accumulator, _item) -> accumulator + _item);
        return total;
    }

    public String criarVenda(int cliente_id) {
        this.setVenda(new Venda(cliente_id));
        this.vendasJDBC.novaVenda(venda);
        return "listar.xhtml?faces-redirect=true";
    }

    public List<Venda> listarVendas() {
        this.setListaVendas((vendasJDBC.listarVendas()));
        return this.listaVendas;
    }

    public String produtosDeUmaVenda(int venda_id) {
        limparProdutos();
        this.setProdutosList(vendasJDBC.listarProdutosDeVenda(venda_id));
        return "produtos_venda.xhtml?faces-redirect=true";
    }

    public String redirecionarAdicionarProduto(int venda_id, int cliente_id) {
        this.setVenda(new Venda(venda_id, cliente_id));
        System.out.println("--- VENDA ----");
        System.out.println(venda.getId());
        return "adicionar_produto.xhtml?faces-redirect=true";
    }

    public String adicionarProdutoCarrinho(int produto_id) {
        Produto_venda pv = new Produto_venda(venda.getId(), produto_id);
        System.out.println("----PV---");
        System.out.println(pv.getVenda_id());
        this.vendasJDBC.adicionarProduto(pv);

        return "listar.xhtml?faces-redirect=true";
    }

    public String excluirVenda(int venda_id) {
        this.vendasJDBC.deletarVenda(venda_id);
        return "listar.xhtml?faces-redirect=true";
    }

}
