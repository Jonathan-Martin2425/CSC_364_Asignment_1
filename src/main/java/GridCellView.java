import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GridCellView extends JPanel implements PropertyChangeListener {

    private final int x;
    private final int y;
    private final GridModel board = GridModel.getInstance();
    private static int clickCount = 0;

    public GridCellView(int x, int y) {
        this.x = x;
        this.y = y;

        setOpaque(true);
        setPreferredSize(new Dimension(20, 20));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        updateColor(board.get(x, y));
        board.addPropertyChangeListener(this);


    }

    private void updateColor(GridCell cell) {
        switch(cell) {
            case EMPTY -> setBackground(Color.WHITE);
            case OPPONENT -> setBackground(Color.CYAN);
            case PLAYER -> setBackground(Color.RED);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("cell".equals(evt.getPropertyName())) {
            Point p = (Point) evt.getNewValue();
            if (p.x == x && p.y == y) {
                updateColor(board.get(x, y));
            }
        }

        if ("gridReset".equals(evt.getPropertyName())) {
            clickCount = 0;
            updateColor(board.get(x, y));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(25, 25); // square cell
    }

    public static void resetClickCount() {
        clickCount = 0;
    }


}