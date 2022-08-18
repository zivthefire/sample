/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineerx.physics.Rigidbody;

import engineerx.Render.Line;
import engineerx.Util.JMath;
import engineerx.physics.Primitives.AABB;
import engineerx.physics.Primitives.Box;
import engineerx.physics.Primitives.Circle;
import engineerx.physics.Primitives.Raycast;
import engineerx.physics.Primitives.Raycastresult;
import static engineerx.physics.Rigidbody.Intersectiondetector.Raycast;
import org.joml.Vector2f;

/**
 *
 * @author ZIVBD
 */
public class Intersectiondetector {
 public static boolean Pointonline(Vector2f point,Line line) {
 float dy = line.Getend().y-line.Getstart().y;
  float dx = line.Getend().x-line.Getstart().x;
  if(dx==0) {
   return JMath.compare(point.x,line.Getstart().x);
  }
  float m = dy/dx;
  float b = line.Getend().y - (m*line.Getend().x);
  return point.y == m*point.x + b;
 }
 public static boolean PointinCircle(Vector2f point,Circle circle) {
  Vector2f Circlecenter = circle.getCenter();
  Vector2f centertopoint = new Vector2f(point).sub(Circlecenter);
  return centertopoint.lengthSquared() <= circle.Getradius() * circle.Getradius();
 }
 public static boolean pointInAABB(Vector2f point,AABB box) {
  Vector2f min = box.getMin();
  Vector2f max = box.getMax();
  return point.x <= max.x && min.x <= point.x &&
          point.y <= max.y && min.y <= point.y;
 }
 public static boolean pointinBox(Vector2f point,Box box) {
     Vector2f pointlocalBoxSpace = new Vector2f(point);
     JMath.rotate(pointlocalBoxSpace,box.getRigidbody().getRotation(),box.getRigidbody().getPosition());
      Vector2f min = box.getlMin();
  Vector2f max = box.getlMax();
  return pointlocalBoxSpace.x <= max.x && min.x <= pointlocalBoxSpace.x &&
          pointlocalBoxSpace.y <= max.y && min.y <= pointlocalBoxSpace.y;
 }
 public static boolean lineAndCircle(Line line , Circle circle) {
     if(PointinCircle(line.Getstart(),circle) || PointinCircle(line.Getend(), circle)) {
         return true;
     }
     Vector2f ab = new Vector2f(line.Getend().sub(line.Getstart()));
     Vector2f Circlecenter = circle.getCenter();
             Vector2f Centertolinestart = new Vector2f(Circlecenter).sub(line.Getstart());
             float t = Centertolinestart.dot(ab) / ab.dot(ab);
             if(t < 0.0f || t > 1.0f) {
                 return false;
             }
             Vector2f Closetpoint = new Vector2f(line.Getstart()).add(ab.mul(t));
             return PointinCircle(Closetpoint,circle);
 }
public static boolean  LineAndAABB(Line line,AABB box) {
    if (pointInAABB(line.Getstart(), box) || pointInAABB(line.Getend(),box)) {
    return true;    
    
}
    Vector2f UnitVector = new Vector2f(line.Getend()).sub(line.Getstart());
    UnitVector.normalize();
    UnitVector.x = (UnitVector.x!=0) ? 1.0f / UnitVector.x :0f;
    Vector2f min = box.getMin();
    min.sub(line.Getstart()).mul(UnitVector);
    Vector2f max = box.getMax();
    max.sub(line.Getstart()).mul(UnitVector);
    float tmin = Math.max(Math.min(min.x,max.x),Math.min(min.y,max.y)); 
    float tmax = Math.min(Math.max(min.x,max.x),Math.max(min.y,max.y));
    if(tmax < 0 || tmin > tmax) {
        return false;
    }
    float t =(tmin < 0f) ? tmax : tmin;
    return t > 0 && t*t < line.LengthSquared();
}
public static boolean lineandBox(Line line,Box box) {
float theta = -box.getRigidbody().getRotation();
Vector2f center = box.getRigidbody().getPosition();
Vector2f localstart = new Vector2f(line.Getstart());
Vector2f localend = new Vector2f(line.Getend());
JMath.rotate(localstart, theta,center);
JMath.rotate(localend, theta,center);
Line localline = new Line(localstart,localend);
AABB aabb = new AABB(box.getlMin(),box.getlMax());
return LineAndAABB(localline,aabb);
}  
public static boolean Raycast(Circle circle,Raycast raycast,Raycastresult result) {
 Raycastresult.reset(result);
 Vector2f origintoCircle = new Vector2f(circle.getCenter().sub(raycast.getOrigin()));
 float radiusSquared = circle.Getradius() * circle.Getradius();
 float originTocirclelengthSquared = origintoCircle.lengthSquared();
 float a = origintoCircle.dot(raycast.getDirection());
 float Bsq = originTocirclelengthSquared - (a*a);
 if(radiusSquared - Bsq <0.0f) {
  return false;   
 }
 float f = (float)Math.sqrt(radiusSquared-Bsq);
 float t = 0;
 if(originTocirclelengthSquared < radiusSquared ) {
  
     t = a + f;
 } else {
 t = a - f;
 }
 if (result != null) {
     Vector2f point = new Vector2f(raycast.getOrigin()).add(new Vector2f(raycast.getDirection().mul(t)));
     Vector2f normal = new Vector2f(point).sub(circle.getCenter());
     result.intit(point,normal,t,true);
 }
 return true;
}

   public static boolean raycast(AABB box,Raycast raycast,Raycastresult result) {
       Raycastresult.reset(result);
    Vector2f UnitVector = raycast.getDirection();
    UnitVector.normalize();
    UnitVector.x = (UnitVector.x!=0) ? 1.0f / UnitVector.x :0f;
    Vector2f min = box.getMin();
    min.sub(raycast.getOrigin()).mul(UnitVector);
    Vector2f max = box.getMax();
    max.sub(raycast.getOrigin()).mul(UnitVector);
    float tmin = Math.max(Math.min(min.x,max.x),Math.min(min.y,max.y)); 
    float tmax = Math.min(Math.max(min.x,max.x),Math.max(min.y,max.y));
    if(tmax < 0 || tmin > tmax) {
        return false;
    }
    float t =(tmin < 0f) ? tmax : tmin;
    boolean hit = t > 0f; // && t * t <
    if (!hit) {
return false;
    }
    if(result != null) {
        Vector2f point = new Vector2f(raycast.getOrigin()).add(new Vector2f(raycast.getDirection().mul(t)));
        Vector2f normal = new Vector2f(raycast.getOrigin()).sub(point);
        normal.normalize();
        result.intit(point, normal, t, true);
    }
    return true;
}   
public static boolean raycast(Box box,Raycast raycast,Raycastresult result) {
    Raycastresult.reset(result);
    Vector2f size = box.getHalfSize();
    Vector2f xAxis = new Vector2f(1,0);
    Vector2f yAxis = new Vector2f(0,1);
    JMath.rotate(xAxis,-box.getRigidbody().getRotation(),new Vector2f(0,0));
    JMath.rotate(yAxis,-box.getRigidbody().getRotation(),new Vector2f(0,0));
    Vector2f p = new Vector2f(box.getRigidbody().getPosition()).sub(raycast.getOrigin());
    Vector2f f = new Vector2f(
    yAxis.dot(raycast.getDirection()),
    xAxis.dot(raycast.getDirection())
    );
    Vector2f e = new Vector2f(
            xAxis.dot(p),
            yAxis.dot(p)
    );
    float[] tArr = {0,0,0,0};
    for(int i=0;i < 2; i++) {
        if(JMath.compare(f.get(i),0)) {
            if(-e.get(i)-size.get(i) > 0 || -e.get(i) + size.get(i)<0) {
                return false;
                
            }
            f.setComponent(i,0.00001f);
            
        }
        tArr[i*2+0] = (e.get(i) + size.get(i)) / f.get(i);
         tArr[i*2+1] = (e.get(i) - size.get(i)) / f.get(i);
    }
    float tmin = Math.max(Math.min(tArr[0],tArr[1]),Math.min(tArr[2],tArr[3]));
    float tmax = Math.min(Math.max(tArr[0],tArr[1]),Math.max(tArr[2],tArr[3]));
    float t =(tmin < 0f) ? tmax : tmin;
    boolean hit = t > 0f; // && t * t <
    if (!hit) {
return false;
    }
    if(result != null) {
        Vector2f point = new Vector2f(raycast.getOrigin()).add(new Vector2f(raycast.getDirection().mul(t)));
        Vector2f normal = new Vector2f(raycast.getOrigin()).sub(point);
        normal.normalize();
        result.intit(point, normal, t, true);
    }
     return true;
}
public static boolean circleAndLine(Circle circle,Line line) {
    return lineAndCircle(line,circle);
}
public static boolean CircleandCircle(Circle c1,Circle c2) {
    Vector2f vecBetweenCenters = new Vector2f(c1.getCenter()).sub(c2.getCenter());
    float radiisum = c1.Getradius() + c2.Getradius();
    return vecBetweenCenters.lengthSquared() <= radiisum * radiisum;
}
public static boolean circleandAABB(Circle circle,AABB box) {
 Vector2f min = box.getMin();
 Vector2f max = box.getMax();
 Vector2f closetpointtocircle = new Vector2f(circle.getCenter());
 if(closetpointtocircle.x <min.x) {
     closetpointtocircle.x = min.x;
     
 }
 else if(closetpointtocircle.x > max.x) {
     closetpointtocircle.x = max.x;
 }
  if(closetpointtocircle.y <min.y) {
     closetpointtocircle.y = min.y;
     
 }
 else if(closetpointtocircle.y > max.y) {
     closetpointtocircle.y = max.y;
 }
  Vector2f Circletobox = new Vector2f(circle.getCenter()).sub(closetpointtocircle);
  return Circletobox.lengthSquared() <= circle.Getradius() * circle.Getradius();
}
public static boolean circleandbox(Circle circle,Box box) {
    Vector2f min = new Vector2f();
    Vector2f max = new Vector2f(box.getHalfSize()).mul(2.0f);
    Vector2f r = new Vector2f(circle.getCenter()).sub(box.getRigidbody().getPosition());
    JMath.rotate(r,-box.getRigidbody().getRotation(),new Vector2f(box.getHalfSize()));
    Vector2f localcirclepose = new Vector2f(r).add(box.getHalfSize());
    Vector2f closetpointtocircle = new Vector2f(localcirclepose);
 if(closetpointtocircle.x <min.x) {
     closetpointtocircle.x = min.x;
     
 }
 else if(closetpointtocircle.x > max.x) {
     closetpointtocircle.x = max.x;
 }
  if(closetpointtocircle.y <min.y) {
     closetpointtocircle.y = min.y;
     
 }
 else if(closetpointtocircle.y > max.y) {
     closetpointtocircle.y = max.y;
 }
  Vector2f Circletobox = new Vector2f(localcirclepose).sub(closetpointtocircle);
  return Circletobox.lengthSquared() <= circle.Getradius() * circle.Getradius();
}
public static boolean AABBandCircle(Circle circle,AABB box) {
    return circleandAABB(circle,box);
}
public static boolean AABBandAABB(AABB b1,AABB b2) {
    Vector2f axestotest[] = {new Vector2f(0,1),new Vector2f(1,0)};
    for (int i=0; i < axestotest.length; i++) {
        if(!overlapOnAxis(b1,b2,axestotest[i])) {
            return false;
        }
    }
    return true;
}
public static boolean AABBandBox(AABB b1,Box b2) {
 Vector2f axesTotest[] = {
     new Vector2f(0,1),
     new Vector2f(1,0),
         new Vector2f(0,1),
         new Vector2f(1,0)
 };   
  JMath.rotate(axesTotest[2],b2.getRigidbody().getRotation(),new Vector2f(0,0));
  JMath.rotate(axesTotest[3],b2.getRigidbody().getRotation(),new Vector2f(0,0));
   for (int i=0; i < axesTotest.length; i++) {
        if(!overlapOnAxis(b1,b2,axesTotest[i])) {
            return false;
        }
    }
    return true;
}
private static boolean overlapOnAxis(AABB b1,AABB b2,Vector2f axis) {
    Vector2f interval1 = getInterval(b1,axis);
    Vector2f interval2 = getInterval(b2,axis);
    return((interval2.x <= interval1.y) && (interval1.x <= interval2.y));
}
private static boolean overlapOnAxis(Box b1,AABB b2,Vector2f axis) {
    Vector2f interval1 = getInterval(b1,axis);
    Vector2f interval2 = getInterval(b2,axis);
    return((interval2.x <= interval1.y) && (interval1.x <= interval2.y));
}
private static boolean overlapOnAxis(AABB b1, Box b2, Vector2f axis) {
    Vector2f interval1 = getInterval(b1,axis);
    Vector2f interval2 = getInterval(b2,axis);
    return((interval2.x <= interval1.y) && (interval1.x <= interval2.y));
}
private static Vector2f getInterval(AABB rect,Vector2f axis) {
    Vector2f result = new Vector2f(0,0);
    Vector2f min = rect.getMin();
    Vector2f max = rect.getMax();
            Vector2f vertices[] = {
                new Vector2f(min.x,min.y),
                new Vector2f(min.x,max.y),
                new Vector2f(max.x,min.y),
                new Vector2f(max.x,max.y)
            };
            result.x = axis.dot(vertices[0]);
            result.y = result.x;
            for(int i =1; i < 4; i++) {
                float projection = axis.dot(vertices[i]);
                if(projection < result.x) {
                    result.x = projection;
                }
                 if(projection > result.y) {
                    result.y = projection;
                }
            }
            return result;
}
private static Vector2f getInterval(Box rect,Vector2f axis) {
    Vector2f result = new Vector2f(0,0);

            Vector2f vertices[] = rect.getVertices();
                
            
            result.x = axis.dot(vertices[0]);
            result.y = result.x;
            for(int i =1; i < 4; i++) {
                float projection = axis.dot(vertices[i]);
                if(projection < result.x) {
                    result.x = projection;
                }
                 if(projection > result.y) {
                    result.y = projection;
                }
            }
            return result;
}
}