/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.util.ArrayList;
import java.util.List;
import renderer.Renderer;

/**
 *
 * @author ZIVBD
 */
public abstract class Scene {
    protected Camera camera;
    private boolean isrunning = false;
  protected Renderer  renderer = new Renderer();
    protected List<GameObject> gameObjects = new ArrayList<>();
    public Scene() {
    }
    
    public void init() {

} 
    public abstract void update(float dt);
    public void start() {
        for(GameObject go : gameObjects) {
            go.start();
            this.isrunning = true;
            this.renderer.add(go);
        }
    }     
    public void addGameobjecttoscene(GameObject go) {
       if(!isrunning) { 
           gameObjects.add(go);
       } else {
           gameObjects.add(go);
           go.start();
           this.renderer.add(go);
       }
    }
    public Camera camera() {
        return this.camera;
    }

   
   
}


