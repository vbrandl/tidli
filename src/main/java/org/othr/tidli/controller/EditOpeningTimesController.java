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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    private Integer wd;
    private Date from;
    private Date to;
    private List<WeekDay> availableWeekDays;

    @PostConstruct
    private void prepareData() {
        this.ot = this.getShop().map(s -> s.getOpeningTimes());
        this.availableWeekDays = Arrays
                .stream(WeekDay.values())
                .sorted((d1, d2) -> Integer.compare(d1.getInt(), d2.getInt()))
                .collect(Collectors.toList());
        this.getShop()
                .map( // map shop to stream of week days
                        shp -> shp
                                .getOpeningTimes().getDays()
                                .parallelStream()
                                .map(day -> day.getWeekDay())
                ).ifPresent( // remove each existing week day from available days
                        stream -> stream.filter(d -> null != d).forEach(day -> this.availableWeekDays.remove(day))
                );
    }

    public Collection<OpeningDay> getOpeningTimes() {
        return this.ot.map(OpeningTime::getDays).orElse(Collections.emptyList());
    }

    public void removeDay(final OpeningDay od) {
        if (this.ss.removeOpeningDay(this.getShop(), od)) {
            this.updateSession();
            this.prepareData();
            this.sendInfo("Erfolg", "Erfolgreich gelöscht");
        } else {
            this.sendError("Fehler", "Fehler beim Löschen");
        }
    }

    public void addDay() {
        //final LocalTime f = LocalDateTime.ofInstant(this.from.toInstant(), ZoneId.systemDefault()).toLocalTime();
        //final LocalTime t = LocalDateTime.ofInstant(this.to.toInstant(), ZoneId.systemDefault()).toLocalTime();
        final Optional<WeekDay> w = WeekDay.fromInteger(this.wd);
        if (w.map(_w -> this.ss
                .addOpeningDay(
                        this.getShop(),
                        new OpeningDay(_w, this.from, this.to)
                )).orElse(false)
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

    public Integer getWd() {
        return this.wd;
    }

    public void setWd(final Integer wd) {
        this.wd = wd;
    }

    public Date getFrom() {
        return this.from;
    }

    public void setFrom(final Date from) {
        this.from = from;
    }

    public Date getTo() {
        return this.to;
    }

    public void setTo(final Date to) {
        this.to = to;
    }

    public Collection<WeekDay> getAvailableWeekDays() {
        return Collections.unmodifiableList(this.availableWeekDays);
    }

}
