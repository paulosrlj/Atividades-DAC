package br.edu.ifpb.domain.venda;

import br.edu.ifpb.domain.produto.Produto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListarProdutosDeVenda {

    @EJB
    private Vendas vendas;

    public List<Produto> listarProdutosDeVenda(int id) {
        return this.vendas.listarProdutosDeVenda(id);
    }
}
