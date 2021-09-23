package br.edu.ifpb.domain.venda;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AdicionarProduto {

    @EJB
    private Vendas vendas;

    public void adicionarProduto(Produto_venda produto_venda) {
        this.vendas.adicionarProduto(produto_venda);
    }
}
