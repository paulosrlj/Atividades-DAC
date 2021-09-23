/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.venda;

public class Produto_venda {

    private int id;
    private int venda_id;
    private int produto_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public Produto_venda(int venda_id, int produto_id) {
        this.venda_id = venda_id;
        this.produto_id = produto_id;
    }

    public Produto_venda(int id, int venda_id, int produto_id) {
        this.id = id;
        this.venda_id = venda_id;
        this.produto_id = produto_id;
    }
}
