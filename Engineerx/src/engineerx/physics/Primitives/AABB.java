/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Primitives;

import engineerx.physics.Rigidbody.Rigidbody;
import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class AABB {
 private Vector2f center = new Vector2f();
 private Vector2f size = new Vector2f();
 private Vector2f halfSize = new Vector2f();
 private Rigidbody Rigidbody = null;
 public AABB(Vector2f min,Vector2f max) {
    this.size = new Vector2f(max).sub(min);
    this.halfSize = new Vector2f(size).mul(0.5f);
 }
 public AABB() {
  this.halfSize = new Vector2f(size).mul(0.5f);
}
 public Vector2f getMin() {
   return new Vector2f(this.Rigidbody.getPosition().sub(this.halfSize)); 
 }
 public Vector2f getMax() {
    return new Vector2f(this.Rigidbody.getPosition().add(this.halfSize));
 }
public void Setrigidbody(Rigidbody rb) {
    this.Rigidbody = rb;
}
 public void Setsize(Vector2f size) {
     this.size.set(size);
     this.halfSize.set(size.x /2.0f,size.y/2.0f);
 }   
}
