package level;

import java.awt.*;


public class Room {

    Room north;
    Room south;
    Room west;
    Room east;

    Point[] northPoints;
    Point[] southPoints;
    Point[] eastPoints;
    Point[] westPoints;

    private boolean placed = false;
    Color color;

    Rectangle bounds;

    public Room(int width, int height, Color c) {
        bounds = new Rectangle(0,0,width,height);
        color = c;
    }

    public void setPlaced(boolean placed) {
        if(placed) {
            if(north != null) northPoints = getClosestPoints(bounds, north.bounds);
            if(south != null) southPoints = getClosestPoints(bounds, south.bounds);
            if(west != null) westPoints = getClosestPoints(bounds, west.bounds);
            if(east != null) eastPoints = getClosestPoints(bounds, east.bounds);
        }

        this.placed = placed;
    }

    private Point[] getClosestPoints(Rectangle r1, Rectangle r2) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);

        Point[][] points = {{new Point(r1.x, r1.y), new Point(r1.x, r1.y + r1.height), new Point(r1.x + r1.width, r1.y), new Point(r1.x + r1.width, r1.y + r1.height)},{new Point(r2.x, r2.y), new Point(r2.x, r2.y + r2.height), new Point(r2.x + r2.width, r2.y), new Point(r2.x + r2.width, r2.y + r2.height)}};

        double currentLength = Double.MAX_VALUE;
        for(int i = 0; i<points[0].length; i++) {
            Point op = points[0][i];
            for(int ii = 0; ii<points[1].length; ii++) {
                Point tp = points[1][ii];
                double dis = op.distanceSq(tp);
                if(dis < currentLength) {
                    p1 = op;
                    p2 = tp;
                    currentLength = dis;
                }
            }
        }

        return new Point[] {p1,p2};
    }


    public void connectNorthTo(Room r) {
        north = r;
    }
    public void connectEastTo(Room r) {
        east = r;
    }
    public void connectSouthTo(Room r) {
        south = r;
    }
    public void connectWestTo(Room r) {
        west = r;
    }
}
