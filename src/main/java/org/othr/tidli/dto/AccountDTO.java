/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.othr.tidli.dto;

import java.io.Serializable;
import org.othr.tidli.entity.Account;
import org.othr.tidli.entity.Administrator;
import org.othr.tidli.entity.Shop;

/**
 *
 * @author Brandl Valentin
 */
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = -2893689439025256847L;

    private String email;
    private String name;
    private UserTypeDTO type;

    public AccountDTO() {}

    public AccountDTO(final String email, final String name, final UserTypeDTO type) {
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public AccountDTO(final Administrator adm) {
        this(adm, UserTypeDTO.Supporter);
    }

    public AccountDTO(final Shop shp) {
        this(shp, UserTypeDTO.User);
    }

    public AccountDTO(final Account acc) {
        this(acc, UserTypeDTO.User);
    }

    private AccountDTO(final Account acc, final UserTypeDTO type) {
        this.email = acc.getEmail();
        this.name = acc.getName();
        this.type = type;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
