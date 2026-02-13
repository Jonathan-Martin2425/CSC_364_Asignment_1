import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowView extends JPanel implements PropertyChangeListener {

    private GridView gridView;
    private Publisher pub;

    public WindowView(Publisher pub) {
        this.pub = pub;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel guide = new JLabel("(Click 1 = START, Click 2 = END, Click = toggle OBSTACLE)");
        guide.setFont(new Font("Arial", Font.PLAIN, 20));
        guide.setHorizontalAlignment(SwingConstants.CENTER);
        add(guide, BorderLayout.NORTH);

        gridView = new GridView();
        add(gridView, BorderLayout.CENTER);

        GridModel.getInstance().addPropertyChangeListener(this);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Point player = pub.getPlayer();
                Point newPos = player;
                if(e.getKeyChar() == 'w'){
                    newPos = new Point(player.x, player.y + 1);
                }else if(e.getKeyChar() == 'a'){
                    newPos = new Point(player.x - 1, player.y);
                }else if(e.getKeyChar() == 's'){
                    newPos = new Point(player.x, player.y - 1);
                }else if(e.getKeyChar() == 'd'){
                    newPos = new Point(player.x + 1, player.y);
                }

                if(!player.equals(newPos)){
                    GridModel.getInstance().set(player.x, player.y, GridCell.EMPTY);
                    GridModel.getInstance().set(newPos.x, newPos.y, GridCell.VISITED);
                    pub.setPlayer(newPos);
                }

                System.out.println(pub.getPlayer());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setFocusable(true);
        requestFocusInWindow();
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
