
package Object2D;

import ConsoleProperty.ClockProperty;
import static console.Console2D.ChuyenMangSangToaDoMay;
import drawObject.Circle;
import drawObject.Line;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.Date;
import javax.swing.JFrame;
import static transformations.Transformations.rotate;

/**
 *
 * @author lucdat
 */
public class Clock extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private Thread thread;
    public JFrame frame;
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    private static boolean running = false;
    private static Point LsPoint[] = new Point[8];
    private ClockProperty clock;

    public static int doixung = 0;

    public Clock() {
        this.frame = new JFrame();
        this.clock = new ClockProperty();
        this.clock.setVisible(true);
        this.clock.setLocation(1000, 120);
        this.clock.setSize(300, 600);
        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);
        init();
    }

    public void init() {
        LsPoint[0] = new Point(0, 0);
        LsPoint[1] = new Point(0, 8);
        LsPoint[2] = new Point(0, 10);
        LsPoint[3] = new Point(0, 15);
        LsPoint[4] = new Point(0, 30);
        LsPoint[5] = new Point(-40, 0);
        LsPoint[6] = new Point(0, -50);
        LsPoint[7] = new Point(60, 0);
    }

    public synchronized void start() {
        running = true;
        this.thread = new Thread(this, "Display");
        this.thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
                render();
                frames++;

            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }

        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);
        LsPoint = ChuyenMangSangToaDoMay(LsPoint);
        
         //to mau qua cau
        g.setColor(Color.yellow);
        g.fillOval(LsPoint[4].x - 20, LsPoint[4].y - 20, 40, 40);
        g.setColor(Color.yellow);
        g.fillOval(LsPoint[5].x - 30, LsPoint[5].y - 30, 60, 60);
        g.setColor(Color.yellow);
        g.fillOval(LsPoint[6].x - 40, LsPoint[6].y - 40, 80, 80);
        
        g.setColor(Color.yellow);
        g.fillOval(LsPoint[7].x - 50, LsPoint[7].y - 50, 100, 100);
        
        g.setColor(Color.red);
        //ve khung dong ho 
        Circle circle = new Circle();
        circle.circleBres(LsPoint[0].x, LsPoint[0].y, 100, g);
        circle.circleBres(LsPoint[4].x, LsPoint[4].y, 20, g);
        circle.circleBres(LsPoint[5].x, LsPoint[5].y, 30, g);
        circle.circleBres(LsPoint[6].x, LsPoint[6].y, 40, g);
        circle.circleBres(LsPoint[7].x, LsPoint[7].y, 50, g);
        circle.circleBres(LsPoint[0].x, LsPoint[0].y, 190, g);
        circle.circleBres(LsPoint[0].x, LsPoint[0].y, 220, g);
        circle.circleBres(LsPoint[0].x, LsPoint[0].y, 270, g);
        circle.circleBres(LsPoint[0].x, LsPoint[0].y, 320, g);
        //ve kim
        Line line = new Line();
        //ve kim gio
        line.DrawLine(LsPoint[0].x, LsPoint[0].y, LsPoint[1].x, LsPoint[1].y, g);
        //ve kim phut
        line.DrawLine(LsPoint[0].x, LsPoint[0].y, LsPoint[2].x, LsPoint[2].y, g);
        //kim giay
        line.DrawLine(LsPoint[0].x, LsPoint[0].y, LsPoint[3].x, LsPoint[3].y, g);

        g.dispose();
        bs.show();

    }

    private void update() {
        init();
        Date dt = new Date();
        int[] Angle = new int[3];
        Angle[0] = (int) ((dt.getHours() * 30) % 360 + dt.getMinutes() / 2);
        Angle[1] = (int) (dt.getMinutes() * 6);
        Angle[2] = (int) (dt.getSeconds() * 6);
        Point[] p1 = new Point[1];
        p1[0] = LsPoint[1];
        Point[] p2 = new Point[1];
        p2[0] = LsPoint[2];
        Point[] p3 = new Point[1];
        p3[0] = LsPoint[3];
        //quay kim gio neu gio 
        p1 = rotate(p1, -Angle[0], LsPoint[0].x, LsPoint[0].y);
        LsPoint[1] = p1[0];
        //quay kim phut neu gio 
        p2 = rotate(p2, -Angle[1], LsPoint[0].x, LsPoint[0].y);
        LsPoint[2] = p2[0];
        //quay kim phut neu giay
        p3 = rotate(p3, -Angle[2], LsPoint[0].x, LsPoint[0].y);
        LsPoint[3] = p3[0];

        //quay qua cau 
        Point[] p4 = new Point[1];
        p4[0] = LsPoint[4];
        p4 = rotate(p4, -Angle[2], LsPoint[0].x, LsPoint[0].y);
        LsPoint[4] = p4[0];
        Point[] p5 = new Point[1];
        p5[0] = LsPoint[5];
        p5 = rotate(p5, -Angle[2], LsPoint[0].x, LsPoint[0].y);
        LsPoint[5] = p5[0];
        Point[] p6 = new Point[1];
        p6[0] = LsPoint[6];
        p6 = rotate(p6, -Angle[2], LsPoint[0].x, LsPoint[0].y);
        LsPoint[6] = p6[0];
        Point[] p7 = new Point[1];
        p7[0] = LsPoint[7];
        p7 = rotate(p7, -Angle[2], LsPoint[0].x, LsPoint[0].y);
        LsPoint[7] = p7[0];
        showTD(LsPoint);
    }

    public void showTD(Point[] lsPoint) {
        this.clock.getjTextField1().setText("X=" + lsPoint[3].x + ",Y=" + lsPoint[3].y);
        this.clock.getjTextField2().setText("X=" + lsPoint[2].x + ",Y=" + lsPoint[2].y);
        this.clock.getjTextField3().setText("X=" + lsPoint[1].x + ",Y=" + lsPoint[1].y);
        this.clock.getjTextField4().setText(new Date().toString());
    }
}
