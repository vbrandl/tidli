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
        final Shop shop = getEm().merge(s);
        final Offer o = new Offer(art, amount, price);
        getEm().persist(o);
        shop.addOffer(o);
        return o;
    }

    @Transactional
    @Override
    public Optional<Integer> decrementOffer(final Offer offer, final int n, final Optional<Shop> shop) {
        //return shop
                //.map(s -> getEm().merge(s))
                //.filter(s -> s.hasOffer(offer))
                //.flatMap(a -> findEntity(offer.getId()))
                //.filter(of -> of.getAmount() >= n)
                //.map(of -> {of.decrementAmount(n); return of;})
                //.map()

        return shop
                .map(s -> getEm().merge(s)) // merge shop into persistence context
                .filter(s -> n > 0 && s.hasOffer(offer))  // check if n is positive and of offer is owned by shop
                .map(s -> getEm().merge(offer)) // merge offer into persistence context
                .filter(of -> of.getAmount() >= n) // check if offer is at least n times available
                .map(of -> {
                    of.decrementAmount(n);
                    return Optional.of(of);
                }) // decrement offer-> amount by n
                .flatMap(of -> mergeIfPresent(of).map(Offer::getAmount)); // merge offer and return the content

                //.flatMap(of -> {
                    //return Optional.of(1);
                //});
                //.flatMap(s -> {
                    //final Optional<Offer> o = findEntity(offer.getId());
                    //o.filter(of -> of.getAmount() >= n).ifPresent(of -> of.decrementAmount(n));
                    //return mergeIfPresent(o).map(Offer::getAmount);
                //});
    }

    @Override
    protected Class<Offer> getEntityClass() {
        return Offer.class;
    }

    @Transactional
    @Override
    public boolean deleteOffer(Offer off, Optional<Shop> shp) {
        final Optional<Shop> opt = shp.filter(s -> s.getOffers().contains(off));
        return deleteEntity(off, opt, Role.Shop);
    }
    
}
