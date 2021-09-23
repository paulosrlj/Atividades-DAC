package br.edu.ifpb.api;

import br.edu.ifpb.domain.venda.*;
import br.edu.ifpb.domain.produto.Produto;
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

@Path("vendas")
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesDeVendas {

    @Inject
    private ListaDeVendas todasAsVendas;

    @Inject
    private NovaVenda novaVenda;

    @Inject
    private AdicionarProduto adicionarProdutoVenda;

    @Inject
    private ListarProdutosDeVenda produtosDeVenda;

    @Inject
    private ListarVenda listarVenda;

    @Inject
    private DeletarVenda deletarVenda;

    @Inject
    private Vendas vendas;

    @GET
    public List<Venda> listarVendas() {
        return this.todasAsVendas.todasAsVendas();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response criarVenda(Venda venda) {
        Venda resposta = novaVenda.novo(venda);
        URI uri = URI.create("localhost:8080/core/api/vendas/" + resposta.getId());

        return Response.created(uri)
                .entity(resposta)
                .build();
    }

    @POST
    @Path("adicionarproduto")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response adicionarProdutoVenda(Produto_venda produto_venda) {
        adicionarProdutoVenda.adicionarProduto(produto_venda);
        return Response.ok()
                .build();
    }

    @GET
    @Path("listarprodutos/{id}")
    public Response listarProdutosVendas(@PathParam("id") int id) {   
        List<Produto> produtos = produtosDeVenda.listarProdutosDeVenda(id);
        URI uri = URI.create("localhost:8080/core/api/vendas/adicionarproduto/" + id);
        
        return Response.created(uri)
                .entity(produtos)
                .build();
    }
//
//    @PUT
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response atualizar(Produto produto, @PathParam("id") int id) {
//        atualizarProduto.atualizar(produto, id);
//        produto.setId(id);
//
//        return Response.ok()
//                .entity(produto)
//                .build();
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deletar(@PathParam("id") int id) {
//        deletarProduto.deletar(id);
//
//        return Response.ok()
//                .build();
//    }
}
