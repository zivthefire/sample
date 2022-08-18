/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Rigidbody;

import engineerx.physics.Primitives.Collider;
import java.awt.Component;
import extrajade.Transform;
import java.util.HashSet;
import java.util.Set;

import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class Rigidbody extends Component{
private Transform rawTransform;    

    public void setRawTransform(Transform rawTransform) {
        this.rawTransform = rawTransform;
        this.Position.set(rawTransform.position);
        
    }
    
 private Vector2f Position = new Vector2f();
private float rotation = 0.0f;
private Collider collider;
private float mass = 0.0f;
private float inverseMass = 0.0f;private Vector2f linearVelocity =  new Vector2f();

private float angularVelocity = 0.0f;
private float linearDamping = 0.0f;
private float angularDamping = 0.0f;

private boolean fixedrotation = false;

private Vector2f forceAccum = new Vector2f();

private float cor = 1.0f;
    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
        if(this.mass!=0.0f) {
            this.inverseMass = 1.0f / this.mass;
        }
        }
    public void PhysicsUpdate(float dt) {
       if(this.mass == 0.0f) return;
           Vector2f acceleration = new Vector2f(forceAccum);
           linearVelocity.add(acceleration.mul(dt));
           this.Position.add(new Vector2f(linearVelocity).mul(dt));
           syncCollisionTransforms();
           clearAccumulators();
       
    }
    public void syncCollisionTransforms() {
    if(rawTransform != null) {
        rawTransform.position.set(this.Position);
    } 
        
}
public void clearAccumulators() {
    this.forceAccum.zero();
}
 



    public Vector2f getPosition() {
        return Position;
    }

    public void setTransform(Vector2f position,float rotation) {
        this.Position.set(position);
        this.rotation = rotation;
    }
     public void setTransform(Vector2f position) {
        this.Position.set(position);
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }
public void AddForce(Vector2f force) {
this.forceAccum.add(force);
}

   
    public Vector2f getVelocity() {
        return linearVelocity;
    }

    public void setVelocity(Vector2f Velocity) {
        this.linearVelocity = Velocity;
    }

    public float getInverseMass() {
        return inverseMass;
    }

    public float getCor() {
        return cor;
    }

    public void setCor(float cor) {
        this.cor = cor;
    }

    public Collider getCollider() {
      return this.collider;
    }

    public boolean hasInfiniteMass() {
       return this.mass == 0.0f;
    }
 public void SetCollider(Collider collider) {
     this.collider = collider;
 }

    
    
}
