/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.othr.tidli.data;

import org.othr.tidli.entity.Article;

/**
 *
 * @author Brandl Valentin
 */
public class WrappedArticle extends Article {

    private static final long serialVersionUID = -182243162663858640L;

    private int rating = 5;
    
    public WrappedArticle(final Article art) {
        super.setId(art.getId());
        super.setDescription(art.getDescription());
        super.setImage(art.getImage());
        super.setName(art.getName());
        super.setRatings(art.getRatings());
    }
    
    public int getRating() {
        return this.rating;
    }
    
    public void setRating(final int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        }
    }

    public Article unwrap() {
        final Article a = new Article(this.getName(), this.getDescription(), this.getImage());
        a.setRatings(this.getRatings());
        a.setId(this.getId());
        return a;
    }

    @Override
    public int hashCode() {
        return this.unwrap().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Article) || this.getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article)obj;
        return this.unwrap().equals(other);
    }
    
}
