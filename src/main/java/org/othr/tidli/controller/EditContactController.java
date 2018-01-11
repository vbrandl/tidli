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

import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.othr.tidli.entity.Contact;
import org.othr.tidli.service.ShopService;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class EditContactController extends AbstractController {
    
    private static final long serialVersionUID = -4671846203418468031L;

    @Inject
    private ShopService ss;
    private String tel;
    private String mail;

    @PostConstruct
    private void prepareData() {
        this.getShop()
                .flatMap(s -> Optional.ofNullable(s.getContact()))
                .ifPresent(c -> {
                    this.tel = c.getTelNo();
                    this.mail = c.getEmail();
                });
    }

    public boolean save() {
        return this.ss.editContact(this.getShop(), new Contact(this.tel, this.mail));
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(final String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }
    
}
