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

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.data.WrappedOffer;
import org.othr.tidli.entity.Offer;
import org.othr.tidli.service.OfferServiceIF;
import org.othr.tidli.service.RatingServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class ListOffersController extends AbstractController {

    private static final long serialVersionUID = -8904855712220676294L;

    @Inject
    private OfferServiceIF os;
    @Inject
    private RatingServiceIF rs;
    private Set<WrappedOffer> offers;

    @PostConstruct
    private void prepareData() {
        this.offers = this.getUser().map(_u -> this.os.listTodaysOffers()).orElse(Collections.emptySet());
    }

    public Set<WrappedOffer> getOffers() {
        return Collections.unmodifiableSet(this.offers);
    }

    public void decrementOffer(final Offer off, final int n) {   
        this.os.decrementOffer(off, n, this.getShop()).ifPresent(offer -> {
            final WrappedOffer wo = new WrappedOffer(offer);
            if (this.offers.remove(wo)) {
                this.offers.add(wo);
            }
        });
    }

    public void rateOffer(final WrappedOffer off) {
        final int rating = off.getRating();
        final Offer unwrap = off.unwrap();
        if (this.rs.rateOffer(unwrap, rating, this.getUser())) {
            this.sendInfo("Erfolg", "Angebot erfolgreich bewertet");
        } else {
            this.sendError("Fehler", "Angebot bereits bewertet");
        }
    }

    public void deleteOffer(final Offer off) {
        this.os.deleteOffer(off, this.getShop());
    }

    public boolean isActive(final Offer off) {
        return off.getDay().equals(LocalDate.now());
    }

    public String formatPrice(final Offer off) {
        if (null != off) {
            return NumberFormat.getCurrencyInstance(Locale.GERMANY).format((double)off.getPrice() / 100);
        } else {
            return "";
        }
    }

    public boolean isOwnerOfOffer(final Offer off) {
        return null != off 
                ? this.getShop() // is shop?
                        .filter(s -> s.getOffers().contains(off)) // that owns the offer?
                        .isPresent() // or not?
                : false; // or no offer?
    }

    public boolean isAlreadyRated(final WrappedOffer off) {
        return this.rs.isRatedByUser(off, this.getUser());
    }

    
}
