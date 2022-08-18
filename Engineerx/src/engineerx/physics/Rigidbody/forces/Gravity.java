/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Rigidbody.forces;

import engineerx.physics.Rigidbody.Rigidbody;
import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class Gravity implements Forcegenerator {
    private Vector2f Gravity;
public Gravity(Vector2f force) {
this.Gravity = new Vector2f(force);
}

    @Override
    public void updateforce(Rigidbody body, float dt) {
     body.AddForce(new Vector2f(Gravity).mul(body.getMass()));
     
    }
    
    
}
