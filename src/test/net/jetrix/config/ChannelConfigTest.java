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

package  net.jetrix.config;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit TestCase for the class net.jetrix.config.ChannelConfig
 *
 * @author Emmanuel Bourg
 */
public class ChannelConfigTest
{
    private ChannelConfig config;

    @Before
    public void setUp()
    {
        config = new ChannelConfig();
    }

    @Test
    public void testIsPasswordProtected()
    {
        assertEquals("is the channel protected ?", false, config.isPasswordProtected());

        config.setPassword("abracadabra");
        assertEquals("is the channel protected ?", true, config.isPasswordProtected());
    }

}
