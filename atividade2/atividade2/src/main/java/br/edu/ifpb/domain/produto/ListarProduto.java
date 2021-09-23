package br.edu.ifpb.domain.produto;

import br.edu.ifpb.domain.cliente.*;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListarProduto {

    @EJB
    private Produtos produtos;

    public Produto listar(int id) {
        return this.produtos.localizar(id);
    }
}
