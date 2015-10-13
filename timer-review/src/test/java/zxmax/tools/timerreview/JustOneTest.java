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
package zxmax.tools.timerreview;

public class JustOneTest {
    public static void main(String[] args) {
        new JustOneTest().test();
    }

    void test() {
        JustOneLock ua = new JustOneLock("JustOneId");

        if (ua.isAppActive()) {
            System.out.println("Already active.");
            System.exit(1);
        } else {
            System.out.println("NOT already active.");
            try {
                while (true) {
                    try {
                        System.out.print(".");
                        Thread.sleep(5 * 60);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}