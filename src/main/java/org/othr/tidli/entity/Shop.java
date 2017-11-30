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
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.othr.tidli.util.Role;

/**
 *
 * @author Brandl Valentin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Shop.findAll", query = "SELECT s FROM Shop s")
})
public class Shop extends Account implements RatableEntity {

    private static final long serialVersionUID = 1837882861365109107L;
    private static final Role ROLE = Role.Shop;

    @OneToOne
//    @NotNull
    private OpeningTime openingTimes;
    @OneToMany
    private Collection<Offer> offers;
    @OneToMany
    private Collection<Article> articles;
    private String description;
    @OneToMany(targetEntity = Rating.class)
    private Collection<Rating> ratings;
    @OneToOne
    private Contact contact;

    public Shop(final String email, final String name, final String password,
            final Address address, final String description) {
        super(email, password, name, address);
        this.description = description;
        this.setActivated(false);
    }

    public Shop(final String name, final OpeningTime openingTimes,
            final String description) {
        this.openingTimes = openingTimes;
        this.description = description;
        this.setActivated(false);
    }

    public Shop() {
        this.setActivated(false);
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

    @Override
    public Role getRole() {
        return ROLE;
    }

    @Override
    public Collection<Rating> getRatings() {
        return Collections.unmodifiableCollection(ratings);
    }

    @Override
    public void setRatings(Collection<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean addRating(Rating r) {
        return this.ratings.add(r);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Collection<Article> getArticles() {
        return Collections.unmodifiableCollection(articles);
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

    public boolean addOffer(final Offer o) {
        return this.offers.add(o);
    }

    public boolean hasOffer(final Offer o) {
        return this.offers.contains(o);
    }

    public boolean addArticle(final Article art) {
        return this.articles.add(art);
    }

    public boolean hasArticle(final Article art) {
        return this.articles.contains(art);
    }

}
