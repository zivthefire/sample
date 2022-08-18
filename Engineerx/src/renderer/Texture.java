/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

/**
 *
 * @author ZIVBD
 */
public class Texture {
    private String filepath;
    private int texID;
    private int width,height;
    
  public Texture(String filepath) {
  this.filepath = filepath;
  texID = glGenTextures();
  glBindTexture(GL_TEXTURE_2D,texID);
  
  glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);
  glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);
  glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
    
    IntBuffer width = BufferUtils.createIntBuffer(1);
     IntBuffer height = BufferUtils.createIntBuffer(1);
      IntBuffer channels = BufferUtils.createIntBuffer(1);
      ByteBuffer image = stbi_load(filepath,width,height,channels,0);
      if(image != null) {
          this.width = width.get(0);
          this.height = height.get(0);
          glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,width.get(0),height.get(0),0,GL_RGBA,GL_UNSIGNED_BYTE,image);
      } else {
          assert false: "Error: Could Not Load Texture" +filepath+".";
      }
      stbi_image_free(image);
  }
  
  public void bind() {
      glBindTexture(GL_TEXTURE_2D,texID);
  }
  public void unbind() {
      glBindTexture(GL_TEXTURE_2D,0);
  }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
  
}
