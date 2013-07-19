package max.utility.tomato.gui;

import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import max.utility.tomato.domain.Tomato;

import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

public class TrayIconMouseMotionListener implements MouseMotionListener {
    private TrayIcon trayIcon;
    private Tomato tomato;

    public TrayIconMouseMotionListener(TrayIcon trayIcon/* , Tomato tomato */) {
        this.trayIcon = trayIcon;
        // this.tomato = tomato;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (null != tomato) {
            LocalDateTime startTime = tomato.getStartTime();
            LocalDateTime endTimer = startTime
                    .plusMinutes(tomato.getDuration());
            LocalDateTime now = new LocalDateTime();

            String timeLeft = "min: "
                    + Minutes.minutesBetween(endTimer, now).getMinutes();

            trayIcon.setToolTip(timeLeft + " / "
                    + System.getProperty("line.separator") + tomato.getTitle()
                    + " / " + System.getProperty("line.separator")
                    + tomato.getFocusOn());
        } else {
            trayIcon.setToolTip(null);
        }
    }

    public void setTomato(Tomato tomato) {
        this.tomato = tomato;
    }

}
