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
public class ForceRegistration {
public Forcegenerator fg = null;
public Rigidbody rb = null;
public ForceRegistration(Forcegenerator fg, Rigidbody rb) {
    this.fg = fg;
    this.rb = rb; 
}
@Override
public boolean equals(Object other) {
    if(other == null) {
        return false;
    }
    if(other.getClass() != ForceRegistration.class) {
return false;        
    }
    ForceRegistration fr = (ForceRegistration)other;
    return fr.rb == this.rb && fr.fg == this.fg;
    
}
}
