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
package org.othr.tidli.entity;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Brandl Valentin
 */
public class Shop extends Account {
    
    @Transient
    private static final long serialVersionUID = 1837882861365109107L;

    private Address address;
    private OpeningTime openingTimes;
    @ManyToOne
    private Collection<Offer> offers;
    private String description;
    private boolean activated = false;

    public Shop(final String email, final String password, final String name,
            final Address address, final OpeningTime openingTimes, final String description) {
        super(email, password, name);
        this.address = address;
        this.openingTimes = openingTimes;
        this.description = description;
    }

    public Shop() {}

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public OpeningTime getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(final OpeningTime openingTimes) {
        this.openingTimes = openingTimes;
    }

    public Collection<Offer> getOffers() {
        return Collections.unmodifiableCollection(offers);
    }

    public void setOffers(final Collection<Offer> offers) {
        this.offers = offers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(final boolean activated) {
        this.activated = activated;
    }
    
}
