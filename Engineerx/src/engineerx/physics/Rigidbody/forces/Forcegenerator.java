/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Rigidbody.forces;

import engineerx.physics.Rigidbody.Rigidbody;

/**
 *
 * @author ZIVBD
 */
public interface Forcegenerator {
   void updateforce(Rigidbody body,float dt);
       
   
}
