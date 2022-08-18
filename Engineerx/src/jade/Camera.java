/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author ZIVBD
 */
public class Camera {
    private Matrix4f projectionMatrix,viewMatrix;
    public Vector2f position;
    public Camera(Vector2f position) {
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }
    public void adjustProjection() {
     projectionMatrix.identity();
     projectionMatrix.ortho(0.0f,32.0f * 40,0.0f,32.0f * 21.0f,0.0f,100.0f);
    }
    public Matrix4f getViewMatrix() {
        Vector3f cameraFront = new Vector3f(0.0f,0.0f,-1.0f);
        Vector3f Cameraup = new Vector3f(0.0f,1.0f,0.0f);
        this.viewMatrix.identity();
        viewMatrix = viewMatrix.lookAt(new Vector3f(position.x,position.y,20.0f),cameraFront.add(position.x,position.y,0.0f),Cameraup);
        return this.viewMatrix;
    }
    public Matrix4f getProjectionMatrix() {
        return this.projectionMatrix;
    }
}
