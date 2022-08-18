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
public class Raycast {
    private Vector2f origin;
    private Vector2f direction;
    public Raycast(Vector2f origin,Vector2f direction) {
        this.origin = origin;
                this.direction = direction;
                this.direction.normalize();
    }          

    public Vector2f getOrigin() {
        return origin;
    }

    public Vector2f getDirection() {
        return direction;
    }
}
