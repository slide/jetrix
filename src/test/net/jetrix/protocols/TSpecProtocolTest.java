/**
 * Jetrix TetriNET Server
 * Copyright (C) 2001-2003  Emmanuel Bourg
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.jetrix.protocols;

import java.util.Locale;

import net.jetrix.Client;
import net.jetrix.Language;
import net.jetrix.Message;
import net.jetrix.Protocol;
import net.jetrix.User;
import net.jetrix.clients.TetrinetClient;
import net.jetrix.messages.channel.SmsgMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit TestCase for the class net.jetrix.protocols.TSpecProtocolTest
 *
 * @author Emmanuel Bourg
 */
public class TSpecProtocolTest
{
    private Protocol protocol;
    private Locale locale;

    @Before
    public void setUp()
    {
        protocol = new TspecProtocol();
        locale = new Locale("fr");
    }

    @Test
    public void testTranslateSmsg()
    {
        User user = new User();
        user.setName("Smanux");

        Client client = new TetrinetClient(user);

        SmsgMessage message = new SmsgMessage(1, "spectator message");
        message.setSource(client);
        String raw = "pline 0 " + Language.getText("channel.spectator.message", locale, "Smanux", "spectator message");
        assertEquals(protocol.applyStyle(raw), protocol.translate(message, locale));
    }

    @Test
    public void testTranslatePrivateSmsg()
    {
        User user = new User();
        user.setName("Smanux");

        Client client = new TetrinetClient(user);

        SmsgMessage message = new SmsgMessage(1, "spectator message");
        message.setSource(client);
        message.setPrivate(true);
        assertEquals("smsg Smanux spectator message", protocol.translate(message, locale));
    }

    @Test
    public void testGetMessageSmsg()
    {
        String raw = "pline 1 // spectator message";
        Message message = protocol.getMessage(raw);

        assertNotNull("message not parsed", message);
        assertEquals("message class", SmsgMessage.class, message.getClass());

        SmsgMessage smsg = (SmsgMessage) message;
        assertEquals("slot", 1, smsg.getSlot());
        assertEquals("private", false, smsg.isPrivate());
        assertEquals("text", "spectator message", smsg.getText());
    }

    @Test
    public void testGetMessagePrivateSmsg()
    {
        String raw = "pline 1 spectator message";
        Message message = protocol.getMessage(raw);

        assertNotNull("message not parsed", message);
        assertEquals("message class", SmsgMessage.class, message.getClass());

        SmsgMessage smsg = (SmsgMessage) message;
        assertEquals("slot", 1, smsg.getSlot());
        assertEquals("private", true, smsg.isPrivate());
        assertEquals("text", "spectator message", smsg.getText());
    }

}
