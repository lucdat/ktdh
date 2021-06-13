package Object3D;

import static console.Console2D.ChuyenMangSangToaDoMay3D;
import drawObject.DotLine;
import drawObject.Line;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author lucdat
 */
public class Rectangular extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private Thread thread;
    public JFrame frame;
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    private static boolean running = false;
    private static Point3D LsPoint[] = new Point3D[8];

    public Rectangular(int dai, int rong, int cao, int x, int y, int z) {
        this.frame = new JFrame();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);
        init(dai, rong, cao, x, y, z);
    }

    public void init(int chieuDai, int chieuRong, int chieuCao, int x, int y, int z) {
        LsPoint[0] = new Point3D(x, y, z);
        LsPoint[1] = new Point3D(x + chieuDai, y, z);
        LsPoint[2] = new Point3D(x + chieuDai, y, z + chieuRong);
        LsPoint[3] = new Point3D(x, y, z + chieuRong);

        LsPoint[4] = new Point3D(x, y + chieuCao, z);
        LsPoint[5] = new Point3D(x + chieuDai, y + chieuCao, z);
        LsPoint[6] = new Point3D(x + chieuDai, y + chieuCao, z + chieuRong);
        LsPoint[7] = new Point3D(x, y + chieuCao, z + chieuRong);

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

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {

                delta--;
                render();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

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

        Line line = new Line();
        g.setColor(Color.white);
        g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight() / 2);
        g.setColor(Color.white);
        g.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        g.setColor(Color.white);
        g.drawLine(this.getWidth() / 4 - 70, this.getHeight(), this.getWidth() / 2, this.getHeight() / 2);

        //ve hinh
        Point[] point = ChuyenMangSangToaDoMay3D(LsPoint);
        //net lien

        line.DrawLine(point[1].x, point[1].y, point[2].x, point[2].y, g);
        line.DrawLine(point[2].x, point[2].y, point[3].x, point[3].y, g);
        line.DrawLine(point[4].x, point[4].y, point[5].x, point[5].y, g);
        line.DrawLine(point[5].x, point[5].y, point[6].x, point[6].y, g);
        line.DrawLine(point[6].x, point[6].y, point[7].x, point[7].y, g);
        line.DrawLine(point[7].x, point[7].y, point[4].x, point[4].y, g);
        line.DrawLine(point[1].x, point[1].y, point[5].x, point[5].y, g);
        line.DrawLine(point[2].x, point[2].y, point[6].x, point[6].y, g);
        line.DrawLine(point[3].x, point[3].y, point[7].x, point[7].y, g);
        //net dut
        DotLine dotLine = new DotLine();
        dotLine.DrawDotLine(point[0].x, point[0].y, point[1].x, point[1].y, g);
        dotLine.DrawDotLine(point[3].x, point[3].y, point[0].x, point[0].y, g);
        dotLine.DrawDotLine(point[0].x, point[0].y, point[4].x, point[4].y, g);
        g.dispose();
        bs.show();

    }

    public static Point3D[] getLsPoint() {
        return LsPoint;
    }

    public static void setLsPoint(Point3D[] LsPoint) {
        Rectangular.LsPoint = LsPoint;
    }

}
