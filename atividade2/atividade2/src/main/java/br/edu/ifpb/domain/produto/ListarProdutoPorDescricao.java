package br.edu.ifpb.domain.produto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListarProdutoPorDescricao {

    @EJB
    private Produtos produtos;

    public Produto localizarPorDescricao(String descricao) {
        return this.produtos.localizarPorDescricao(descricao);
    }
}
