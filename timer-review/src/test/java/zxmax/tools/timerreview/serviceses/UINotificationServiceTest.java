/**
 * This file is part of timer-review.
 *
 * timer-review is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * timer-review is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with timer-review.  If not, see <http://www.gnu.org/licenses/>.
 */
package zxmax.tools.timerreview.serviceses;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        assertTrue(result.contains("min: " + duration));
    }

}
