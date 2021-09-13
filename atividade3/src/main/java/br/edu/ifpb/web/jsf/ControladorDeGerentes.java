
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.infra.GerenteDB;
import br.edu.ifpb.domain.Gerente;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.util.List;

/**
 *
 * @author paulo
 */
@Named("gerentebean")
@SessionScoped
public class ControladorDeGerentes implements Serializable {

    private Gerente gerente = new Gerente();
    private List<Gerente> gerentes = new ArrayList<>();

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public List<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Gerente> gerentes) {
        this.gerentes = gerentes;
    }

    public void limparGerente() {
        this.setGerente(new Gerente(gerente.getId()));
    }

    public String criarGerente() {
        GerenteDB.persistirGerente(gerente);
        limparGerente();
        return "listar?faces-redirect=true";
    }

    public String redirecionarEditarGerente(Gerente gerente) {
        this.setGerente(gerente);
        return "editar?faces-redirect=true";
    }

    public String editarGerente() throws Exception {
        GerenteDB.atualizarGerente(gerente);
        limparGerente();

        return "listar?faces-redirect=true";
    }

    public List<Gerente> listarGerentes() {
        this.setGerentes(GerenteDB.buscarGerentes());
        return this.getGerentes();
    }

    public String excluirGerente(Gerente gerente) {
        GerenteDB.excluirGerente(gerente.getId());
        limparGerente();
        return "listar?faces-redirect=true";
    }

    public void buscarPorCpf() throws ClassNotFoundException {
        this.setGerente(GerenteDB.buscarGerentePorCpf(gerente.getCpf()));        
    }
}
