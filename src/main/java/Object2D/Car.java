package Object2D;

/**
 *
 * @author lucdat
 */
import ConsoleProperty.CarProperty;
import static console.Console2D.ChuyenMangSangToaDoMay;
import drawObject.Circle;
import drawObject.Line;
import drawObject.Triangle;
import drawObject.Rectangle;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import static transformations.Transformations.DoiXungQuaO;
import static transformations.Transformations.DoiXungQuaOx;
import static transformations.Transformations.DoiXungQuaOy;
import static transformations.Transformations.rotate;
import static transformations.Transformations.translatePoint;

public class Car extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private Thread thread;
    public JFrame frame;
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    private static boolean running = false;
    private static Point LsPoint[] = new Point[23];
    public CarProperty car;
    public static int doixung = 0;

    public Car() {
        this.frame = new JFrame();
        this.car = new CarProperty();
        this.car.setVisible(true);
        this.car.setLocation(1000, 120);
        this.car.setSize(300, 600);
        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);
        init();
    }

    public void init() {
        //Khung xe
        LsPoint[0] = new Point(-30, 5);
        LsPoint[1] = new Point(-5, 5);
        LsPoint[2] = new Point(-5, 10);
        LsPoint[3] = new Point(-10, 15);
        LsPoint[4] = new Point(-10, 17);
        LsPoint[5] = new Point(-25, 17);
        LsPoint[6] = new Point(-25, 15);
        LsPoint[7] = new Point(-30, 10);

        //Hinh chua nhat
        LsPoint[8] = new Point(-15, 10);
        LsPoint[9] = new Point(-15, 15);
        LsPoint[10] = new Point(-22, 15);
        LsPoint[11] = new Point(-22, 10);

        //hinh tam giac
        LsPoint[12] = new Point(-10, 10);
        LsPoint[13] = new Point(-13, 15);
        LsPoint[14] = new Point(-13, 10);

        //tam banh sau
        LsPoint[15] = new Point(-25, 5);
        //tam banh truoc
        LsPoint[16] = new Point(-10, 5);

        // tăm bánh xe sau
        LsPoint[17] = new Point(-25, 2);
        LsPoint[18] = new Point(-23, 8);
        LsPoint[19] = new Point(-27, 8);

        //tăm banh trước
        LsPoint[20] = new Point(-10, 2);
        LsPoint[21] = new Point(-8, 8);
        LsPoint[22] = new Point(-12, 8);
        this.LsPoint = translatePoint(LsPoint, -70, 0);
        if (doixung == 1) {
            this.LsPoint = DoiXungQuaOx(LsPoint);
        } else if (doixung == 2) {
            this.LsPoint = DoiXungQuaOy(LsPoint);
        } else if (doixung == 3) {
            this.LsPoint = DoiXungQuaO(LsPoint);
        }
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
        int i = 0, c = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update(i, frames);
                delta--;
                render();
                frames++;
                if (c == 0 && i < WIDTH / 4) {
                    i++;
                    if (i == WIDTH / 4) {
                        this.doixung = 1;
                        i = 0;
                        c++;
                    }
                } else if (c == 1 && i < WIDTH / 4) {
                    i++;

                    if (i == WIDTH / 4) {
                        this.doixung = 2;
                        i = 0;
                        c++;
                    }
                } else if (c == 2 && i < WIDTH / 4) {
                    i--;
                    if ((-1) * i == WIDTH / 4) {
                        this.doixung = 3;
                        i = 0;
                        c++;
                    }
                } else if (c == 3 && i < WIDTH / 4) {

                    i--;
                    if ((-1) * i == WIDTH / 4) {

                        i = 0;
                        c -= 3;
                        this.doixung = 0;
                    }
                }

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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);
        this.LsPoint = ChuyenMangSangToaDoMay(LsPoint);

        Polygon poly1 = new Polygon();
        for (int i = 0; i < 8; i++) {
            poly1.addPoint(LsPoint[i].x, LsPoint[i].y);
        }
        g.setColor(Color.ORANGE);
        g.fillPolygon(poly1);
        Polygon poly2 = new Polygon();
        for (int i = 8; i < 12; i++) {
            poly2.addPoint(LsPoint[i].x, LsPoint[i].y);
        }
        g.setColor(Color.CYAN);
        g.fillPolygon(poly2);
        Polygon poly3 = new Polygon();
        for (int i = 12; i < 15; i++) {
            poly3.addPoint(LsPoint[i].x, LsPoint[i].y);
        }
        g.setColor(Color.CYAN);
        g.fillPolygon(poly3);

        g.setColor(Color.GREEN);
        g.fillOval(LsPoint[15].x - 20, LsPoint[15].y - 20, 40, 40);
        g.setColor(Color.GREEN);
        g.fillOval(LsPoint[16].x - 20, LsPoint[16].y - 20, 40, 40);

        g.setColor(Color.red);
        g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        g.setColor(Color.red);
        g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);

        //ve khung xe
        Line line = new Line();
        line.DrawLine(LsPoint[0].x, LsPoint[0].y, LsPoint[1].x, LsPoint[1].y, g);
        line.DrawLine(LsPoint[1].x, LsPoint[1].y, LsPoint[2].x, LsPoint[2].y, g);

        line.DrawLine(LsPoint[2].x, LsPoint[2].y, LsPoint[3].x, LsPoint[3].y, g);

        line.DrawLine(LsPoint[3].x, LsPoint[3].y, LsPoint[4].x, LsPoint[4].y, g);

        line.DrawLine(LsPoint[4].x, LsPoint[4].y, LsPoint[5].x, LsPoint[5].y, g);

        line.DrawLine(LsPoint[5].x, LsPoint[5].y, LsPoint[6].x, LsPoint[6].y, g);

        line.DrawLine(LsPoint[6].x, LsPoint[6].y, LsPoint[7].x, LsPoint[7].y, g);

        line.DrawLine(LsPoint[7].x, LsPoint[7].y, LsPoint[0].x, LsPoint[0].y, g);

        //xe hcn
        Rectangle r = new Rectangle(LsPoint[8], LsPoint[9], LsPoint[10], LsPoint[11], g);
        //ve hinh tam giac
        Triangle triangle = new Triangle(LsPoint[12], LsPoint[13], LsPoint[14], g);

        Circle circle = new Circle();
        //ve banh sau
        circle.circleBres(LsPoint[15].x, LsPoint[15].y, 20, g);
        //ve banh truoc
        circle.circleBres(LsPoint[16].x, LsPoint[16].y, 20, g);
        //vẽ tăm banh sau
        line.DrawLine(LsPoint[15].x, LsPoint[15].y, LsPoint[17].x, LsPoint[17].y, g);
        line.DrawLine(LsPoint[15].x, LsPoint[15].y, LsPoint[18].x, LsPoint[18].y, g);
        line.DrawLine(LsPoint[15].x, LsPoint[15].y, LsPoint[19].x, LsPoint[19].y, g);
        //vẽ tăm banh trươc
        line.DrawLine(LsPoint[16].x, LsPoint[16].y, LsPoint[20].x, LsPoint[20].y, g);
        line.DrawLine(LsPoint[16].x, LsPoint[16].y, LsPoint[21].x, LsPoint[21].y, g);
        line.DrawLine(LsPoint[16].x, LsPoint[16].y, LsPoint[22].x, LsPoint[22].y, g);

        g.dispose();
        bs.show();

    }

    private void update(int i, int degree) {
        init();
        this.LsPoint = translatePoint(LsPoint, i, 0);
        Point[] pt1 = {LsPoint[17], LsPoint[18], LsPoint[19]};
        Point[] pt2 = {LsPoint[20], LsPoint[21], LsPoint[22]};
        pt1 = rotate(pt1, -degree * 6, LsPoint[15].x, LsPoint[15].y);
        pt2 = rotate(pt2, -degree * 6, LsPoint[16].x, LsPoint[16].y);
        LsPoint[17] = pt1[0];
        LsPoint[18] = pt1[1];
        LsPoint[19] = pt1[2];
        LsPoint[20] = pt2[0];
        LsPoint[21] = pt2[1];
        LsPoint[22] = pt2[2];
        showTD(LsPoint);
    }

    public void showTD(Point[] lsPoint) {
        this.car.getjTextField3().setText("X=" + LsPoint[0].x + "" + ",Y=" + LsPoint[0].y + "");
        this.car.getjTextField4().setText("X=" + LsPoint[1].x + "" + ",Y=" + LsPoint[1].y + "");
        this.car.getjTextField5().setText("X=" + LsPoint[2].x + "" + ",Y=" + LsPoint[2].y + "");
        this.car.getjTextField6().setText("X=" + LsPoint[3].x + "" + ",Y=" + LsPoint[3].y + "");
        this.car.getjTextField7().setText("X=" + LsPoint[4].x + "" + ",Y=" + LsPoint[4].y + "");
        this.car.getjTextField8().setText("X=" + LsPoint[5].x + "" + ",Y=" + LsPoint[5].y + "");
        this.car.getjTextField9().setText("X=" + LsPoint[6].x + "" + ",Y=" + LsPoint[6].y + "");
        this.car.getjTextField10().setText("X=" + LsPoint[7].x + "" + ",Y=" + LsPoint[7].y + "");
        this.car.getjTextField11().setText("X=" + LsPoint[8].x + "" + ",Y=" + LsPoint[8].y + "");
        this.car.getjTextField12().setText("X=" + LsPoint[9].x + "" + ",Y=" + LsPoint[9].y + "");
        this.car.getjTextField13().setText("X=" + LsPoint[10].x + "" + ",Y=" + LsPoint[10].y + "");
        this.car.getjTextField14().setText("X=" + LsPoint[11].x + "" + ",Y=" + LsPoint[11].y + "");
        this.car.getjTextField15().setText("X=" + LsPoint[12].x + "" + ",Y=" + LsPoint[12].y + "");
        this.car.getjTextField16().setText("X=" + LsPoint[13].x + "" + ",Y=" + LsPoint[13].y + "");
        this.car.getjTextField17().setText("X=" + LsPoint[14].x + "" + ",Y=" + LsPoint[14].y + "");
        this.car.getjTextField1().setText("X=" + LsPoint[15].x + "" + ",Y=" + LsPoint[15].y + "");
        this.car.getjTextField2().setText("X=" + LsPoint[16].x + "" + ",Y=" + LsPoint[16].y + "");
    }
}
