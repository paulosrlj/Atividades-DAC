package br.edu.ifpb.domain.cliente;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DeletarCliente {

    @EJB
    private Clientes clientes;

    public void deletar(int id) {
        this.clientes.deletar(id);
    }
}
