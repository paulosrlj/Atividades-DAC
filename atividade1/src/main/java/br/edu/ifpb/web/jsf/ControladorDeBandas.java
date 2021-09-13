/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Banda;
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
@Named("bandabean")
@SessionScoped
public class ControladorDeBandas implements Serializable {

    private DB db = new DB();
    private Banda banda = new Banda();
    private List<Banda> bandas = new ArrayList<>();
    private List<Banda> bandasPorlocaldeorigem = new ArrayList<>();

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public List<Banda> getBandasPorlocaldeorigem() {
        return bandasPorlocaldeorigem;
    }

    public void setBandasPorlocaldeorigem(List<Banda> bandasPorlocaldeorigem) {
        this.bandasPorlocaldeorigem = bandasPorlocaldeorigem;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }
    
    public void cleanBandasPorLocaldeorigem() {
        this.setBandasPorlocaldeorigem(new ArrayList<>());
    }

    public String criarBanda() throws ClassNotFoundException {
        db.novaBanda(banda);
        return "listar?faces-redirect=true";
    }

    public String redirecionarEditarBanda(Banda banda) throws ClassNotFoundException {
        this.setBanda(banda);
        return "editar?faces-redirect=true";
    }

    public String editarBanda() throws ClassNotFoundException {
        db.editarBanda(banda);
        this.setBanda(new Banda());
        
        return "listar?faces-redirect=true";
    }

    public String listarBandas() throws ClassNotFoundException {
        this.setBandas(db.listarTodasBandas());
        return null;
    }

    public String excluirBanda(Banda banda) throws ClassNotFoundException {
        db.excluirBanda(banda);
        return "listar";
    }
    
    public void buscarPorLocaldeorigem() throws ClassNotFoundException {
        this.setBandasPorlocaldeorigem(db.buscarPorLocaldeorigem(banda.getLocalDeOrigem()));
    }
}
