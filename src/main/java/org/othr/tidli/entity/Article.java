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
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Brandl Valentin
 */
@Entity
public class Article extends Id implements RatableEntity {
    
    private static final long serialVersionUID = -1604497535637986945L;

    private String name;
    private String description;
    private byte[] image;
    @OneToMany(targetEntity = Rating.class)
    private Collection<Rating> ratings;

    public Article(final String name, final String description, final byte[] image) {
        super();
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Article() {}

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return this.image.clone();
    }

    public void setImage(final byte[] image) {
        this.image = image.clone();
    }

    @Override
    public Collection<Rating> getRatings() {
        return Collections.unmodifiableCollection(this.ratings);
    }

    @Override
    public void setRatings(final Collection<Rating> ratings) {
        this.ratings = new HashSet<>(ratings);
    }

    @Override
    public boolean addRating(Rating r) {
        return this.ratings.add(r);
    }
    
}
