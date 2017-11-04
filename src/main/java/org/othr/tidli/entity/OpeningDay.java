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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.othr.tidli.util.WeekDay;

/**
 *
 * @author Brandl Valentin
 */
@Entity
public class OpeningDay extends Id {
    
    @Transient
    private static final long serialVersionUID = -3578698357739936426L;

    private WeekDay weekDay;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "from_date")
    private Date from;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "to_date")
    private Date to;

    public OpeningDay(final WeekDay wd, final Date f, final Date t) {
        super();
        this.weekDay = wd;
        this.from = f;
        this.to = t;
    }

    public OpeningDay() {}

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(final WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public Date getFrom() {
        return (Date)from.clone();
    }

    public void setFrom(final Date from) {
        this.from = (Date)from.clone();
    }

    public Date getTo() {
        return (Date)to.clone();
    }

    public void setTo(final Date to) {
        this.to = (Date)to.clone();
    }
    
}
