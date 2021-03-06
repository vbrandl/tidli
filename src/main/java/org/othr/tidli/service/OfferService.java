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

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.othr.tidli.data.WrappedOffer;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Article;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.entity.Shop;


@RequestScoped
public class OfferService extends AbstractService<Offer> implements OfferServiceIF {

    private static final long serialVersionUID = 7858277297413201593L;

    @Transactional
    @Override
    public Offer createOffer(final Article art, final int price, final int amount, final Shop s) {
        final Shop shop = this.getEm().merge(s);
        final Article artMerged;
        if (this.getEm().find(Article.class, art.getId()) == null) {
            this.getEm().persist(art);
            artMerged = art;
        } else {
            artMerged = this.getEm().merge(art);
        }
        final Offer o = new Offer(artMerged, amount, price);
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
        if (opt.isPresent()) {
            final Offer merged = this.getEm().merge(off);
            merged.getRatings().parallelStream().map(this.getEm()::merge).forEach(this.getEm()::remove);
            final Shop mShop = this.getEm().merge(opt.get());
            mShop.removeOffer(merged);
            this.getEm().remove(merged);
            return true;
        } else {
            return false;
        }
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
                .map(a -> this.findForCustomLocation(a.getCity(), a.getZipCode()))
                .orElse(Collections.emptyList());

    }

    @Override
    public Collection<Offer> findForCustomLocation(final String city, final Integer zip) {
        final TypedQuery<Offer> query = this.getEm().createNamedQuery("Offer.findForLocation", Offer.class);
        query.setParameter("city", city);
        query.setParameter("zipCode", zip);
        return query.getResultList();
    }

    @Override
    public Set<WrappedOffer> listTodaysOffers() {
        final TypedQuery<Offer> query = this.getEm().createNamedQuery("Offer.findAll", Offer.class);
        final Collection<Offer> offers = query.getResultList();
        return offers.parallelStream()
                .filter(o -> o.getDay().equals(LocalDate.now()))
                .map(WrappedOffer::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Shop> findOwner(final Offer off) {
        return this.getEm().createNamedQuery("Shop.findAll", Shop.class)
                .getResultList()
                .parallelStream()
                .filter(s -> s.getOffers().contains(off))
                .findAny();
    }
    
}
