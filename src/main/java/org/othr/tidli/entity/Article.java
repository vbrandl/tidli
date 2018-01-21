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

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.othr.tidli.util.ImageMimeType;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Brandl Valentin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
})
public class Article extends Id implements RatableEntity {
    
    private static final long serialVersionUID = -1604497535637986945L;

    @NotNull
    private String name;
    private String description;
    @Lob
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
        return this.image != null
                ? this.image.clone()
                : new byte[0];
    }

    public StreamedContent getImageAsStream() {
        final Optional<ImageMimeType> imt = ImageMimeType.fromContent(this.image);
        return new DefaultStreamedContent(
                new ByteArrayInputStream(
                        null != this.image
                                ? this.image
                                : new byte[0]
                ),
                imt
                        .map(ImageMimeType::getMimeType)
                        .orElse("image/png") // silent fallback, maybe we are lucky
        );
    }

    public void setImage(final byte[] image) {
        this.image = null != image
                ? image.clone()
                : new byte[0];
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
