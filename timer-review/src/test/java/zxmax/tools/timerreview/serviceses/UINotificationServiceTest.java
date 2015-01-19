/**
 * Timer Review  -  a personal time management tool
 *
 *
 * Copyright (C)  2012 - 2014 Parentini Massimiliano
 * Project home page: http://www.timer-review.net
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package zxmax.tools.timerreview.serviceses;


import org.junit.Test;
import static org.junit.Assert.*;
import zxmax.tools.timerreview.domain.Tomato;


public class UINotificationServiceTest {
    
    /**
     * Test of getToolTipMessage method, of class UINotificationService.
     */
    @Test
    public void testGetToolTipMessage() {
        System.out.println("getToolTipMessage");
        Tomato tomato = new Tomato("");
        final int duration = 20;
        tomato.setDuration(duration);
        UINotificationService instance = new UINotificationService(tomato);
        String result = instance.getToolTipMessage();
        assertTrue( result.contains("min: " + duration));        
    }

}
