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
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.othr.tidli.entity.Article;
import org.othr.tidli.service.ArticleServiceIF;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
@SessionScoped
public class CreateArticleController extends AbstractController {

    private static final long serialVersionUID = -7706811759715568074L;

    private String name, description;
//    private UploadedFile image;
    private byte[] content = null;
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

//    public UploadedFile getImage() {
//        return image;
//    }

//    public void setImage(final UploadedFile image) {
//        this.image = image;
//    }

    public void fileUpload(final FileUploadEvent fue) {
        this.content = fue.getFile().getContents();
    }

    public void save() {
        this.getShop().ifPresent(shp -> {
            this.as.createArticle(
                    new Article(this.name, this.description, this.content),
                    shp
            );
        });
    }
    
}
