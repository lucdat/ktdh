package drawObject;

import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author lucdat
 */
public class Line {

    public void DrawLine(int x0,int y0,int x1,int y1, Graphics g) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            g.setColor(Color.red);
            g.drawString(".", x0, y0);

            if ((x0 == x1) && (y0 == y1)) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

}
