package engineerx.Util;

import Components.Spritesheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
 private static Map<String,Spritesheet> Spritesheets = new HashMap<>();
    public static Shader getShader(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.shaders.containsKey(file.getAbsolutePath())) {
            return AssetPool.shaders.get(file.getAbsolutePath());
        } else {
            Shader shader = new Shader(resourceName);
            shader.compile();
            AssetPool.shaders.put(file.getAbsolutePath(), shader);
            return shader;
        }
    }

    public static Texture getTexture(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetPool.textures.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture(resourceName);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }
    
    public static void addSpriteSheet(String resourcename,Spritesheet spritesheet) {
        File file = new File(resourcename);
        if(!AssetPool.Spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.Spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }
  public static Spritesheet getSpriteSheet(String resourcename) {
     File file = new File(resourcename);
        if(!AssetPool.Spritesheets.containsKey(file.getAbsolutePath())) {
            assert false : "Error: Tried to access spritesheet" + resourcename + "And it has not been added to AssetPool"; 
        }  
        return AssetPool.Spritesheets.getOrDefault(file.getAbsolutePath(), null);
  }  
}