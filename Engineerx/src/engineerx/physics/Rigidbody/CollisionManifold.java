/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Rigidbody;

import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class CollisionManifold {
private Vector2f normal;
private List<Vector2f> Contactpoints;
private float Depth;
private boolean isColliding;
    
    public Vector2f getNormal() {
        return normal;
    }

    public List<Vector2f> getContactpoint() {
        return Contactpoints;
    }

    public float getCollisionDepth() {
        return Depth;
    }
public CollisionManifold() {
this.normal = new Vector2f();
this.Contactpoints = Contactpoints;
this.Depth = 0.0f;
this.isColliding = false;
}
    public CollisionManifold(Vector2f normal,float CollisionDepth) {
        this.normal = normal;
        this.Contactpoints = new ArrayList<>();
        this.Depth = CollisionDepth;
        this.isColliding = true;
    }
public void AddContactPoint(Vector2f contact) {
    this.Contactpoints.add(contact);
}
public boolean isColliding() {
    return this.isColliding;
}
}
