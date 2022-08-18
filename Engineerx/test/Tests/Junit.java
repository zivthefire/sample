/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;


import engineerx.Render.Line;
import engineerx.physics.Rigidbody.Intersectiondetector;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import org.joml.Vector2f;


/**
 *
 * @author ZIVBD
 */
public class Junit {
    private final float Eplison = 0.000001f;
public Junit() {
}
@Test
public void Pointonlineshouldreturntruetest(){
Line line = new Line(new Vector2f(0,0),new Vector2f(12,4));
Vector2f point = new Vector2f(0,0);
assertTrue(Intersectiondetector.Pointonline(point, line));

}
@Test
public void Pointonlineshouldreturntruetesttwo(){
Line line = new Line(new Vector2f(0,0),new Vector2f(12,4));
Vector2f point = new Vector2f(12,4);
assertTrue(Intersectiondetector.Pointonline(point, line));

}
@Test
public void Pointonlineshouldreturntruetestthree(){
Line line = new Line(new Vector2f(0,0),new Vector2f(0,10));
Vector2f point = new Vector2f(0,5);
assertTrue(Intersectiondetector.Pointonline(point, line));

}
}   
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

