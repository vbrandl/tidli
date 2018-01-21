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
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.data.WrappedArticle;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.OpeningDay;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.service.ArticleServiceIF;
import org.othr.tidli.service.RatingServiceIF;
import org.othr.tidli.service.ShopServiceIF;

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
    @Inject
    private ShopServiceIF ss;
    private Collection<WrappedArticle> articles;
    private Shop detailShop;

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

    public boolean userIsOwnerOf(final WrappedArticle art) {
        return this.isAdminRole()
                || this.ss.isOwnerOf(art.unwrap(), this.getShop());
    }

    public Shop shopForArt(final WrappedArticle art) {
        return this.shopForArt(art.unwrap());
    }

    public Shop shopForArt(final Article art) {
        return this.as.getShopForArticle(art).orElse(null);
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

    public Shop getDetailShop() {
        return this.detailShop;
    }

    public void setDetailShop(Shop detailShop) {
        this.detailShop = detailShop;
    }
    
    public void setDetailShopForArt(WrappedArticle art) {
        this.setDetailShop(this.shopForArt(art));
    }

    public List<OpeningDay> getDetailOpeningDays() {
        return null != this.detailShop
                && null != this.detailShop.getOpeningTimes()
                && null != this.detailShop.getOpeningTimes().getDays()
                ? this.detailShop
                        .getOpeningTimes()
                        .getDays()
                        .parallelStream()
                        .sorted((o1, o2) ->
                                Integer.compare(o1.getWeekDay().ordinal(), o2.getWeekDay().ordinal())
                        ).collect(Collectors.toList())
                : Collections.emptyList();
    }

}
