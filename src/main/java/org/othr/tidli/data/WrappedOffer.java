/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package org.othr.tidli.data;

import org.othr.tidli.entity.Offer;

/**
 *
 * @author Brandl Valentin
 */
public class WrappedOffer extends Offer {
    
    private static final long serialVersionUID = 4267942235432483634L;
    
    private int rating = 5;
    
    public WrappedOffer(final Offer off) {
        super.setId(off.getId());
        super.setAmount(off.getAmount());
        super.setArticle(off.getArticle());
        super.setDay(off.getDay());
        super.setPrice(off.getPrice());
        super.setRatings(off.getRatings());
    }
    
    public int getRating() {
        return this.rating;
    }
    
    public void setRating(final int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        }
    }

    public Offer unwrap() {
        final Offer o = new Offer(this.getArticle(), this.getAmount(), this.getPrice());
        o.setRatings(this.getRatings());
        o.setDay(this.getDay());
        o.setId(this.getId());
        return o;
    }
    
}