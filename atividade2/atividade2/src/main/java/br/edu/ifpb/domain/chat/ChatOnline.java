/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.chat;

import java.util.List;
import java.util.ArrayList;
import javax.ejb.Singleton;

// Estado compartilhado entre todos os clientes
@Singleton
public class ChatOnline implements Chat {

    private List<String> mensagens = new ArrayList<>();

    @Override
    public void nova(String msg) {
        this.mensagens.add(msg);
    }

    @Override
    public List<String> historico() {
        return this.mensagens;
    }

}
