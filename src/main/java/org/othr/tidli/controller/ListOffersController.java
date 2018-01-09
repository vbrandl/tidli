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

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.service.OfferServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class ListOffersController extends AbstractController {

    @Inject
    private OfferServiceIF os;
    private Set<Offer> offers;

    @PostConstruct
    private void prepareData() {
        getShop().ifPresent(shp -> offers = new HashSet<>(shp.getOffers()));
    }

    public Set<Offer> getOffers() {
        return Collections.unmodifiableSet(offers);
    }

    public void decrementOffer(final Offer off, final int n) {   
        os.decrementOffer(off, n, getShop()).ifPresent(offer -> {
            if (offers.remove(offer)) {
                offers.add(offer);
            }
        });
    }

    public void deleteOffer(final Offer off) {
        os.deleteOffer(off, getShop());
    }

    public boolean isActive(final Offer off) {
        final Calendar today = Calendar.getInstance();
        final Calendar o = Calendar.getInstance();
        o.setTime(off.getDay());
        
        return today.get(Calendar.YEAR) == o.get(Calendar.YEAR)
                && today.get(Calendar.DAY_OF_YEAR) == o.get(Calendar.DAY_OF_YEAR);
    }
    
}
