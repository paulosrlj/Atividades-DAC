package br.edu.ifpb.domain.cliente;

import java.util.List;

public interface Clientes {

    Cliente novo(Cliente cliente);
    Cliente localizar(int id);
    List<Cliente> todos();
    void atualizar(Cliente cliente, int id);
    void deletar(int id);
}
