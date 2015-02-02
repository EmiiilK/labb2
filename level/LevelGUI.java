package level;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LevelGUI implements Observer {

    private Level lv;
    private Display d;

    public LevelGUI(Level level, String name) {

        this.lv = level;

        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        d = new Display(lv,lv.windowSize.width, lv.windowSize.height);

        frame.getContentPane().add(d);
        frame.pack();
        frame.setLocation(0,0);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(100,100);

        level.addObserver(this);
    }


    public void update(Observable level, Object arg1) {
        d.repaint();
    }

    private class Display extends JPanel {

        private Level level;

        public Display(Level fp, int x, int y) {
            level = fp;

            addKeyListener(new Listener());

            setBackground(Color.white);
            setPreferredSize(new Dimension(x+20,y+20));
            setFocusable(true);
        }

        private void drawRoom(Graphics2D g, Room r) {
            Rectangle rekt = r.bounds.getBounds();
            g.setColor(r.color);
            g.fillRect(rekt.x, rekt.y, rekt.width, rekt.height);

            if(r == level.currentRoom) {
                String str = "Current room";
                Font f = new Font("sans serif", Font.BOLD, 15);
                g.setFont(f);
                FontMetrics fm = g.getFontMetrics();
                int width = fm.stringWidth(str);
                int height = fm.getHeight();

                g.drawString(str, rekt.x + rekt.width/2 - width / 2, rekt.y + rekt.height + height);
            }

            g.setColor(Color.black);
            g.setStroke(new BasicStroke(2.0f));
            g.drawRect(rekt.x, rekt.y, rekt.width, rekt.height);
        }
        private void drawLineEast(Graphics2D g, Room r1, Room r2) {
            Rectangle rekt1 = r1.bounds.getBounds(),
                      rekt2 = r2.bounds.getBounds();

            Stroke oldStroke = g.getStroke();
            g.setColor(r1.color);
            g.setStroke(new BasicStroke(1.0f));
            g.drawLine(r1.eastPoints[0].x, r1.eastPoints[0].y, r1.eastPoints[1].x, r1.eastPoints[1].y);
            g.setStroke(oldStroke);

            g.setColor(r1.east.color);
            g.drawLine(rekt1.x + rekt1.width, rekt1.y, rekt1.x + rekt1.width, rekt1.y + rekt1.height);
        }
        private void drawLineWest(Graphics2D g, Room r1, Room r2) {
            Rectangle rekt1 = r1.bounds.getBounds(),
                    rekt2 = r2.bounds.getBounds();

            Stroke oldStroke = g.getStroke();
            g.setColor(r1.color);
            g.setStroke(new BasicStroke(1.0f));
            g.drawLine(r1.westPoints[0].x, r1.westPoints[0].y, r1.westPoints[1].x, r1.westPoints[1].y);
            g.setStroke(oldStroke);

            g.setColor(r1.west.color);
            g.drawLine(rekt1.x, rekt1.y, rekt1.x, rekt1.y + rekt1.height);
        }
        private void drawLineSouth(Graphics2D g, Room r1, Room r2) {
            Rectangle rekt1 = r1.bounds.getBounds(),
                      rekt2 = r2.bounds.getBounds();

            Stroke oldStroke = g.getStroke();
            g.setColor(r1.color);
            g.setStroke(new BasicStroke(1.0f));
            g.drawLine(r1.southPoints[0].x, r1.southPoints[0].y, r1.southPoints[1].x, r1.southPoints[1].y);
            g.setStroke(oldStroke);

            g.setColor(r1.south.color);
            g.drawLine(rekt1.x, rekt1.y + rekt1.height, rekt1.x + rekt1.width, rekt1.y + rekt1.height);
        }
        private void drawLineNorth(Graphics2D g, Room r1, Room r2) {
            Rectangle rekt1 = r1.bounds.getBounds(),
                      rekt2 = r2.bounds.getBounds();

            Stroke oldStroke = g.getStroke();
            g.setColor(r1.color);
            g.setStroke(new BasicStroke(1.0f));
            g.drawLine(r1.northPoints[0].x, r1.northPoints[0].y, r1.northPoints[1].x, r1.northPoints[1].y);
            g.setStroke(oldStroke);

            g.setColor(r1.north.color);
            g.drawLine(rekt1.x, rekt1.y, rekt1.x + rekt1.width, rekt1.y);
        }

        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D)graphics;

            for(Room room : level.getRooms()) {
                drawRoom(g, room);
                if(room.east != null) {
                    drawLineEast(g, room, room.east);
                }
                if(room.west != null) {
                    drawLineWest(g, room, room.west);
                }
                if(room.north != null) {
                    drawLineNorth(g, room, room.north);
                }
                if(room.south != null) {
                    drawLineSouth(g, room, room.south);
                }
            }
        }


        private class Listener implements KeyListener {

            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        lv.moveNorth();
                        break;

                    case KeyEvent.VK_S:
                        lv.moveSouth();
                        break;

                    case KeyEvent.VK_A:
                        lv.moveWest();
                        break;

                    case KeyEvent.VK_D:
                        lv.moveEast();
                        break;

                    default: break;
                }

                repaint();
            }
            public void keyReleased(KeyEvent e) {

            }
            public void keyTyped(KeyEvent e) {

            }
        }

    }

}
