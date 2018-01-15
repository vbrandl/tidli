/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.othr.tidli.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Id;
import org.othr.tidli.service.ArticleServiceIF;

/**
 *
 * @author Brandl Valentin
 */
//@FacesConverter(forClass = Article.class, value = "articleConverter")
@Named("articleConverter")
public class ArticleConverter implements Converter {

    @Inject
    private ArticleServiceIF as;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            try {
                final long id = Long.valueOf(value);
                return this.as.findEntity(id).orElse(null);
            } catch (final NumberFormatException _nfe) {
                return null;
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (null != value && value instanceof Article) {
            return String.valueOf(((Id) value).getId());
        } else {
            return "";
        }
    }
    
}
