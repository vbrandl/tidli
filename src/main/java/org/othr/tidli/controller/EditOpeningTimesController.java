/*
 * Copyright (C) 2018 Brandl Valentin
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
package org.othr.tidli.controller;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.OpeningDay;
import org.othr.tidli.entity.OpeningTime;
import org.othr.tidli.service.ShopServiceIF;
import org.othr.tidli.util.WeekDay;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class EditOpeningTimesController extends AbstractController {
    
    private static final long serialVersionUID = 1428414846330889709L;

    @Inject
    private ShopServiceIF ss;
    private Optional<OpeningTime> ot;

    private WeekDay wd;
    private LocalTime from;
    private LocalTime to;
    private Set<WeekDay> availableWeekDays;

    @PostConstruct
    private void prepareData() {
        this.ot = this.getShop().map(s -> s.getOpeningTimes());
        this.availableWeekDays = new HashSet<>(Arrays.asList(WeekDay.values()));
        this.getShop()
                .map( // map shop to stream of week days
                        shp -> shp
                                .getOpeningTimes().getDays()
                                .parallelStream()
                                .map(day -> day.getWeekDay())
                ).ifPresent( // remove each existing week day from available days
                        stream -> stream.forEach(day -> this.availableWeekDays.remove(day))
                );
    }

    public Collection<OpeningDay> getOpeningTimes() {
        return this.ot.map(OpeningTime::getDays).orElse(Collections.emptyList());
    }

    public void addDay() {
        if (this.ss
                .addOpeningDay(
                        this.getShop(),
                        new OpeningDay(this.wd, this.from, this.to)
                )
                ) {
            //this.availableWeekDays.remove(this.wd);
            this.updateSession();
            this.prepareData();
            this.sendInfo("Erfolgreich angelegt", "Öffnungszeit wurde erfolgreich angelegt!");
        } else {
            this.sendError("Fehler", "Fehler beim anlegen der Öffnungszeit!");
        }
    }

    public boolean save() {
        return this.ot
                .map(o -> this.ss.editOpeningTimes(this.getShop(), o))
                .orElse(false);
    }

    public WeekDay getWd() {
        return this.wd;
    }

    public void setWd(final WeekDay wd) {
        this.wd = wd;
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

    public Set<WeekDay> getAvailableWeekDays() {
        return Collections.unmodifiableSet(this.availableWeekDays);
    }

}
