/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.jsf.converters;

import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.infra.DB;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        DB db = new DB();

        int id = Integer.parseInt(value);
        Integrante integrante = new Integrante();

        try {
            integrante = db.buscarPorId(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntegranteConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
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
