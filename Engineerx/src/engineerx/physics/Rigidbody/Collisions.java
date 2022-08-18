/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Rigidbody;

import engineerx.physics.Primitives.Circle;
import engineerx.physics.Primitives.Collider;
import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class Collisions {
    public static CollisionManifold findCollisionFeatures(Collider a,Collider b) {
        if(a instanceof Circle && b instanceof Circle) {
            return findCollisionFeatures((Circle)a,(Circle)b);
        } else {
            assert false : "Unknown collider" + a.getClass() + "vs" +b.getClass();
            
        }
        return null;
    }
 public static  CollisionManifold findCollisionFeatures(Circle a,Circle b) {
CollisionManifold result = new CollisionManifold();
float sumRadii = a.Getradius() + b.Getradius();
Vector2f distance = new Vector2f(b.getCenter()).sub(a.getCenter());
if(distance.lengthSquared() - (sumRadii + sumRadii) > 0) {
    return result;
}
float depth  = Math.abs(distance.length() - sumRadii) * 0.5f;
Vector2f normal = new Vector2f(distance);
normal.normalize();
float distancetopoint = a.Getradius() - depth;
Vector2f contactpoint = new Vector2f(a.getCenter()).add(
   new Vector2f(normal).mul(distancetopoint)
);
result = new CollisionManifold(normal,depth);
result.AddContactPoint(contactpoint);
return result;
 }   

    
}
