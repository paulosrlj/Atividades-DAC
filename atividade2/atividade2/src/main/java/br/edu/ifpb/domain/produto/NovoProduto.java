package br.edu.ifpb.domain.produto;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NovoProduto {

    @EJB
    private Produtos produtos;

    public Produto novo(Produto produto) {
        Objects.requireNonNull(produto, "O produto não pode ser nulo");
        return produtos.novo(produto);
    }

}
