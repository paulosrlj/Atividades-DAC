package br.edu.ifpb.domain.cliente;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AtualizarCliente {

    @EJB
    private Clientes clientes;

    public void atualizar(Cliente cliente, int id) {
        this.clientes.atualizar(cliente, id);
    }
}
