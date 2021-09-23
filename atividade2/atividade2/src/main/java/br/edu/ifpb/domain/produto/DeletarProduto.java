package br.edu.ifpb.domain.produto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DeletarProduto {

    @EJB
    private Produtos produtos;

    public void deletar(int id) {
        this.produtos.deletar(id);
    }
}
