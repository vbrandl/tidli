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
package org.othr.tidli.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.Shop;
import org.othr.tidli.service.LoginServiceIF;
import org.othr.tidli.service.ShopServiceIF;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class ShopDetailController extends AbstractController {
    private static final long serialVersionUID = -2292619186583344544L;
    private static boolean isNullOrEmpty(final String str) {
        return null == str || str.isEmpty();
    }

    @Inject
    private LoginServiceIF ls;
    @Inject 
    private ShopServiceIF ss;
    private Shop shop;
    private String pw1;
    private String pw2;
    private String pwOld;

    @PostConstruct
    private void prepareData() {
        this.shop = this.ls.getShop().get();
    }


    public void updateShop() {
        if (!isNullOrEmpty(this.pw1) || !isNullOrEmpty(this.pw2) || !isNullOrEmpty(this.pwOld)) {
            if (this.pw1.equals(this.pw2) && this.shop.checkPassword(this.pw1)) {
                this.ss.updateShop(this.shop, this.pwOld, this.pw1);
                this.ls.updateSession();
                this.sendInfo("Erfolg", "Erfolgreich gespeichert");
            } else {
                this.sendError("Passwort", "Passwort ist falsch oder stimmen nicht überein");
            }
        } else {
            this.ss.updateShop(this.shop, null, null);
            this.ls.updateSession();
            this.sendInfo("Erfolg", "Erfolgreich gespeichert");
        }
    }

    public Shop getUnwrapedShop() {
        return this.shop;
    }

    public String getPw1() {
        return this.pw1;
    }

    public void setPw1(String pw1) {
        this.pw1 = pw1;
    }

    public String getPw2() {
        return this.pw2;
    }

    public void setPw2(String pw2) {
        this.pw2 = pw2;
    }

    public String getPwOld() {
        return this.pwOld;
    }

    public void setPwOld(String pwOld) {
        this.pwOld = pwOld;
    }
    
}
