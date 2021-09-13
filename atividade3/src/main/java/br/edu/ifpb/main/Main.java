package br.edu.ifpb.main;

import br.edu.ifpb.domain.*;
import br.edu.ifpb.infra.GerenteDB;
import br.edu.ifpb.infra.IntegranteDB;
import java.time.LocalDate;
import java.time.Month;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        IntegranteDB.entityManager = Persistence
//                .createEntityManagerFactory("jpa-web")
//                .createEntityManager();

//        Integrante integrante = new Integrante("Paulo", LocalDate.of(2001, Month.MAY, 30), new CPF("23247821048"));
//        IntegranteDB.persistirIntegrante(integrante);
//        
//        integrante = new Integrante("Julia", LocalDate.of(2020, Month.NOVEMBER, 9), new CPF("28491948072"));
//        IntegranteDB.persistirIntegrante(integrante);
//
//        List<Integrante> listadeintegrantes = IntegranteDB.buscarIntegrantes();
//
//        for (Integrante i : listadeintegrantes) {
//            System.out.println(i.getNome());
//        }
//        
//        System.out.println("Atualizando ------------------------");
//        Integrante integrante3 = IntegranteDB.buscarIntegrantePorId(1);
//        integrante3.setNome("Paulo Sérgio");
//        integrante3.setDataNascimento(LocalDate.of(2001, Month.MAY, 10));
//        IntegranteDB.atualizarIntegrante(integrante3);
//        
//        listadeintegrantes = IntegranteDB.buscarIntegrantes();
//
//        for (Integrante i : listadeintegrantes) {
//            System.out.println(i.getNome());
//        }
//        
//        System.out.println("Excluindo ------------------------");
//        IntegranteDB.excluirIntegrante(1);
//        listadeintegrantes = IntegranteDB.buscarIntegrantes();
//
//        for (Integrante i : listadeintegrantes) {
//            System.out.println(i.getNome());
//        }
//        
//        System.out.println("Buscar por cpf ------------------------");
//        Integrante integranteBuscado = IntegranteDB.buscarIntegrantePorCpf(new CPF("28491948072"));
//        
//        System.out.println(integranteBuscado.getNome());

//        Gerente gerente = new Gerente("Paulo", "paulo@gmail.com", new CPF("28491948072"));
//        GerenteDB.persistirGerente(gerente);
//        
//        List<Gerente> listadeintegrantes = GerenteDB.buscargerentes();
//
//        for (Gerente i : listadeintegrantes) {
//            System.out.println(i.getEmail());
//        }
    }
}
