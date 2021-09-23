/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.venda;

import br.edu.ifpb.domain.produto.Produto;
import java.util.List;

public interface Vendas {

    Venda novaVenda(Venda venda);

    void adicionarProduto(Produto_venda produto_venda);

    Venda localizarVenda(int id);

    List<Venda> listarVendas();

    List<Produto> listarProdutosDeVenda(int id);

    void deletarVenda(int id);
}
