/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.Render;

import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class Line {
private Vector2f from;

    public Line(Vector2f from, Vector2f to) {
        this.from = from;
        this.to = to;
    }

    public Vector2f getFrom() {
        return from;
    }

    public Vector2f getTo() {
        return to;
    }
    public Vector2f Getstart() {
     return this.from;   
    }
    public Vector2f Getend() {
     return this.to;
    }
    public float LengthSquared() {
        return new Vector2f(to).sub(from).lengthSquared();
    }
private Vector2f to;    
}
