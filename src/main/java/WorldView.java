import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WorldView extends JPanel implements PropertyChangeListener {
    private static final WorldView instance = new WorldView();
    private Player me;
    private Player other;;
    public WorldView(){
        this.me = new Player(new Point(5, 5), GridCell.PLAYER);
        this.other = new Player(new Point(0, 0), GridCell.EMPTY);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Point newPos = me.pos;
                if(e.getKeyChar() == 'w'){
                    newPos = new Point(me.pos.x, me.pos.y - 1);
                }else if(e.getKeyChar() == 'a'){
                    newPos = new Point(me.pos.x - 1, me.pos.y);
                }else if(e.getKeyChar() == 's'){
                    newPos = new Point(me.pos.x, me.pos.y + 1);
                }else if(e.getKeyChar() == 'd'){
                    newPos = new Point(me.pos.x + 1, me.pos.y);
                }

                if(!me.pos.equals(newPos)){
                    me.pos = newPos;
                    instance.repaintWorldView();
                }
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
    public void propertyChange(PropertyChangeEvent evt){
        repaintWorldView();
    }

    public void repaintWorldView() {
        GridModel.getInstance().setSize(GridModel.getInstance().getSize());
        GridModel.getInstance().set(other.pos.x, other.pos.y, other.color);
        GridModel.getInstance().set(me.pos.x, me.pos.y, me.color);
    }

    public static WorldView getInstance(){
        return instance;
    }

    public Player getMe(){
        return me;
    }

    public Player getOther() {
        return other;
    }

    public void setOther(Point oth){
        if(other.color.equals(GridCell.EMPTY)) other.color = GridCell.OPPONENT;
        other.pos = oth;
        repaintWorldView();
    }
}
