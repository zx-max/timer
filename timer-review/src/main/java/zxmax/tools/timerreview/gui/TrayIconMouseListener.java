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

package zxmax.tools.timerreview.gui;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPopupMenu;

public class TrayIconMouseListener implements MouseListener {
	private JPopupMenu popup = null;

	public TrayIconMouseListener(JPopupMenu popup) {
		this.popup = popup;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
			Rectangle bounds = getSafeScreenBounds(e.getPoint());
			Point point = e.getPoint();
			int x = point.x;
			int y = point.y;
			if (y < bounds.y) {
				y = bounds.y;
			} else if (y > bounds.y + bounds.height) {
				y = bounds.y + bounds.height;
			}
			if (x < bounds.x) {
				x = bounds.x;
			} else if (x > bounds.x + bounds.width) {
				x = bounds.x + bounds.width;
			}
			if (x + popup.getPreferredSize().width > bounds.x + bounds.width) {
				x = (bounds.x + bounds.width) - popup.getPreferredSize().width;
			}
			if (y + popup.getPreferredSize().height > bounds.y + bounds.height) {
				y = (bounds.y + bounds.height)
						- popup.getPreferredSize().height;
			}
			popup.setLocation(x, y);
			popup.setVisible(true);
		}
	}

	private Rectangle getSafeScreenBounds(Point pos) {
		Rectangle bounds = getScreenBoundsAt(pos);
		Insets insets = getScreenInsetsAt(pos);

		bounds.x += insets.left;
		bounds.y += insets.top;
		bounds.width -= (insets.left + insets.right);
		bounds.height -= (insets.top + insets.bottom);

		return bounds;
	}

	private Rectangle getScreenBoundsAt(Point pos) {
		GraphicsDevice gd = getGraphicsDeviceAt(pos);
		Rectangle bounds = null;
		if (gd != null) {
			bounds = gd.getDefaultConfiguration().getBounds();
		}
		return bounds;
	}

	private Insets getScreenInsetsAt(Point pos) {
		GraphicsDevice gd = getGraphicsDeviceAt(pos);
		Insets insets = null;
		if (gd != null) {
			insets = Toolkit.getDefaultToolkit().getScreenInsets(
					gd.getDefaultConfiguration());
		}
		return insets;
	}

	private GraphicsDevice getGraphicsDeviceAt(Point pos) {

		GraphicsDevice device = null;

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice lstGDs[] = ge.getScreenDevices();

		ArrayList<GraphicsDevice> lstDevices = new ArrayList<GraphicsDevice>(
				lstGDs.length);

		for (GraphicsDevice gd : lstGDs) {

			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			Rectangle screenBounds = gc.getBounds();

			if (screenBounds.contains(pos)) {

				lstDevices.add(gd);

			}

		}

		if (lstDevices.size() > 0) {
			device = lstDevices.get(0);
		} else {
			device = ge.getDefaultScreenDevice();
		}

		return device;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
