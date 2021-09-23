package br.edu.ifpb.domain.cliente;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ListaDeClientes {

    @EJB
    private Clientes clientes;

    public List<Cliente> todosOsClientes() {
        return this.clientes.todos();
    }
}
