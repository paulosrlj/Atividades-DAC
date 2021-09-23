package br.edu.ifpb.domain.venda;

import br.edu.ifpb.domain.produto.*;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NovaVenda {

    @EJB
    private Vendas vendas;

    public Venda novo(Venda venda) {
        Objects.requireNonNull(venda, "A venda não pode estar nula");
        return vendas.novaVenda(venda);
    }

}
