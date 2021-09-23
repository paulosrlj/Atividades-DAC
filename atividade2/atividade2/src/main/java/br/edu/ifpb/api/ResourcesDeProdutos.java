package br.edu.ifpb.api;

import br.edu.ifpb.domain.produto.*;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("produtos") //localhost:8080/core/api/clientes
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesDeProdutos {

    @Inject
    private ListaDeProdutos todosOsProdutos;

    @Inject
    private NovoProduto novoCliente;

    @Inject
    private ListarProduto listarProduto;

    @Inject
    private ListarProdutoPorDescricao listarProdutoPorDescricao;

    @Inject
    private AtualizarProduto atualizarProduto;

    @Inject
    private DeletarProduto deletarProduto;

    @Inject
    private Produtos produtos;

    @GET
    public Response todos() {
        List<Produto> produtosBuscados = this.todosOsProdutos.todosOsProdutos();
        URI uri = URI.create("localhost:8080/core/api/produtos/");

        return Response.created(uri)
                .entity(produtosBuscados)
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criarProduto(Produto produto) {
        Produto resposta = novoCliente.novo(produto);
        URI uri = URI.create("localhost:8080/core/api/produtos/" + resposta.getId());

        return Response.created(uri)
                .entity(resposta)
                .build();
    }

    @GET
    @Path("{id}")
    public Response localizar(@PathParam("id") int id) {
        Produto produto = listarProduto.listar(id);

        return Response.ok()
                .entity(produto)
                .build();
    }

    @GET
    @Path("listarpordescricao/{descricao}")
    public Response localizarPorDescricao(@PathParam("descricao") String descricao) {
        Produto produto = listarProdutoPorDescricao.localizarPorDescricao(descricao);

        return Response.ok()
                .entity(produto)
                .build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(Produto produto, @PathParam("id") int id) {
        atualizarProduto.atualizar(produto, id);
        produto.setId(id);

        return Response.ok()
                .entity(produto)
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") int id) {
        deletarProduto.deletar(id);

        return Response.ok()
                .build();
    }

}
