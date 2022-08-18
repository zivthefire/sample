/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import org.joml.Vector2f;
import renderer.Texture;

/**
 *
 * @author ZIVBD
 */
public class Sprite {
private Texture texture;
private Vector2f[] texcords;
 public Sprite(Texture tex) {
   this.texture = tex;
    Vector2f[] texCoords = {
            new Vector2f(1,0),
            new Vector2f(1,1),
            new Vector2f(0,1),
            new Vector2f(0,0)
        };
    this.texcords = texCoords;
 }
 
public Sprite(Texture tex,Vector2f[] texCoords) {
     this.texture = tex;
     this.texcords = texCoords;
 }


    public Texture getTexture() {
        return texture;
    }

    
    public Vector2f[] getTexcords() {
        return texcords;
    }
 
 
 
}
