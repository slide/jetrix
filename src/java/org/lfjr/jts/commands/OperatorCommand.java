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

import java.util.logging.*;

import org.lfjr.jts.*;
import org.lfjr.jts.config.*;

/**
 * Grant operator status to the player.
 *
 * @author Emmanuel Bourg
 * @version $Revision$, $Date$
 */
public class OperatorCommand implements Command
{
    private int accessLevel = 0;
    private Logger logger = Logger.getLogger("net.jetrix");

    public String[] getAliases()
    {
        return (new String[] { "op", "operator" });
    }

    public int getAccessLevel()
    {
        return accessLevel;
    }

    public String getUsage()
    {
        return "/op <password>";
    }

    public String getDescription()
    {
        return "Gain authenticated operator status.";
    }

    public void execute(Message m)
    {
        String cmd = m.getStringParameter(1);
        TetriNETClient client = (TetriNETClient)m.getSource();
        ServerConfig conf = TetriNETServer.getInstance().getConfig();

        if (m.getParameterCount() >= 3)
        {
            String password = m.getStringParameter(2);

            if (password.equalsIgnoreCase(conf.getOpPassword()))
            {
                // access granted
                client.getPlayer().setAccessLevel(1);
                Message response = new Message(Message.MSG_PLINE);
                String message = ChatColors.red + "Operator level granted.";
                response.setParameters(new Object[] { new Integer(0), message });
                client.sendMessage(response);
            }
            else
            {
                // access denied, logging attempt
                logger.severe(client.getPlayer().getName() + "(" + client.getSocket().getInetAddress() + ") attempted to get operator status.");
                Message response = new Message(Message.MSG_PLINE);
                String message = ChatColors.red + "Invalid Password! (Attempt logged)";
                response.setParameters(new Object[] { new Integer(0), message });
                client.sendMessage(response);
            }
        }
        else
        {
            // not enough parameters
            Message response = new Message(Message.MSG_PLINE);
            String message = ChatColors.red + cmd + ChatColors.blue + " <password>";
            response.setParameters(new Object[] { new Integer(0), message });
            client.sendMessage(response);
        }
    }
}