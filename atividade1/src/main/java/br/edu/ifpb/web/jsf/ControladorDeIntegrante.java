/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Integrante;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import br.edu.ifpb.infra.DB;
import br.edu.ifpb.infra.DBException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author paulo
 */
@Named("integrantebean")
@SessionScoped
public class ControladorDeIntegrante implements Serializable {

    private DB db = new DB();
    private Integrante integrante = new Integrante();
    private List<Integrante> integrantes = new ArrayList<>();

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    public String criarIntegrante() throws ClassNotFoundException {
        db.novo(integrante);
        return "listar?faces-redirect=true";
    }

    public String redirecionarEditarIntegrante(Integrante integrante) throws ClassNotFoundException {
        this.setIntegrante(integrante);
        return "editar?faces-redirect=true";
    }

    public String editarIntegrante() throws ClassNotFoundException {
        db.editar(integrante);
        this.setIntegrante(new Integrante());
        
        return "listar?faces-redirect=true";
    }

    public List<Integrante> listarIntegrantes() throws ClassNotFoundException {
        this.setIntegrantes(db.todos());
        return this.getIntegrantes();
    }

    public String excluirIntegrante(Integrante integrante) throws ClassNotFoundException {
        db.excluir(integrante);
        return "listar";
    }
    
    public void buscarPorCpf() throws ClassNotFoundException {
        this.setIntegrante(db.buscarPorCpf(integrante.getCpf().getNumero()));
    }
}
