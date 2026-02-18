import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Player{
    public Point pos;
    public GridCell color;

    public Player(Point pos, GridCell color){
        this.pos = pos;
        this.color = color;
    }
}
