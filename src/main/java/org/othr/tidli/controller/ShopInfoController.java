/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.othr.tidli.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.othr.tidli.entity.OpeningDay;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.service.ShopServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class ShopInfoController extends AbstractController {
    
    private static final long serialVersionUID = 1966693174091130616L;

    private Shop shop;
    @Inject
    private ShopServiceIF ss;

    @PostConstruct
    private void prepareData() {
        final String strId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        try {
            final long id = Long.parseLong(strId);
            final Optional<Shop> s = this.ss.findEntity(id);
            if (s.isPresent()) {
                this.shop = s.get();
            } else {
                this.sendError("Error", "Kann Shop nicht finden");
            }
        } catch (final NumberFormatException nfe) {
            this.sendError("Error", "Ungültige id");
        }
    }

    public Shop getInfoShop() {
        return this.shop;
    }

    public Collection<OpeningDay> getOpeningDays() {
        return null != this.shop.getOpeningTimes()
                ? this.shop
                        .getOpeningTimes()
                        .getDays()
                        .parallelStream()
                        .sorted((o1, o2) -> Integer.compare(o1.getWeekDay().ordinal(), o2.getWeekDay().ordinal()))
                        .collect(Collectors.toList())
                : Collections.emptyList();
    }
    
}
