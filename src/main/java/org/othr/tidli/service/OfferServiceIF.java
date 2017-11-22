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
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
public interface OfferServiceIF {

    /**
     * Creates a new offer for the given article.
     * @param art Article to create an offer for
     * @param price Price for the offer
     * @param amount Available amount
     * @param s
     * @return The created offer
     */
    Offer createOffer(final Article art, final int price, final int amount, final Shop s);

    /**
     * Decrements the available amount of an {@link Offer} by {@code n } and
     * returns the new amount or none
     * @param offer The offer to decrement
     * @param n The amount by which to decrement
     * @param s
     * @return The new amount or empty
     */
    Optional<Integer> decrementOffer(final Offer offer, final int n, final Optional<Shop> s);

    /**
     * Deletes an offer if it is owned by the given shop.
     * @param off
     * @param shp
     * @return 
     */
    boolean deleteOffer(final Offer off, final Optional<Shop> shp);
    
}
