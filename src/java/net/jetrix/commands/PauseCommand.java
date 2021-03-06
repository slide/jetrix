/**
 * Jetrix TetriNET Server
 * Copyright (C) 2001-2004  Emmanuel Bourg
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

package net.jetrix.commands;

import static net.jetrix.GameState.*;

import net.jetrix.*;
import net.jetrix.messages.channel.ChannelMessage;
import net.jetrix.messages.channel.PauseMessage;
import net.jetrix.messages.channel.ResumeMessage;
import net.jetrix.messages.channel.CommandMessage;

/**
 * Pause the game.
 *
 * @author Emmanuel Bourg
 */
public class PauseCommand extends AbstractCommand implements Command
{
    public PauseCommand()
    {
        setAccessLevel(AccessLevel.OPERATOR);
    }

    public String getAlias()
    {
        return "pause";
    }

    public void execute(CommandMessage m)
    {
        Client client = (Client) m.getSource();
        Channel channel = client.getChannel();

        if (channel != null)
        {
            ChannelMessage pause = null;
            if (channel.getGameState() == PAUSED)
            {
                pause = new ResumeMessage();
            }
            else
            {
                pause = new PauseMessage();
            }

            pause.setSlot(channel.getClientSlot(client));
            pause.setSource(client);
            channel.send(pause);
        }
    }

}
