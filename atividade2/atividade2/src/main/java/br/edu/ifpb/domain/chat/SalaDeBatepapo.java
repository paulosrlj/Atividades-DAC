/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SalaDeBatepapo {

    private List<String> mensagens = new ArrayList<>();

    public void nova(String msg) {
        this.mensagens.add(msg);
    }

    public List<String> historico() {
        return Collections.unmodifiableList(this.mensagens);
    }
}
