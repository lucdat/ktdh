/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawObject;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author lucdat
 */
public class Rectangle {

    public Rectangle(Point a,Point b,Point c,Point d,Graphics g) {
        Line line = new Line();
        line.DrawLine(a.x, a.y, b.x, b.y, g);
        line.DrawLine(c.x, c.y, b.x, b.y, g);
        line.DrawLine(c.x, c.y, d.x, d.y, g);
        line.DrawLine(d.x, d.y, a.x, a.y, g);
    }
    
}
