/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.Util;

import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class JMath {
    public static void rotate(Vector2f vec,float angeleDeg,Vector2f origin) {
      float x = vec.x - origin.x;
      float y = vec.y - origin.y;
      float cos = (float)Math.cos(Math.toRadians(angeleDeg));
      float sin = (float)Math.sin(Math.toRadians(angeleDeg));
         float Xprime = (x *  cos) - (y * sin);
         float Yprime = (x *  cos) + (y * sin);
         Xprime += origin.x;
         Yprime += origin.y;
         vec.x = Xprime;
         vec.y = Yprime;
    }
    public static boolean compare(float x,float y) {
        return Math.abs(x-y) <= Float.MIN_VALUE * Math.max(1.0f,Math.max(Math.abs(x),Math.abs(y)));
    }
    public static boolean compare(Vector2f vec1,Vector2f vec2) {
        return compare(vec1.x,vec2.x) && compare(vec1.y,vec2.y);
    }
}
