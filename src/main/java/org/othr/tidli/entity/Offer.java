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

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Brandl Valentin
 */
@Entity
public class Offer extends Article {
    
    @Transient
    private static final long serialVersionUID = -3282709858909762202L;

    @ManyToOne
    private Shop owner;
    private int amount;
    private int price;
    @Temporal(TemporalType.DATE)
    private Date day = new Date();

    public Offer(final String name, final String description, final byte[] image,
            final Shop owner, final int amount, final int price) {
        super(name, description, image);
        this.owner = owner;
        this.amount = amount;
        this.price = price;
    }

    public Offer() {}

    public Shop getOwner() {
        return owner;
    }

    public void setOwner(final Shop owner) {
        this.owner = owner;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public Date getDay() {
        return (Date)day.clone();
    }

    public void setDay(final Date day) {
        this.day = (Date)day.clone();
    }
    
}
