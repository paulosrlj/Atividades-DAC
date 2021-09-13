/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Gerente;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author paulo
 */
public class GerenteDB {

    public static EntityManager entityManager = Persistence
            .createEntityManagerFactory("jpa-web")
            .createEntityManager();

    public static void persistirGerente(Gerente gerente) {
        EntityTransaction transaction = entityManager.getTransaction();
        gerente.setId(gerente.getId() + 1);

        transaction.begin();
        entityManager.persist(gerente);
        transaction.commit();

    }

    public static Gerente buscarGerentePorId(int id) {
        Gerente retorno = entityManager.find(Gerente.class, id);
        return retorno;
    }
    
    public static Gerente buscarGerentePorCpf(CPF cpf) {
        String sql = "SELECT g FROM Gerente g WHERE g.cpf = :cpf";
        TypedQuery<Gerente> query = entityManager.createQuery(sql, Gerente.class);
        query.setParameter("cpf", cpf);
        Gerente gerente = query.getSingleResult();

        return gerente;
    }
    

    public static List<Gerente> buscarGerentes() {
        String sql = "SELECT g FROM Gerente g";
        TypedQuery<Gerente> query = entityManager.createQuery(sql, Gerente.class);
        List<Gerente> gerentes = query.getResultList();
        return gerentes;
    }

    public static void atualizarGerente(Gerente gerente) throws Exception {
        Gerente gerenteEncontrado = buscarGerentePorId(gerente.getId());

        entityManager.getTransaction().begin();
        gerenteEncontrado.setNome(gerente.getNome());
        gerenteEncontrado.setCpf(gerente.getCpf());
        gerenteEncontrado.setEmail(gerente.getEmail());
        gerenteEncontrado.setBanda(gerente.getBanda());
        entityManager.getTransaction().commit();
    }

    public static void excluirGerente(int id) {
        Gerente gerenteEncontrado = buscarGerentePorId(id);

        entityManager.getTransaction().begin();
        entityManager.remove(gerenteEncontrado);
        entityManager.getTransaction().commit();

    }
}
