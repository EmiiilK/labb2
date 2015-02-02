package level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Level extends Observable {

    private ArrayList<Room> rooms = new ArrayList<Room>();
    Room currentRoom;
    Dimension windowSize = new Dimension(800,600);
    private Rectangle winRekt = new Rectangle(0,0,windowSize.width, windowSize.height);

    public boolean place(Room r, int x, int y)  {
        r.bounds.x = x; r.bounds.y = y;
        if(!winRekt.contains(r.bounds))
            return false;

        for(Room room : rooms) {
            if(room.bounds.intersects(r.bounds)) {
                return false;
            }
        }

        rooms.add(r);

        setChanged();
        notifyObservers();
        return true;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void firstLocation(Room r) {
        currentRoom = r;
    }

    public void init() {
        for(Room r : rooms) {
            r.setPlaced(true);
        }

        setChanged();
        notifyObservers();
    }

    public void moveEast() {
        if(currentRoom.east != null) {
            currentRoom = currentRoom.east;
        }
    }
    public void moveWest() {
        if(currentRoom.west != null) {
            currentRoom = currentRoom.west;
        }
    }
    public void moveNorth() {
        if(currentRoom.north != null) {
            currentRoom = currentRoom.north;
        }
    }
    public void moveSouth() {
        if(currentRoom.south != null) {
            currentRoom = currentRoom.south;
        }
    }

}