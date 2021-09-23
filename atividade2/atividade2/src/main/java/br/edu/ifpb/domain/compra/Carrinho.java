/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.compra;

import java.util.List;

public interface Carrinho {

    public void adicionar(String produto);

    public List<String> produtos();

    public void finalizar();
}
