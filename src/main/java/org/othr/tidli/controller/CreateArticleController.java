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

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Article;
import org.othr.tidli.service.ArticleServiceIF;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class CreateArticleController extends AbstractController {

    private static final long serialVersionUID = -7706811759715568074L;

    private String name;
    private String description;
    private UploadedFile uf;
    @Inject
    private ArticleServiceIF as;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public UploadedFile getFile() {
        return this.uf;
    }

    public void setFile(final UploadedFile uf) {
        this.uf = uf;
    }

    @Transactional
    public void save() {
        if (this.getShop().map(shp -> {
            final byte[] content = null != this.uf
                    ? this.uf.getContents()
                    : null;
            return this.as.createArticle(
                    new Article(this.name, this.description, content),
                    shp
            );
        }).isPresent()) {
            this.updateSession();
            this.sendInfo("Artikel erstellen", "Artikel erfolgreich angelegt");
        } else {
            this.sendError("Artikel erstellen", "Es ist ein Fehler auftetreten");
        }
    }
    
}
