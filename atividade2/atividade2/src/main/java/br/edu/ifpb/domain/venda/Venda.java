package br.edu.ifpb.domain.venda;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Venda {

    private int id;
    private int cliente_id;

    public Venda() {
    }

    public Venda(int cliente_id) {
        this(-1, cliente_id);
    }

    public Venda(int id, int cliente_id) {
        this.id = id;
        this.cliente_id = cliente_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

}
