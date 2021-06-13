package transformations;

import java.awt.Point;

/**
 *
 * @author lucdat
 */
public class Transformations {

    public static Point[] rotate(Point[] point, int degree, int xp, int yp) {

        float t, v;
        float radian = (float) (degree * (2 * Math.PI / 360));
        for (Point point1 : point) {
            t = point1.x - xp;
            v = point1.y - yp;
            point1.x = (int) (xp + t * Math.cos(radian) - v * Math.sin(radian));
            point1.y = (int) (yp + v * Math.cos(radian) + t * Math.sin(radian));
        }

        return point;
    }

    public static Point[] translatePoint(Point[] point, int tx, int ty) {
        for (Point p : point) {
            p.x += tx;
            p.y += ty;
        }
        return point;
    }

    public static Point[] DoiXungQuaOx(Point[] point) {
        for (int i = 0; i < point.length; i++) {
            point[i].y *= (-1);
        }

        return point;
    }

    public static Point[] DoiXungQuaOy(Point[] point) {
        for (int i = 0; i < point.length; i++) {
            point[i].x *= (-1);
        }
        return point;
    }

    public static Point[] DoiXungQuaO(Point[] point) {
        for (int i = 0; i < point.length; i++) {
            point[i].x *= (-1);
            point[i].y *= (-1);
        }
        return point;
    }

    public static Point Chuyen3DThanh2D(float X, float Y, float Z) {
        float alpha = (float) Math.PI / 4,L,Xp,Yp;
        L = Z / (float) Math.tan(alpha);
        Xp = (float) (X - (float) Z * Math.sqrt(2) / 2);
        Yp = (float) (Y - (float) Z * Math.sqrt(2) / 2);

        return new Point((int) Xp, (int) Yp);
    }

}
