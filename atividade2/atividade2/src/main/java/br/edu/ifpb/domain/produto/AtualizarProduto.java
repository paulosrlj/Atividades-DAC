package br.edu.ifpb.domain.produto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AtualizarProduto {

    @EJB
    private Produtos produtos;

    public void atualizar(Produto produto, int id) {
        this.produtos.atualizar(produto, id);
    }
}
