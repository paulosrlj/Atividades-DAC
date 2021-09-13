/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.CPF;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author paulo
 */
@FacesConverter(value = "cpf-converter", forClass = CPF.class)
public class ConverterCpf implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        if(value == null) return null;
        
        CPF cpf = new CPF(value);
        return cpf;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        if(value == null) return null;
        CPF cpf = (CPF) value;
        return cpf.getNumero();
    }

}
