package br.edu.ifpb.domain.produto;

import java.util.List;

public interface Produtos {

    Produto novo(Produto produto);

    Produto localizar(int id);

    Produto localizarPorDescricao(String descricao);

    List<Produto> todos();

    void atualizar(Produto produto, int id);

    void deletar(int id);
}
