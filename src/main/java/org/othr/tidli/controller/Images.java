/*
* Copyright (C) 2017 Brandl Valentin
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.othr.tidli.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import org.othr.tidli.service.ArticleServiceIF;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
@ApplicationScoped
public class Images {
    
    @Inject
    private ArticleServiceIF af;
    
    // thanks stackoverflow
    // https://stackoverflow.com/questions/10073905/display-database-blob-images-in-pgraphicimage-inside-uirepeat
    public StreamedContent getImage() throws IOException {
        final FacesContext context = FacesContext.getCurrentInstance();
        
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            final Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("id"));
            final byte[] content = af.getImageForId(id);
            return new DefaultStreamedContent(new ByteArrayInputStream(content));
        }
    }
    
}
