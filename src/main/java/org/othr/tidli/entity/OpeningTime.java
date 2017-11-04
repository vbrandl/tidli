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

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Brandl Valentin
 */
@Entity
public class OpeningTime extends Id implements Serializable {
    
    @Transient
    private static final long serialVersionUID = 5269673570636175593L;

    @OneToMany
    private Collection<OpeningDay> days;

    public OpeningTime(final Collection<OpeningDay> d) {
        super();
        this.days = d;
    }

    public OpeningTime() {}

    public Collection<OpeningDay> getDays() {
        return Collections.unmodifiableCollection(days);
    }

    public void setDays(final Collection<OpeningDay> days) {
        this.days = days;
    }
    
}
