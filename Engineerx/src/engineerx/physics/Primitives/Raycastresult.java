/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Primitives;

import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class Raycastresult {
    private Vector2f point;
    private Vector2f normal;
    private float t;
    private boolean hit;
    public Raycastresult() {
        this.point = new Vector2f();
        this.normal = new Vector2f();
        this.t = -1;
        this.hit = false;
    }
    public void intit(Vector2f point,Vector2f normal,float t,boolean hit) {
        this.point.set(point);
        this.normal.set(normal);
        this.t = t;
        this.hit = hit;
    }
    public static void reset(Raycastresult result) {
       if (result != null) {
           result.point.zero();
           result.normal.set(0,0);
           result.t = -1;
           result.hit = false;
       } 
    }
}
