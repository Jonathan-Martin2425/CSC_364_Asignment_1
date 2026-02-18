import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowView extends JPanel {

    private GridView gridView;
    private Publisher pub;

    public WindowView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        gridView = new GridView();
        add(gridView, BorderLayout.CENTER);
    }

}
