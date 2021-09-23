/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.venda;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DeletarVenda {

    @EJB
    private Vendas vendas;

    public void deletar(int id) {
        this.vendas.deletarVenda(id);
    }
}
