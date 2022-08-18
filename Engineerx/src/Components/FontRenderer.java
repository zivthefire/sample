/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import jade.Component;

/**
 *
 * @author ZIVBD
 */
public class FontRenderer extends Component{
    
    @Override
    public void start() {
        if(gameObject.getComponent(SpriteRenderer.class) != null) {
            System.out.println("Found font Renderer");
        }
    }

    @Override
    public void update(float dt) {

    }
    
}
