/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Gerente;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.TypedQuery;


public class BandaDB {

    public static EntityManager entityManager = Persistence
            .createEntityManagerFactory("jpa-web")
            .createEntityManager();

    public static void persistirBanda(Banda banda) {
        EntityTransaction transaction = entityManager.getTransaction();
        banda.setId(banda.getId() + 1);

        transaction.begin();
        entityManager.persist(banda);
        transaction.commit();

    }

    public static Banda buscarBandaPorId(int id) {
        Banda retorno = entityManager.find(Banda.class, id);
        return retorno;
    }

    public static List<Banda> buscarBandaPorLocaldeorigem(String localdeorigem) {
        String sql = "SELECT b FROM Banda b WHERE b.localDeOrigem = :localdeorigem";
        TypedQuery<Banda> query = entityManager.createQuery(sql, Banda.class);
        query.setParameter("localdeorigem", localdeorigem);
        List<Banda> banda = query.getResultList();

        return banda;
    }

    public static List<Banda> buscarBandas() {
        String sql = "SELECT b FROM Banda b";
        TypedQuery<Banda> query = entityManager.createQuery(sql, Banda.class);
        List<Banda> bandas = query.getResultList();
        return bandas;
    }

    public static void atualizarBanda(Banda banda) throws Exception {
        Banda bandaEncontrado = buscarBandaPorId(banda.getId());
        Gerente gerenteEncontrado;

        entityManager.getTransaction().begin();
        bandaEncontrado.setNomeFantasia(banda.getNomeFantasia());
        bandaEncontrado.setLocalDeOrigem(banda.getLocalDeOrigem());

        if (banda.getGerente() != null) {
            gerenteEncontrado = GerenteDB.buscarGerentePorId(banda.getGerente().getId());
            bandaEncontrado.setGerente(banda.getGerente());
            gerenteEncontrado.setBanda(banda);
            entityManager.merge(gerenteEncontrado);
        }

        bandaEncontrado.setIntegrantes(banda.getIntegrantes());

        entityManager.merge(bandaEncontrado);

        entityManager.getTransaction().commit();
    }

    public static void excluirBanda(int id) {
        Banda bandaEncontrado = buscarBandaPorId(id);

        entityManager.getTransaction().begin();
        entityManager.remove(bandaEncontrado);
        entityManager.getTransaction().commit();

    }
}
