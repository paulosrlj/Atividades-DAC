/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.cliente.Cliente;
import br.edu.ifpb.domain.cliente.Clientes;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.List;
import javax.inject.Inject;

@Named("clientebean")
@SessionScoped
public class ControladorDeCliente implements Serializable {

    private List<Cliente> clientesList = new ArrayList<>();

    private Cliente cliente = new Cliente();

    @Inject
    private Clientes clienteJDBC;

    public List<Cliente> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Cliente> clientesList) {
        this.clientesList = clientesList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Clientes getClienteJDBC() {
        return clienteJDBC;
    }

    public void setClienteJDBC(Clientes clienteJDBC) {
        this.clienteJDBC = clienteJDBC;
    }

    public void limparCliente() {
        this.setCliente(new Cliente());
    }

    public String criarCliente() {
        this.clienteJDBC.novo(cliente);
        limparCliente();
        return "listar.xhtml?faces-redirect=true";
    }

    public String redirecionarEditarCliente(Cliente cliente) {
        this.setCliente(cliente);
        return "editar?faces-redirect=true";
    }

    public String editarCliente() {
        this.clienteJDBC.atualizar(cliente, cliente.getId());
        limparCliente();
        return "listar.xhtml?faces-redirect=true";
    }

    public String excluirCliente(Cliente cliente) {
        this.clienteJDBC.deletar(cliente.getId());
        limparCliente();
        return "listar.xhtml?faces-redirect=true";
    }

    public void listarClientes() {
        this.setClientesList(clienteJDBC.todos());
    }
}
