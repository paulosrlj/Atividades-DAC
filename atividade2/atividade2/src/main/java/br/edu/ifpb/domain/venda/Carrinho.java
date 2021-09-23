/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.venda;

import br.edu.ifpb.domain.produto.Produto;
import java.util.List;

public interface Carrinho {

    public void adicionar(Produto produto);

    public void remover(Produto produto);

    public List<Produto> produtos();

    public void finalizar();
}
