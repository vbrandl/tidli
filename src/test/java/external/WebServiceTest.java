/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external;

import com.othr.sw.gsupportticketsystem.services.Ticket;
import com.othr.sw.gsupportticketsystem.services.TicketHeader;
import com.othr.sw.gsupportticketsystem.services.TicketReply;
import com.othr.sw.gsupportticketsystem.services.UserAccessPointService;
import com.othr.sw.gsupportticketsystem.services.UserAccessPointServiceService;
import java.util.List;
import javax.xml.ws.soap.SOAPFaultException;
import org.junit.Test;

/**
 *
 * @author Brandl Valentin
 */
public class WebServiceTest {

    @Test(expected = SOAPFaultException.class)
    public void dafuq2() {
        final TicketHeader th = new TicketHeader();
        th.setTitle("foo");
        th.setTicketPriority(6);
        th.setTicketTypeID(1); // hardcoded constants are bad but integer typed apis are too...
        th.setTicketSubjID(5);
        final TicketReply tr = new TicketReply();
        tr.setReplyText("foobarbaz");
        final UserAccessPointService uaps = new UserAccessPointServiceService().getUserAccessPointServicePort();
        // marshalling error from webservice
        // provider won't fix
        // (tickets seem to get created but the response does not work
        final Ticket t = uaps.createTicket(11, th, tr);
        System.out.println(t != null);
    }

    @Test(expected = SOAPFaultException.class)
    public void dafuq() {
        final UserAccessPointService uaps = new UserAccessPointServiceService().getUserAccessPointServicePort();
        // marshalling error from webservice
        // provider won't fix
        // (tickets seem to get created but the response does not work
        final List<Ticket> tickets = uaps.getTickets(11);
        System.out.println(tickets.size());
    }
    
}
