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
 * Interface for channel filters.
 * 
 * @author Emmanuel Bourg
 * @version $Revision$, $Date$
 */
public interface MessageFilter
{
    public void process(Message m, List out);

    public String getDisplayName();

    public String getShortDescription();

    public String getVersion();

    public String getAuthor();
    
    public String getProperty(String key);
    
    public void setProperty(String key, String value);

}