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
public class Circle extends Collider{
  private float radius = 1.0f;
  private Rigidbody Body = null;
  public float Getradius() {
      return this.radius;
      
  }
  
    public void setRadius(float r) {
        this.radius = r;
    }
  public Vector2f getCenter() {
      return Body.getPosition();
      
  }
  public void Setrigidbody(Rigidbody rb) {
    this.Body = rb;
}
}
