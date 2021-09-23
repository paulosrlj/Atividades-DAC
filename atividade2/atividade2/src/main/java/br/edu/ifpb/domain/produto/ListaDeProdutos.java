package br.edu.ifpb.domain.produto;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListaDeProdutos {

    @EJB
    private Produtos produtos;

    public List<Produto> todosOsProdutos() {
        return this.produtos.todos();
    }
}
