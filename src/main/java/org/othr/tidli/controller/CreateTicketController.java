/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.othr.tidli.controller;

import com.othr.sw.gsupportticketsystem.services.Ticket;
import com.othr.sw.gsupportticketsystem.services.TicketHeader;
import com.othr.sw.gsupportticketsystem.services.TicketReply;
import com.othr.sw.gsupportticketsystem.services.UserAccessPointService;
import com.othr.sw.gsupportticketsystem.services.UserAccessPointServiceService;
import java.util.Optional;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Brandl Valentin
 */
@ManagedBean
public class CreateTicketController extends AbstractController {
    
    private static final long serialVersionUID = -2561639377421339021L;

    private final UserAccessPointService uaps = new UserAccessPointServiceService().getUserAccessPointServicePort();

    private String subject;
    private String content;

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void createTicket() {
        final TicketHeader th = new TicketHeader();
        th.setTitle(this.subject);
        th.setTicketPriority(6);
        th.setTicketTypeID(1); // hardcoded constants are bad but integer typed apis are too...
        th.setTicketSubjID(5);
        final TicketReply tr = new TicketReply();
        tr.setReplyText(this.content);
        // marshalling error from webservice
        // provider won't fix
        // (tickets seem to get created but the response does not work
        final Optional<Ticket> optTicket = this.getUser()
                .map(u -> this.uaps.createTicket((int) u.getId(), th, tr));
        if (optTicket.isPresent()) {
            this.sendInfo("Erfolg", "Ticket erfolgreich angelegt");
        } else {
            this.sendError("Fehler", "Fehler beim Anlegen des Tickets");
        }
    }
    
}
