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

package org.lfjr.jts.filter;

import java.util.*;
import org.lfjr.jts.*;

/**
 * A dummy filter forward incomming messages with no change.
 * 
 * @author Emmanuel Bourg
 * @version $Revision$, $Date$
 */
public class NoopFilter extends AbstractFilter
{
    public void process(Message m, List out)
    {
        out.add(m);	
    }

    public String getDisplayName() { return "Noop Filter"; }

    public String getShortDescription() { return "I'm useless !"; }

    public String getVersion() { return "1.0"; }

    public String getAuthor() { return "Emmanuel Bourg"; }

}