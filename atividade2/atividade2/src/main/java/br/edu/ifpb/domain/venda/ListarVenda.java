package br.edu.ifpb.domain.venda;

import br.edu.ifpb.domain.produto.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListarVenda {

    @EJB
    private Vendas vendas;

    public Venda listar(int id) {
        return this.vendas.localizarVenda(id);
    }
}
