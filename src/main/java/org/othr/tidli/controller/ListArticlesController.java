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
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.data.WrappedArticle;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.service.ArticleServiceIF;
import org.othr.tidli.service.RatingServiceIF;
import org.othr.tidli.util.Role;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class ListArticlesController extends AbstractController {

    private static final long serialVersionUID = 1877130317374256378L;

    @Inject
    private ArticleServiceIF as;
    @Inject
    private RatingServiceIF rs;
    private Collection<WrappedArticle> articles;

    @PostConstruct
    private void prepareData() {
        //this.articles = this.isShopRole()
        //? this.getShop().map(shp -> shp.getArticles()).orElse(Collections.emptyList())
        this.articles = this.as.findAll().parallelStream().map(WrappedArticle::new).collect(Collectors.toList());
    }

    public void filterOwn() {
        if (this.isShopRole()) {
            this.articles = this.getShop()
                    .map(shp -> shp.getArticles())
                    .map(a -> a.parallelStream().map(WrappedArticle::new).collect(Collectors.toList()))
                    .orElse(Collections.emptyList());
        }
    }

    public Collection<WrappedArticle> getArticles() {
        return Collections.unmodifiableCollection(this.articles);
    }

    public void deleteArticle(final Article art) {
        this.as.deleteArticle(art);
        this.updateSession();
    }

    public boolean userIsOwnerOf(final Article art) {
        return this.getUser()
                .filter(u -> u.getRole() == Role.Shop)
                .map(s -> ((Shop)s).getArticles().contains(art))
                .orElse(false);
    }

    public String shopForArt(final Article art) {
        return this.as.getShopForArticle(art).map(Shop::getName).orElse("");
    }

    public StreamedContent getImageForArt(final Article art) {
        if (null != art) {
            return new DefaultStreamedContent(new ByteArrayInputStream(art.getImage()));
        } else {
            return new DefaultStreamedContent(new ByteArrayInputStream(new byte[0]));
        }
    }

    public void rateArticle(final WrappedArticle art) {
        final int rating = art.getRating();
        final Article unwrap = art.unwrap();
        if (this.rs.rateArticle(unwrap, rating, this.getUser())) {
            this.sendInfo("Erfolg", "Erfolgreich bewertet");
        } else {
            this.sendError("Fehler", "Artikel bereits bewertet");
        }
    }

    public boolean isAlreadyRated(final WrappedArticle art) {
        return this.rs.isRatedByUser(art, this.getUser());
    }

}
