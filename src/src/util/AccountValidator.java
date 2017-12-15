package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.logging.Logger;

@FacesValidator(value = "accountValidator")
public class AccountValidator implements Serializable, Validator{
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Logger.getLogger(getClass().getName() ).info("Validor validate method is called");
        if( uiComponent.getId().equals("sUsername") ) {
            Logger.getLogger(getClass().getName()).info("Validator: sUsername");
            String nameToCheck = (String) o;
            if( nameToCheck.equalsIgnoreCase("Hasan") ) {
                Logger.getLogger(getClass().getName()).info("Validator: Hasan");

                facesContext.addMessage(null, new FacesMessage("Passwords are not equal."));
                facesContext.validationFailed();
                ((UIInput) uiComponent).setValid(false);

                //throw new ValidatorException(new FacesMessage("Errrorrrr!"));
            }
        }
    }
}
