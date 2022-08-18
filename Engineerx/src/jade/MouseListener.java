/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 *
 * @author Zivthefire
 */
public class MouseListener {
   private static MouseListener instance;
   private double scrollx,scrolly;
   private double xpos,ypos,lasty,lastx;
   private boolean mousebuttonpressed[] = new boolean[3];
   private boolean dragging;
    private MouseListener() {
        this.scrollx = 0.0;
        this.scrolly = 0.0;
        this.xpos = 0.0;
        this.ypos = 0.0;
        this.lastx = 0.0;
        this.lasty = 0.0;
        
        
    }
    public static MouseListener get() {
        if(instance == null) {
            MouseListener.instance = new MouseListener();
            
        }
       return MouseListener.instance;
    }
    public static void mousePosCallback(long window,double Xpos,double Ypos) {
        get().lastx = get().xpos;
        get().lasty= get().ypos;
        get().xpos = Xpos;
        get().ypos = Ypos;
        get().dragging = get().mousebuttonpressed[0] || get().mousebuttonpressed[1] || get().mousebuttonpressed[2];
        
    
    
        
    }
    public static void mouseButtonCallback(long window,int button,int action,int mods) {
        if(action == GLFW_PRESS) {
            if(button < get().mousebuttonpressed.length) {
        get().mousebuttonpressed[button] = true;
        get().dragging = true;
            }
        } else if(action == GLFW_RELEASE) {
            if(button < get().mousebuttonpressed.length) {
            get().mousebuttonpressed[button] = false;
            get().dragging = false;
            }
        }
    }
    public static void mouseScrollCallBack(long window,double xoffset,double yoffset) {
        get().scrollx = xoffset;
        get().scrolly = yoffset;
        
    }
    public static void endFrame() {
        get().scrollx = 0;
        get().scrolly = 0;
        get().lastx = get().xpos; 
        get().lasty = get().ypos;
                
    }
   public static float getX() {
       return (float)get().xpos;
   }
   public static float getY() {
       return (float)get().ypos;
   }
    public static float getDX() {
       return (float)(get().lastx - get().xpos);
   }
     public static float getDY() {
       return (float)(get().lasty - get().ypos);
   }
     public static float getScrollX() {
         return (float) get().scrollx;
     }
     public static float getScrollY() {
         return (float) get().scrolly;
     }
     public static boolean isDragging() {
         return get().dragging;
     }
     public static boolean mouseButtonDown(int button) {
         if(button < get().mousebuttonpressed.length) {
         return get().mousebuttonpressed[button];
         } else {
             return false;
         }
   
    
     }
}
