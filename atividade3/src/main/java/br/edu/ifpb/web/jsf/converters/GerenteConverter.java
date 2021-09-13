/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf.converters;

import br.edu.ifpb.domain.Gerente;
import br.edu.ifpb.infra.GerenteDB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author paulo
 */
@FacesConverter(value = "gerente-converter", forClass = Gerente.class)
public class GerenteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        if (value == null) {
            return null;
        }

        int id = Integer.parseInt(value);
        Gerente gerente = GerenteDB.buscarGerentePorId(id);
        return gerente;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        if (value == null) {
            return null;
        }

//        String.valueOf(((Equipe) object).getEquipeId());
        return String.valueOf(((Gerente) value).getId());
    }

}
