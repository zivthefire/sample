package jade;

import Components.Sprite;
import Components.SpriteRenderer;
import Components.Spritesheet;
import org.joml.Vector2f;
import org.joml.Vector4f;
import engineerx.Util.AssetPool;

import static org.lwjgl.glfw.GLFW.*;

public class LevelEditorScene extends Scene {
private GameObject obj1;
private GameObject obj2;
private Spritesheet Blocks;
private Spritesheet characterwalkinganim;
    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f(-250, 0));
        characterwalkinganim = AssetPool.getSpriteSheet("src\\Assets\\pictures\\Charactersprite.png");
       Blocks = AssetPool.getSpriteSheet("src\\Assets\\pictures\\Blocksspritesheet.png");
        obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)),1);
        obj1.addComponent(new SpriteRenderer(characterwalkinganim.getSprite(0)));
        this.addGameobjecttoscene(obj1);
       
       obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)),0);
        obj2.addComponent(new SpriteRenderer(Blocks.getSprite(0)));
        this.addGameobjecttoscene(obj2);
        GameObject obj3 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)),0);
        obj3.addComponent(new SpriteRenderer(Blocks.getSprite(1)));
        this.addGameobjecttoscene(obj3);
        
    }

    private void loadResources() {
        AssetPool.getShader("src\\Assets\\shaders\\default.glsl");
        AssetPool.addSpriteSheet("src\\Assets\\pictures\\Charactersprite.png", 
        new Spritesheet(AssetPool.getTexture("src\\Assets\\pictures\\Charactersprite.png"),24,50,7,0));
        AssetPool.addSpriteSheet("src\\Assets\\pictures\\Blocksspritesheet.png", new Spritesheet(AssetPool.getTexture("src\\Assets\\pictures\\Blocksspritesheet.png"),16,16,2,0));
    }
     private int spriteIndex = 0;
     private float spriteFliptime = 0.1f;
     private float spriteFliptimeleft = 0.0f;
    @Override
    public void update(float dt) {
        spriteFliptimeleft -=dt;
     if(spriteFliptimeleft <= 0) {
         spriteFliptimeleft = spriteFliptime;
         spriteIndex++;
         if(spriteIndex > 6) {
             spriteIndex = 0;
         }
        obj1.getComponent(SpriteRenderer.class).setSprite(characterwalkinganim.getSprite(spriteIndex));
     }
        

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}