/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

/**
 *
 * @author ZIVBD
 */
public abstract class Component {
    public GameObject gameObject = null;

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
   
    public abstract void update(float dt);
    public void start() {
        
    }
}
