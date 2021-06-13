package console;

import Object3D.Point3D;
import java.awt.Graphics;
import java.awt.Point;
import static transformations.Transformations.Chuyen3DThanh2D;

/**
 *
 * @author lucdat
 */
public class Console2D extends javax.swing.JPanel {

    public static int width;
    public static int height;
    public static int pixel = 5;

    public Console2D() {
        initComponents();
        System.out.println(this.getWidth() + " " + this.getHeight());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(61, 61, 61));
        setForeground(new java.awt.Color(0, 0, 0));
        setAutoscrolls(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        width = getWidth() / 2;
        height = getHeight() / 2;
    }

    public static Point ChuyenSangToaDoMay(Point p) {
        int x = p.x,
                y = p.y;
        x = x > 0 ? (x * pixel + width) : (width - Math.abs(x) * pixel);
        y = y > 0 ? (height - y *pixel) : (height + Math.abs(y) *pixel);

        return new Point(x, y);
    }

    public static Point[] ChuyenMangSangToaDoMay(Point[] p) {
        for (int i = 0; i < p.length; i++) {
            System.out.println(ChuyenSangToaDoMay(p[i]));
            p[i] = ChuyenSangToaDoMay(p[i]);
        }
        return p;
    }

    public static Point ChuyenSangToaDoMay3D(int X, int Y, int Z) {
        Point point = new Point();
        point = Chuyen3DThanh2D(X, Y, Z);
        int x = point.x,
                y = point.y;
        x = x > 0 ? (x * pixel + width) : (width - Math.abs(x) * pixel);
        y = y > 0 ? (height - y *pixel) : (height + Math.abs(y) * pixel);

        return new Point(x, y);
    }

    public static Point[] ChuyenMangSangToaDoMay3D(Point3D[] p) {
        Point[] temp = new Point[p.length];
        for (int i = 0; i < p.length; i++) {
           temp[i] =ChuyenSangToaDoMay3D(p[i].x,p[i].y,p[i].z);
        }
        return temp;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
