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

import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

public class TestTrayIcon02 {

	public static Image createImage(final String path, final String description) {
		Image image = null;
		final URL imageURL = PropertyLoader.loadFromClassPathAsURL(path);

		if (imageURL == null) {
		} else {
			image = new ImageIcon(imageURL, description).getImage();
		}

		return image;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}
				try {
					final TrayIcon ti = new TrayIcon(createImage(
							"images/bulb.gif", "fdsafdsaf"), "Have a nice day");
					final JPopupMenu popup = new JPopupMenu();

					JMenuItem mi = new JMenuItem("Get me some");
					mi.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SystemTray.getSystemTray().remove(ti);
							System.exit(0);
						}
					});

					popup.add(mi);
					ti.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON1
									&& e.getClickCount() == 1) {
								Rectangle bounds = getSafeScreenBounds(e
										.getPoint());
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
								if (x + popup.getPreferredSize().width > bounds.x
										+ bounds.width) {
									x = (bounds.x + bounds.width)
											- popup.getPreferredSize().width;
								}
								if (y + popup.getPreferredSize().height > bounds.y
										+ bounds.height) {
									y = (bounds.y + bounds.height)
											- popup.getPreferredSize().height;
								}
								popup.setLocation(x, y);
								popup.setVisible(true);
							}
						}
					});

					SystemTray.getSystemTray().add(ti);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	public static Rectangle getSafeScreenBounds(Point pos) {

		Rectangle bounds = getScreenBoundsAt(pos);
		Insets insets = getScreenInsetsAt(pos);

		bounds.x += insets.left;
		bounds.y += insets.top;
		bounds.width -= (insets.left + insets.right);
		bounds.height -= (insets.top + insets.bottom);

		return bounds;

	}

	public static Insets getScreenInsetsAt(Point pos) {
		GraphicsDevice gd = getGraphicsDeviceAt(pos);
		Insets insets = null;
		if (gd != null) {
			insets = Toolkit.getDefaultToolkit().getScreenInsets(
					gd.getDefaultConfiguration());
		}
		return insets;
	}

	public static Rectangle getScreenBoundsAt(Point pos) {
		GraphicsDevice gd = getGraphicsDeviceAt(pos);
		Rectangle bounds = null;
		if (gd != null) {
			bounds = gd.getDefaultConfiguration().getBounds();
		}
		return bounds;
	}

	public static GraphicsDevice getGraphicsDeviceAt(Point pos) {

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
}