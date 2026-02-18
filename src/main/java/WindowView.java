import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowView extends JPanel implements PropertyChangeListener {

    private GridView gridView;
    private Publisher pub;

    public WindowView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel guide = new JLabel("(Click 1 = START, Click 2 = END, Click = toggle OBSTACLE)");
        guide.setFont(new Font("Arial", Font.PLAIN, 20));
        guide.setHorizontalAlignment(SwingConstants.CENTER);
        add(guide, BorderLayout.NORTH);

        gridView = new GridView();
        add(gridView, BorderLayout.CENTER);

        GridModel.getInstance().addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("gridReset".equals(evt.getPropertyName())) {

            for (Component c : gridView.getComponents()) {
                if (c instanceof GridCellView cell) {
                    GridModel.getInstance().removePropertyChangeListener(cell);
                }
            }

            remove(gridView);
            gridView = new GridView();
            add(gridView, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    public static void removeAllCellListeners() {
        GridModel board = GridModel.getInstance();

        for (PropertyChangeListener listener : board.getPropertyChangeListeners()) {
            if (listener instanceof GridCellView) {
                board.removePropertyChangeListener(listener);
            }
        }
    }

}
