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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.service.OfferServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class EditOfferController extends AbstractController {

    @Inject
    private OfferServiceIF os;
    private Collection<Article> articles;
    private Article art;
    private int amount;
    private BigDecimal price;

    @PostConstruct
    private void prepareData() {
        getShop().ifPresent(shp -> articles = shp.getArticles());
    }

    public Collection<Article> getArticles() {
        return Collections.unmodifiableCollection(articles);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Article getArticle() {
        return art;
    }

    public void setArticle(final Article art) {
        this.art = art;
    }

    public void createOffer() {
        getShop().ifPresent(shp ->
                os.createOffer(
                        art,
                        amount,
                        price.multiply(new BigDecimal(100)).intValue(),
                        shp
                )
        );
    }
    
}
