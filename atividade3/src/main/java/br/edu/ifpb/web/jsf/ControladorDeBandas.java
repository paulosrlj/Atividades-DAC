/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.infra.BandaDB;
import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.infra.IntegranteDB;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.util.List;

@Named("bandabean")
@SessionScoped
public class ControladorDeBandas implements Serializable {

    private Banda banda = new Banda();
    private Integrante integrante = new Integrante();
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

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public void limparBanda() {
        this.setBanda(new Banda(banda.getId()));
    }

    public void cleanBandasPorLocaldeorigem() {
        this.setBandasPorlocaldeorigem(new ArrayList<>());
    }

    public String criarBanda() {
        BandaDB.persistirBanda(banda);
        limparBanda();
        return "listar?faces-redirect=true";
    }

    public String redirecionarEditarBanda(Banda banda) {
        this.setBanda(banda);
        return "editar?faces-redirect=true";
    }

    public String redirecionarListarIntegrantes(Banda banda) {
        this.setBanda(banda);
        return "listar_integrantes?faces-redirect=true";
    }

    public String editarBanda() throws Exception {
        banda.getIntegrantes().add(integrante);
        BandaDB.atualizarBanda(banda);
        limparBanda();
        setIntegrante(new Integrante());

        return "listar?faces-redirect=true";
    }

    public List<Banda> listarBandas() {
        this.setBandas(BandaDB.buscarBandas());
        return this.bandas;
    }

    public String excluirBanda(Banda banda) {
        BandaDB.excluirBanda(banda.getId());
        limparBanda();
        return "listar?faces-redirect=true";
    }

    public void buscarPorLocaldeorigem() {
        this.setBandasPorlocaldeorigem(BandaDB.buscarBandaPorLocaldeorigem(banda.getLocalDeOrigem()));
    }

    public String desassociarIntegrante(Integrante integrante) {
        banda.getIntegrantes().remove(integrante);
        return "listar_integrantes?faces-redirect=true";
    }
}
