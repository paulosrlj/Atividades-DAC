/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra;

/**
 *
 * @author paulo
 */
public class DBException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DBException(String msg) {
        super(msg);
    }
}
