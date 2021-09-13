/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author paulo
 */
public class IntegranteDB {

    public static EntityManager entityManager = Persistence
            .createEntityManagerFactory("jpa-web")
            .createEntityManager();

    public static void persistirIntegrante(Integrante integrante) {
        EntityTransaction transaction = entityManager.getTransaction();
        integrante.setId(integrante.getId() + 1);

        transaction.begin();
        entityManager.persist(integrante);
        transaction.commit();

    }

    public static Integrante buscarIntegrantePorId(int id) {
        Integrante retorno = entityManager.find(Integrante.class, id);
        return retorno;
    }

    public static Integrante buscarIntegrantePorCpf(CPF cpf) {
        String sql = "SELECT i FROM Integrante i WHERE i.cpf = :cpf";
        TypedQuery<Integrante> query = entityManager.createQuery(sql, Integrante.class);
        query.setParameter("cpf", cpf);
        Integrante integrante = query.getSingleResult();

        return integrante;
    }

    public static List<Integrante> buscarIntegrantes() {
        String sql = "SELECT i FROM Integrante i";
        TypedQuery<Integrante> query = entityManager.createQuery(sql, Integrante.class);
        List<Integrante> integrantes = query.getResultList();
        return integrantes;
    }

    public static List<Integrante> listarIntegrantesEntreDatas(LocalDate dataInicial, LocalDate dataFinal) {
        String sql = "SELECT i FROM Integrante i WHERE "
                + "i.dataDeNascimento BETWEEN :data1 AND :data2";
        TypedQuery<Integrante> query = entityManager.createQuery(sql, Integrante.class);
        query.setParameter("data1", dataInicial);
        query.setParameter("data2", dataFinal);
        List<Integrante> integrantes = query.getResultList();
        return integrantes;
    }

    public static void atualizarIntegrante(Integrante integrante) throws Exception {
        Integrante integranteEncontrado = buscarIntegrantePorId(integrante.getId());

        entityManager.getTransaction().begin();
        integranteEncontrado.setNome(integrante.getNome());
        integranteEncontrado.setDataNascimento(integrante.getDataNascimento());
        integranteEncontrado.setCpf(integrante.getCpf());
        entityManager.getTransaction().commit();
    }

    public static void excluirIntegrante(int id) {
        Integrante integranteEncontrado = buscarIntegrantePorId(id);

        entityManager.getTransaction().begin();
        entityManager.remove(integranteEncontrado);
        entityManager.getTransaction().commit();

    }
}
