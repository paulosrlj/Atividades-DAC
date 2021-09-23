/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra;

import br.edu.ifpb.domain.compra.Carrinho;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author paulo
 */
@Stateful
public class CarrinhoOnline implements Carrinho {

    private final List<String> produtos = new LinkedList<>();

    @Override
    public void adicionar(String produto) {
        this.produtos.add(produto);
    }

    @Override
    public List<String> produtos() {
        return Collections.unmodifiableList(this.produtos);
    }

    // Ao executar esse metodo, ele tira o bean 'CarrinhoOnline' da memória
    @Remove
    @Override
    public void finalizar() {
        System.out.println("--- produtos ---");
        this.produtos.forEach(System.out::println);
    }
}
