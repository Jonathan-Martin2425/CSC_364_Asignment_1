import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GridModel extends PropertyChangeSupport {

    private static final GridModel instance = new GridModel();
    private GridCell[][] grid;
    private int size;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private GridModel() {
        super(new Object());
        setSize(20); // default
    }

    public static GridModel getInstance() {
        return instance;
    }

    public void setSize(int size) {
        Lock write = lock.writeLock();
        write.lock();
        try{
            this.size = size;
            grid = new GridCell[size][size];

            for (int y = 0; y < size; y++)
                for (int x = 0; x < size; x++)
                    grid[y][x] = GridCell.EMPTY;

            firePropertyChange("gridReset", null, null);
        } finally {
            write.unlock();
        }
    }

    public GridCell get(int x, int y) {
        Lock read = lock.readLock();
        read.lock();
        try{
            return grid[y][x];
        }finally {
            read.unlock();
        }
    }

    public void set(int x, int y, GridCell value) {
        Lock write = lock.writeLock();
        write.lock();
        try{
            GridCell prev = grid[y][x];
            grid[y][x] = value;
            firePropertyChange("cell", prev, new Point(x, y));
        }finally {
            write.unlock();
        }
    }

    public int getSize() {
        Lock read = lock.readLock();
        read.lock();
        try{
            return size;
        }finally {
            read.unlock();
        }
    }
}
