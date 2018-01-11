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
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Brandl Valentin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Offer.findForArticle", query = "SELECT o FROM Offer o WHERE o.article = :article"),
    @NamedQuery(name = "Offer.findForQuery", query = "SELECT o FROM Offer o WHERE LOWER(o.article.description) LIKE LOWER('%:query%') OR LOWER(o.article.name) LIKE LOWER('%:query%')"),
    @NamedQuery(name = "Offer.findForLocation", query = "SELECT o FROM Offer o, Shop s WHERE o IN s.offers AND LOWER(s.address.city) = LOWER(:city) OR s.address.zipCode = :zipCode")
})
public class Offer extends Id implements RatableEntity {
    
    @Transient
    private static final long serialVersionUID = -3282709858909762202L;

    @ManyToOne
    private Article article;
    //@ManyToOne
    //private Shop owner;
    private int amount;
    private int price;
    @Temporal(TemporalType.DATE)
    @Column(name = "offer_date")
    private Date day = new Date();
    @OneToMany(targetEntity = Rating.class)
    private Collection<Rating> ratings;

    public Offer(final Article art, final int amount, final int price) {
        this.article = art;
        this.amount = amount;
        this.price = price;
    }

    public Offer() {}

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public void decrementAmount(final int n) {
        this.amount -= n;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public Date getDay() {
        return (Date)this.day.clone();
    }

    public void setDay(final Date day) {
        this.day = (Date)day.clone();
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public Collection<Rating> getRatings() {
        return Collections.unmodifiableCollection(this.ratings);
    }

    @Override
    public void setRatings(Collection<Rating> ratings) {
        this.ratings = new HashSet<>(ratings);
    }

    @Override
    public boolean addRating(Rating r) {
        return this.ratings.add(r);
    }
    
}
