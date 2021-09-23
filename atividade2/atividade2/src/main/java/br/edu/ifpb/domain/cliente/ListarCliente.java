package br.edu.ifpb.domain.cliente;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListarCliente {

    @EJB
    private Clientes clientes;

    public Cliente listar(int id) {
        return this.clientes.localizar(id);
    }
}
