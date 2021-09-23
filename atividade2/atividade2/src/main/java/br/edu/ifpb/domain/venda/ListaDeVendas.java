package br.edu.ifpb.domain.venda;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListaDeVendas {

    @EJB
    private Vendas vendas;

    public List<Venda> todasAsVendas() {
        return this.vendas.listarVendas();
    }
}
