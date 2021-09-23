package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Integrante;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import br.edu.ifpb.infra.DB;

import java.util.List;

@Named("bandabean")
@SessionScoped
public class ControladorDeBandas implements Serializable {

    private DB db = new DB();
    private Banda banda = new Banda();
    private List<Banda> bandas = new ArrayList<>();
    private List<Banda> bandasPorlocaldeorigem = new ArrayList<>();
    private Integrante integrante = new Integrante();
    private List<Integrante> integrantesBanda = new ArrayList<>();

    public List<Integrante> getIntegrantesBanda() {
        return integrantesBanda;
    }

    public void setIntegrantesBanda(List<Integrante> integrantesBanda) {
        this.integrantesBanda = integrantesBanda;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
    
    public void limparBanda() {
        this.setBanda(new Banda());
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
        System.out.println("----AQUIIIII----");
        System.out.println(integrante.getNome());
        this.banda.addIntegrate(integrante);
        db.editarBanda(banda, integrante);
        this.setBanda(new Banda());
        this.setIntegrante(new Integrante());
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

    public String redirecionarListarIntegrantes(Banda banda) throws ClassNotFoundException {
        this.setBanda(banda);
        this.setIntegrantesBanda(db.buscarIntegrantesBanda(banda.getId()));
        return "listar_integrantes.xhtml?faces-redirect=true";
    }
}
