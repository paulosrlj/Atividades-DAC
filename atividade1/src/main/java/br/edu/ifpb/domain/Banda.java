/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import java.util.List;

/**
 *
 * @author paulo
 */
public class Banda {

    private int id;
    private String localDeOrigem;
    private String nomeFantasia;
    private List<Integrante> integrantes;
    
    public Banda() {
        
    }
    
    public Banda(int id, String localDeOrigem, String nomeFantasia) {
        this.id = id;
        this.localDeOrigem = localDeOrigem;
        this.nomeFantasia = nomeFantasia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    
    public void addIntegrate(Integrante integrante) {
        this.integrantes.add(integrante);
    }
    
    public void removeIntegrante(Integrante integrante) {
        this.integrantes.remove(integrante);
    }
}
