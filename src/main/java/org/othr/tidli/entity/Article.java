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

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author Brandl Valentin
 */
@MappedSuperclass
public abstract class Article extends Id {
    
    @Transient
    private static final long serialVersionUID = -1604497535637986945L;

    private String name;
    private String description;
    private byte[] image;

    public Article(final String name, final String description, final byte[] image) {
        super();
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Article() {}

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(final byte[] image) {
        this.image = image;
    }
    
}
