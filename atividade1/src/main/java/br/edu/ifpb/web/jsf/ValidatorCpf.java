/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.CPF;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author paulo
 */
@FacesValidator(value="cpf-validator")
public class ValidatorCpf implements Validator {

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
        CPF cpf = (CPF) value;
        if(cpf.valido()) return;
        
        throw new ValidatorException(new FacesMessage("CPF inválido"));
    }
    
}
