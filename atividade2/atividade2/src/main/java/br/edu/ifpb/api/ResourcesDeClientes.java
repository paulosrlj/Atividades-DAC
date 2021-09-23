package br.edu.ifpb.api;

import br.edu.ifpb.domain.cliente.AtualizarCliente;
import br.edu.ifpb.domain.cliente.Cliente;
import br.edu.ifpb.domain.cliente.Clientes;
import br.edu.ifpb.domain.cliente.ListaDeClientes;
import br.edu.ifpb.domain.cliente.ListarCliente;
import br.edu.ifpb.domain.cliente.DeletarCliente;
import br.edu.ifpb.domain.cliente.NovoCliente;
import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

//@Stateless -> escopo de requisição
@Path("clientes") //localhost:8080/core/api/clientes
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesDeClientes {

    @Inject
    private ListaDeClientes todosOsClientes;

    @Inject
    private NovoCliente novoCliente;

    @Inject
    private ListarCliente listarCliente;

    @Inject
    private AtualizarCliente atualizarCliente;

    @Inject
    private DeletarCliente deletarCliente;

    @Inject
    private Clientes clientes;

    @GET
    public List<Cliente> todos() {
        return this.todosOsClientes.todosOsClientes();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criarCliente(Cliente cliente) {
        Cliente resposta = novoCliente.novo(cliente);
        URI uri = URI.create("localhost:8080/core/api/clientes/" + resposta.getId());

        return Response.created(uri) //201
                .entity(resposta)
                .build();
    }

    @GET
    @Path("{id}")
    public Response localizar(@PathParam("id") int id) {
        Cliente cliente = listarCliente.listar(id);

        return Response.ok()
                .entity(cliente)
                .build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(Cliente cliente, @PathParam("id") int id) {
        atualizarCliente.atualizar(cliente, id);
        cliente.setId(id);

        return Response.ok()
                .entity(cliente)
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") int id) {
        deletarCliente.deletar(id);

        return Response.ok()
                .build();
    }

}
