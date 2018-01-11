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
package org.othr.tidli.service;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.RatableEntity;
import org.othr.tidli.entity.Rating;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
@RequestScoped
public class RatingService extends AbstractService<Rating> implements RatingServiceIF {

    private static final long serialVersionUID = -3258964989482032568L;

    @Transactional
    @Override
    public void rateOffer(final Offer offer, final int score, final Optional<Account> acc) {
        this.rate(offer, score, acc);
    }

    @Transactional
    @Override
    public void rateShop(final Shop shop, final int score, final Optional<Account> acc) {
        this.rate(shop, score, acc);
    }

    @Transactional
    @Override
    public void rateArticle(final Article art, final int score, final Optional<Account> acc) {
        this.rate(art, score, acc);
    }

    @Transactional
    private<T extends RatableEntity> void rate(final T ratable, final int score, final Optional<Account> acc) {
        acc.filter(a -> !ratable.isRatedByUser(a)).map(a -> new Rating(score, a)).ifPresent(r -> {
            final T merged = this.getEm().merge(ratable);
            this.getEm().persist(r);
            merged.addRating(r);
        });
    }

    @Override
    protected Class<Rating> getEntityClass() {
        return Rating.class;
    }
    
}
