import level.Level;
import level.LevelGUI;
import level.Room;

import java.awt.*;

public class Driver {

    private Level level;
    private LevelGUI gui;

    public void run() {
        level = new Level();
        gui = new LevelGUI(level, "Amazing game of amazingnessnessiness");

        Room r1 = new Room(120,80, Color.cyan);
        Room r2 = new Room(160, 60, Color.green);
        Room r3 = new Room(60, 120, Color.orange);
        Room r4 = new Room(180, 40, Color.red);

        r1.connectNorthTo(r2);
        r1.connectSouthTo(r1);
        r2.connectWestTo(r3);
        r3.connectEastTo(r2);
        r3.connectSouthTo(r4);
        r4.connectNorthTo(r2);
        r4.connectEastTo(r1);
        r3.connectWestTo(r1);

        boolean place1 = level.place(r1, 100, 50),
                place2 = level.place(r2, 600, 400),
                place3 = level.place(r3, 200, 450),
                place4 = level.place(r4, 550, 100);


        System.out.println(place1);
        System.out.println(place2);
        System.out.println(place3);
        System.out.println(place4);

        level.firstLocation(r1);
        level.init();
    }


}