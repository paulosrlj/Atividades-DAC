/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web;

import java.sql.Connection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import br.edu.ifpb.infra.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.ifpb.web.jsf.ControladorDeIntegrante;

/**
 *
 * @author paulo
 */
@WebServlet(name = "ControladorDeIntegranteServlet", urlPatterns = {"/integrantes"})
public class ControladorDeIntegranteServlet extends HttpServlet {

    private DB db = new DB();

    // Listar todos os clientes
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        Connection conn;
//        try {
//            conn = db.getConnection();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ControladorDeIntegranteServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador de integrante</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> </h1>");
            listarClientes(out);
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorDeIntegranteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String cpf = request.getParameter("cpf");
//        String nome = request.getParameter("nome");
//        Cliente cliente = new Cliente(cpf,nome);
//        this.service.novo(cliente);
//        response.sendRedirect("clientes");

//       Integrante integrante = new Integrante();
    
    }

    private void listarClientes(final PrintWriter out) throws ClassNotFoundException {
        this.db
                .todos()
                .forEach(c
                        -> out.println("<p>" + c.getNome() + "</p>")
                );
    }
}
