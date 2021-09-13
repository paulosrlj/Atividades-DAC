/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.infra.IntegranteDB;
import br.edu.ifpb.domain.Integrante;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.util.List;

@Named("integrantebean")
@SessionScoped
public class ControladorDeIntegrante implements Serializable {

    private Integrante integrante = new Integrante();
    private List<Integrante> integrantes = new ArrayList<>();

    private LocalDate dataInicial;
    private LocalDate dataFinal;

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

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void limparIntegrante() {
        this.setIntegrante(new Integrante(integrante.getId()));
    }

    public void limparIntegrantes() {
        this.setIntegrantes(new ArrayList<>());
    }

    public void limparDatas() {
        this.setDataInicial(null);
        this.setDataFinal(null);
    }

    public String criarIntegrante() throws ClassNotFoundException {
        IntegranteDB.persistirIntegrante(integrante);
        limparIntegrante();
        return "listar?faces-redirect=true";
    }

    public String redirecionarEditarIntegrante(Integrante integrante) throws ClassNotFoundException {
        this.setIntegrante(integrante);
        return "editar?faces-redirect=true";
    }

    public String editarIntegrante() throws Exception {
        IntegranteDB.atualizarIntegrante(integrante);
        limparIntegrante();
        return "listar?faces-redirect=true";
    }

    public List<Integrante> listarIntegrantes() {
        this.setIntegrantes(IntegranteDB.buscarIntegrantes());
        return this.getIntegrantes();
    }

    public String excluirIntegrante(Integrante integrante) {
        IntegranteDB.excluirIntegrante(integrante.getId());
        limparIntegrante();
        return "listar?faces-redirect=true";
    }

    public void buscarPorCpf() {
        this.setIntegrante(IntegranteDB.buscarIntegrantePorCpf(integrante.getCpf()));
    }

    public void listarEntreDatas() {
        this.setIntegrantes(IntegranteDB.listarIntegrantesEntreDatas(dataInicial, dataFinal));
        this.limparDatas();
    }
}
