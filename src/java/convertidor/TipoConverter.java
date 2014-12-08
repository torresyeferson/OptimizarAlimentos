/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package convertidor;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Yeferson
 */
@FacesConverter(value="tp_conv")
public class TipoConverter implements Converter{

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List a= new ArrayList();
        a.add(value);
        return a;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "putoo";
    }
    
    
}
