package drawObject;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lucdat
 */
public class Circle {

    public Circle() {

    }

    public void drawCircle(int xc, int yc, int x, int y, Graphics g) {
        g.setColor(Color.red);
        //Mot diem se ung voi 8 diem duoc ve
        g.drawString(".", xc + x, yc + y);
        g.drawString(".", xc - x, yc + y);
        g.drawString(".", xc + x, yc - y);
        g.drawString(".", xc - x, yc - y);
        g.drawString(".", xc + y, yc + x);
        g.drawString(".", xc - y, yc + x);
        g.drawString(".", xc + y, yc - x);
        g.drawString(".", xc - y, yc - x);
    }

    public void circleBres(int xc, int yc, int r, Graphics g) {
      
        //bắt đầu từ (0, r) và di chuyển theo góc phần tư đầu tiên cho đến x = y . 
        int x = 0, y = r;
        int d = 3 - 2 * r;
        drawCircle(xc, yc, x, y, g);
        while (y >= x) {
            x++;
            //Nếu d> 0, thì (x + 1, y-1) sẽ được chọn làm điểm tiếp theo vì nó sẽ gần cung hơn.
            //else (x + 1, y) sẽ được chọn làm diểm tiếp theo.
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            drawCircle(xc, yc, x, y, g);
        }
    }
}
