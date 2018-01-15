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
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.RatableEntity;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
public interface RatingServiceIF {

    /**
     * Creates a rating for the given offer
     * @param offer The offer to be rated
     * @param rating The rating {@code 1 - 5}
     * @param acc
     * @return 
     */
    boolean rateOffer(final Offer offer, final int rating, final Optional<Account> acc);
    /**
     * Creates a rating for the given shop
     * @param shop The shop to be rated
     * @param rating The rating {@code 1 - 5}
     * @param acc
     * @return 
     */
    boolean rateShop(final Shop shop, final int rating, final Optional<Account> acc);
    /**
     * Creates a rating for the given article
     * @param article The shop to be rated
     * @param rating The rating {@code 1 - 5}
     * @param acc
     * @return 
     */
    boolean rateArticle(final Article article, final int rating, final Optional<Account> acc);

    <T extends RatableEntity> boolean isRatedByUser(final T entity, final Optional<Account> acc);
    
}
