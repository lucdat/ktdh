package drawObject;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author lucdat
 */
public class Triangle {

    public Triangle(Point a, Point b, Point c, Graphics g) {
        Line line = new Line();
        line.DrawLine(a.x, a.y, b.x, b.y, g);
        line.DrawLine(c.x, c.y, b.x, b.y, g);
        line.DrawLine(c.x, c.y, a.x, a.y, g);
    }

}
