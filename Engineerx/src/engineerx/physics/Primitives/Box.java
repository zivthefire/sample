/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Primitives;

import engineerx.Util.JMath;
import engineerx.physics.Rigidbody.Rigidbody;
import org.joml.Vector2f;
/**
 *
 * @author ZIVBD
 */
public class Box {
private Vector2f size = new Vector2f(); 
private Vector2f halfSize = new Vector2f();
private Rigidbody Rigidbody = null;
 public Box(Vector2f min,Vector2f max) {
    this.size = new Vector2f(max).sub(min);
    this.halfSize = new Vector2f(size).mul(0.5f);
 }
 public Box() {
  this.halfSize = new Vector2f(size).mul(0.5f);
}
 public Vector2f getlMin() {
   return new Vector2f(this.Rigidbody.getPosition().sub(this.halfSize)); 
 }
 public Vector2f getlMax() {
    return new Vector2f(this.Rigidbody.getPosition().add(this.halfSize));
 }
 public Vector2f[] getVertices() {
     Vector2f min = getlMin();
     Vector2f max = getlMax();
     Vector2f[] vertices = {
      new Vector2f(min.x,min.y),
      new Vector2f(min.x,max.y),
      new Vector2f(max.x,min.y),
      new Vector2f(max.x,max.y)
             
     };
     if(Rigidbody.getRotation() != 0.0f)
     for(Vector2f vert : vertices) {
         JMath.rotate(vert,this.Rigidbody.getRotation(),this.Rigidbody.getPosition());
     }
     return vertices;
 }

    public Vector2f getHalfSize() {
        return halfSize;
    }

    public Rigidbody getRigidbody() {
        return Rigidbody;
    }
public void Setrigidbody(Rigidbody rb) {
    this.Rigidbody = rb;
}
 public void Setsize(Vector2f size) {
     this.size.set(size);
     this.halfSize.set(size.x /2.0f,size.y/2.0f);
 }     
}
