/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Rigidbody.forces;

import engineerx.physics.Rigidbody.Rigidbody;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZIVBD
 */
public class ForceRegistry {
private List<ForceRegistration> registry;

public ForceRegistry() {
    this.registry = new ArrayList<>();
}
public void add(Rigidbody rb,Forcegenerator fg) {
 ForceRegistration fr = new ForceRegistration(fg,rb);
 registry.add(fr);
}
public void remove(Rigidbody rb,Forcegenerator fg) {
 ForceRegistration fr = new ForceRegistration(fg,rb);
 registry.remove(fr);   
}
public void clear() {
    registry.clear();
}
public void UpdateForces(float dt) {
    for (ForceRegistration fr : registry) {
        fr.fg.updateforce(fr.rb,dt);
    
        
    }
} 
   public void zeroForces() {
       for (ForceRegistration fr : registry) {
        //fr.rb.zeroForces();
   }  
   }

   
}
    

    