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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.Article;
import org.othr.tidli.service.ArticleServiceIF;
import org.othr.tidli.service.OfferServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class EditOfferController extends AbstractController {

    private static final long serialVersionUID = -1331128825835684493L;

    @Inject
    private OfferServiceIF os;
    @Inject
    private ArticleServiceIF as;
    private Collection<Article> articles;
    private Long art;
    private int amount;
    private BigDecimal price;

    @PostConstruct
    private void prepareData() {
        this.getShop().ifPresent(shp -> this.articles = shp.getArticles());
    }

    public Collection<Article> getArticles() {
        return Collections.unmodifiableCollection(this.articles);
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public boolean isArticleSelected() {
        return null != this.art;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Long getArticle() {
        return this.art;
    }

    public void setArticle(final Long art) {
        this.art = art;
    }

    public void createOffer() {
        this.getShop().ifPresent(s -> {
            final Optional<Article> arti = this.as.findEntity(this.art);
            arti.ifPresent(a ->
                    this.os.createOffer(
                            a,
                            this.amount,
                            this.price.multiply(new BigDecimal(100)).intValue(),
                            s
                    )
            );
        });
    }
    
}
