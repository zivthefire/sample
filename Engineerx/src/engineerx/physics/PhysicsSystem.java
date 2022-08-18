/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics;

import engineerx.physics.Primitives.Collider;
import engineerx.physics.Rigidbody.CollisionManifold;
import engineerx.physics.Rigidbody.Collisions;
import engineerx.physics.Rigidbody.Rigidbody;
import engineerx.physics.Rigidbody.forces.ForceRegistry;
import engineerx.physics.Rigidbody.forces.Gravity;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class PhysicsSystem {
private  ForceRegistry forceRegistry;

private List<Rigidbody> rigidbodies;

private List<Rigidbody> rigidbodies1;

private List<Rigidbody> rigidbodies2;

private List<CollisionManifold> collisions;

private Gravity gravity;

private float fixedUpdate;

private int impulseIterations = 6;
public PhysicsSystem(float fixedUpdateDt,Vector2f gravity) {
    this.forceRegistry = new ForceRegistry();
    
   this.rigidbodies = new ArrayList<>();
   
    this.fixedUpdate = fixedUpdateDt;
    
    this.gravity = new Gravity(gravity);
    
    this.rigidbodies1 = new ArrayList<>();
     this.rigidbodies2 = new ArrayList<>();
}

   
public void update(float dt) {
fixedUpdate();   
}
public void fixedUpdate() {
    rigidbodies1.clear();
    rigidbodies2.clear();
    collisions.clear();
    
    int size = rigidbodies.size();
    for(int i = 0; i < size; i++) {
        for(int j=i; j < size; j++) {
            if(i == j) continue;
            CollisionManifold result = new CollisionManifold();
            Rigidbody r1 = rigidbodies.get(i);
            Rigidbody r2 = rigidbodies.get(j);
            Collider c1 = r1.getCollider();
            Collider c2 = r2.getCollider();
            
            if(c1 != null && c2 != null && !(r1.hasInfiniteMass() && r2.hasInfiniteMass())) {
            result = Collisions.findCollisionFeatures(c1,c2);
        }
            if(result != null && result.isColliding()) {
                rigidbodies1.add(r1);
                rigidbodies2.add(r2);
                collisions.add(result);
            }
         }
    }
    
   
    
    forceRegistry.UpdateForces(fixedUpdate);
    
    for(int k = 0; k < impulseIterations; k++) {
      for(int i = 0; i < collisions.size(); i++) {
          int JSize = collisions.get(i).getContactpoint().size();
          for(int j=0; j<JSize;j++) {
              Rigidbody r1 = rigidbodies.get(i);
              Rigidbody r2 = rigidbodies.get(i);
              applyImpulse(r1,r2,collisions.get(i));
          }
      }
    }
    
    for (int i =0; i < rigidbodies.size(); i++) {
     rigidbodies.get(i).PhysicsUpdate(fixedUpdate);
    }
    
}

private void applyImpulse(Rigidbody a,Rigidbody b,CollisionManifold m) {
 float invMass1 = a.getInverseMass();
 float invMass2 = b.getInverseMass();
 float InverseMasssum = invMass1 + invMass2;
 if(InverseMasssum == 0f) {
     return;
 }
  Vector2f relativeVel = new Vector2f(b.getVelocity()).sub(a.getVelocity());
  Vector2f relativenormal = new Vector2f(m.getNormal());
  relativenormal.normalize();
  if(relativeVel.dot(relativenormal) > 0.0f) {
      return;
  }
   float e = Math.min(a.getCor(),b.getCor());
   float numerator = (-(1.0f + e * relativeVel.dot(relativenormal)));
   float j = numerator / InverseMasssum;
   if(m.getContactpoint().size() > 0 && j != 0.0f) {
       j /= (float) m.getContactpoint().size();
   }
   Vector2f impulse = new Vector2f(relativenormal).mul(j);
   a.setVelocity(new Vector2f(a.getVelocity()).add(new Vector2f(impulse).mul(invMass1).mul(-1f)));
    b.setVelocity(new Vector2f(b.getVelocity()).add(new Vector2f(impulse).mul(invMass2).mul(1f)));
 }

public void addrigidbody(Rigidbody body,boolean AddGravity) {
    this.rigidbodies.add(body);
    if(AddGravity){
    this.forceRegistry.add(body,gravity);
    }
}
}
