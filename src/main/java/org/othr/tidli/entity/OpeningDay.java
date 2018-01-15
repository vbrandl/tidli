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

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    //@Temporal(TemporalType.TIME)
    @Column(name = "from_date")
    private LocalTime from;
    //@Temporal(TemporalType.TIME)
    @Column(name = "to_date")
    private LocalTime to;

    public OpeningDay(final WeekDay wd, final LocalTime f, final LocalTime t) {
        super();
        this.weekDay = wd;
        this.from = f;
        this.to = t;
    }

    public OpeningDay() {}

    public WeekDay getWeekDay() {
        return this.weekDay;
    }

    public void setWeekDay(final WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getFrom() {
        return this.from;
    }

    public void setFrom(final LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return this.to;
    }

    public void setTo(final LocalTime to) {
        this.to = to;
    }

}
