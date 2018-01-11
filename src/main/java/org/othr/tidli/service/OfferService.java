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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.util.Role;


@RequestScoped
public class OfferService extends AbstractService<Offer> implements OfferServiceIF {

    private static final long serialVersionUID = 7858277297413201593L;

    @Transactional
    @Override
    public Offer createOffer(final Article art, final int price, final int amount, final Shop s) {
        final Shop shop = this.getEm().merge(s);
        final Offer o = new Offer(art, amount, price);
        this.getEm().persist(o);
        shop.addOffer(o);
        return o;
    }

    @Transactional
    @Override
    public Optional<Offer> decrementOffer(final Offer offer, final int n, final Optional<Shop> shop) {
        return shop
                .map(s -> this.getEm().merge(s)) // merge shop into persistence context
                .filter(s -> n > 0 && s.hasOffer(offer))  // check if n is positive and of offer is owned by shop
                .map(_unused -> this.getEm().merge(offer)) // merge offer into persistence context
                .filter(of -> of.getAmount() >= n) // check if offer is at least n times available
                .map(of -> {
                    of.decrementAmount(n);
                    return Optional.of(of);
                }) // decrement offer-> amount by n
                .flatMap(of -> this.mergeIfPresent(of)); // merge offer and return the content
    }

    @Override
    protected Class<Offer> getEntityClass() {
        return Offer.class;
    }

    @Transactional
    @Override
    public boolean deleteOffer(final Offer off, final Optional<Shop> shp) {
        final Optional<Shop> opt = shp.filter(s -> s.getOffers().contains(off));
        return this.deleteEntity(off, opt, Role.Shop);
    }

    @Override
    public Collection<Offer> findForArticle(final Article art) {
        final TypedQuery<Offer> query = this.getEm().createNamedQuery("Offer.findForArticle", Offer.class);
        query.setParameter("article", art);
        return query.getResultList();
    }

    @Override
    public Collection<Offer> findForQuery(final String query) {
        final TypedQuery<Offer> typedQuery = this.getEm().createNamedQuery("Offer.findForQuery", Offer.class);
        typedQuery.setParameter("query", query);
        return typedQuery.getResultList();
    }

    @Override
    public Collection<Offer> findForLocation(final Optional<? extends Account> acc) {
        return acc
                .filter(a -> a.getAddress() != null)
                .map(a -> {
                    final TypedQuery<Offer> query = this.getEm().createNamedQuery("Offer.findForLocation", Offer.class);
                    query.setParameter("city", a.getAddress().getCity());
                    query.setParameter("zipCode", a.getAddress().getZipCode());
                    return query.getResultList();
                }).orElse(Collections.emptyList());

    }
    
}
