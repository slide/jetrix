/**
 * Jetrix TetriNET Server
 * Copyright (C) 2001-2002  Emmanuel Bourg
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

package org.lfjr.jts.commands;

import java.util.*;
import org.lfjr.jts.*;

/**
 * Send a message to all clients on the server.
 *
 * @author Emmanuel Bourg
 * @version $Revision$, $Date$
 */
public class BroadcastCommand implements Command
{
    private int accessLevel = 1;

    public String[] getAliases()
    {
        return (new String[] { "broadcast", "br", "gmsg" });
    }

    public int getAccessLevel()
    {
        return accessLevel;
    }

    public String getUsage()
    {
        return "/br <message>";
    }

    public String getDescription()
    {
        return "Send a message to all clients on the server.";
    }

    public void execute(Message m)
    {
        TetriNETClient client = (TetriNETClient)m.getSource();
        
        if (m.getParameterCount() > 2)
        {
            // preparing message
            Message response = new Message(Message.MSG_PLINE);
            
            String messageBody = m.getRawMessage().substring(m.getStringParameter(1).length() + 9);
            String message = ChatColors.bold + ChatColors.red + "[Broadcast from " + ChatColors.purple 
                             + client.getPlayer().getName() + ChatColors.red + "] " 
                             + ChatColors.darkBlue + messageBody;
            response.setParameters(new Object[] { new Integer(0), message });
            
            Iterator clients = ClientRepository.getInstance().getClients();
            while (clients.hasNext())
            {
                TetriNETClient target = (TetriNETClient)clients.next();
                target.sendMessage(response);
            }
        }
        else
        {
            // not enough parameters
            Message response = new Message(Message.MSG_PLINE);
            String message = ChatColors.red + m.getStringParameter(1) + ChatColors.blue + " <message>";
            response.setParameters(new Object[] { new Integer(0), message });
            client.sendMessage(response);
        }
    }

}