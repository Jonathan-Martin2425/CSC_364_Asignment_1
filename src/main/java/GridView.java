import javax.swing.*;
import java.awt.*;

public class GridView extends JPanel {

    private final GridModel board = GridModel.getInstance();

    public GridView() {
        int size = board.getSize();
        setLayout(new GridLayout(size, size));

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                add(new GridCellView(x, y));
            }
        }
    }
}

