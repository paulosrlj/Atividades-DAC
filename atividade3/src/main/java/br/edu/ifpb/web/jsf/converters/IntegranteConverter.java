/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf.converters;

import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.infra.IntegranteDB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "integrante-converter", forClass = Integrante.class)
public class IntegranteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        if (value == null) {
            return null;
        }

        int id = Integer.parseInt(value);
        Integrante integrante = IntegranteDB.buscarIntegrantePorId(id);
        return integrante;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        if (value == null) {
            return null;
        }

        return String.valueOf(((Integrante) value).getId());
    }

}
