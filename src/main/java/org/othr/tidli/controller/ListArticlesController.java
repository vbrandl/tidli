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

import java.util.Collection;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.othr.tidli.entity.Article;
import org.othr.tidli.service.ArticleServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
//@Named
@ViewScoped
public class ListArticlesController extends AbstractController {

    @Inject
    private ArticleServiceIF as;
    private Collection<Article> articles;

    @PostConstruct
    private void prepareData() {
        articles = getShop().map(shp -> shp.getArticles()).orElse(Collections.emptyList());
    }

    public Collection<Article> getArticles() {
        return Collections.unmodifiableCollection(articles);
    }

    public void deleteArticle(final Article art) {
        as.deleteArticle(art);
    }
    
}
