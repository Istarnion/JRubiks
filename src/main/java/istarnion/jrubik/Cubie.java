package istarnion.jrubik;

import com.jogamp.opengl.GL2;
import java.util.Arrays;

/**
 *
 * @author istarnion
 */
public class Cubie {
    
    public static final float[][] SIDE_COLORS = {
        {0.77f, 0.12f, 0.23f},
        {0.00f, 0.62f, 0.38f},
        {0.00f, 0.32f, 0.73f},
        {1.00f, 0.35f, 0.00f},
        {1.00f, 0.84f, 0.00f},
        {1.00f, 1.00f, 1.00f}
    };
    
    public static final int
            RED     = 0,
            GREEN   = 1,
            BLUE    = 2,
            ORANGE  = 3,
            YELLOW  = 4,
            WHITE   = 5;
    
    public float[]
            right,
            front,
            back,
            left,
            down,
            up;
        
    public Cubie() {
        right = SIDE_COLORS[RED];
        front = SIDE_COLORS[GREEN];
        back  = SIDE_COLORS[BLUE];
        left  = SIDE_COLORS[ORANGE];
        down  = SIDE_COLORS[YELLOW];
        up    = SIDE_COLORS[WHITE];
    }
    
    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);
        {
            gl.glColor3fv(right, 0);
            gl.glVertex3f(0.5f,  0.5f,  0.5f);
            gl.glVertex3f(0.5f, -0.5f,  0.5f);
            gl.glVertex3f(0.5f, -0.5f, -0.5f);
            gl.glVertex3f(0.5f,  0.5f, -0.5f);
            
            gl.glColor3fv(front, 0);
            gl.glVertex3f( 0.5f,  0.5f, 0.5f);
            gl.glVertex3f( 0.5f, -0.5f, 0.5f);
            gl.glVertex3f(-0.5f, -0.5f, 0.5f);
            gl.glVertex3f(-0.5f,  0.5f, 0.5f);
            
            gl.glColor3fv(back, 0);
            gl.glVertex3f( 0.5f,  0.5f, -0.5f);
            gl.glVertex3f( 0.5f, -0.5f, -0.5f);
            gl.glVertex3f(-0.5f, -0.5f, -0.5f);
            gl.glVertex3f(-0.5f,  0.5f, -0.5f);
            
            gl.glColor3fv(left, 0);
            gl.glVertex3f(-0.5f,  0.5f,  0.5f);
            gl.glVertex3f(-0.5f, -0.5f,  0.5f);
            gl.glVertex3f(-0.5f, -0.5f, -0.5f);
            gl.glVertex3f(-0.5f,  0.5f, -0.5f);
            
            gl.glColor3fv(down, 0);
            gl.glVertex3f( 0.5f, -0.5f,  0.5f);
            gl.glVertex3f(-0.5f, -0.5f,  0.5f);
            gl.glVertex3f(-0.5f, -0.5f, -0.5f);
            gl.glVertex3f( 0.5f, -0.5f, -0.5f);
            
            gl.glColor3fv(up, 0);
            gl.glVertex3f( 0.5f, 0.5f,  0.5f);
            gl.glVertex3f(-0.5f, 0.5f,  0.5f);
            gl.glVertex3f(-0.5f, 0.5f, -0.5f);
            gl.glVertex3f( 0.5f, 0.5f, -0.5f);
        }
        gl.glEnd();
    }
    
    public void copy(Cubie other) {
        right = Arrays.copyOf(other.right, 3);
        front = Arrays.copyOf(other.front, 3);
        left  = Arrays.copyOf(other.left , 3);
        back  = Arrays.copyOf(other.back , 3);
        up    = Arrays.copyOf(other.up   , 3);
        down  = Arrays.copyOf(other.down , 3);
    }
    
    // Note that 'clockwise' here is seen from front, top, or right face,
    // corresponding to roll, yaw and pitch.
    public void swapAndRotate(Cubie other, Rotation rot, boolean clockwise) {
        switch(rot) {
            case YAW:   // up and down are unchanged
                if(clockwise) {
                    right = Arrays.copyOf(other.back , 3);
                    front = Arrays.copyOf(other.right, 3);
                    left  = Arrays.copyOf(other.front, 3);
                    back  = Arrays.copyOf(other.left , 3);
                    up    = Arrays.copyOf(other.up   , 3);
                    down  = Arrays.copyOf(other.down , 3);
                }
                else {
                    right = Arrays.copyOf(other.front, 3);
                    front = Arrays.copyOf(other.left , 3);
                    left  = Arrays.copyOf(other.back , 3);
                    back  = Arrays.copyOf(other.right, 3);
                    up    = Arrays.copyOf(other.up   , 3);
                    down  = Arrays.copyOf(other.down , 3);
                }
                break;
            case PITCH: // right and left are unchanged
                if(clockwise) {
                    right = Arrays.copyOf(other.right, 3);
                    front = Arrays.copyOf(other.down , 3);
                    left  = Arrays.copyOf(other.left , 3);
                    back  = Arrays.copyOf(other.up   , 3);
                    up    = Arrays.copyOf(other.front, 3);
                    down  = Arrays.copyOf(other.back , 3);
                }
                else {
                    right = Arrays.copyOf(other.right, 3);
                    front = Arrays.copyOf(other.up   , 3);
                    left  = Arrays.copyOf(other.left , 3);
                    back  = Arrays.copyOf(other.down , 3);
                    up    = Arrays.copyOf(other.back , 3);
                    down  = Arrays.copyOf(other.front, 3);
                }
                break;
            case ROLL:  // front and back are unchanged
                if(clockwise) {
                    right = Arrays.copyOf(other.up   , 3);
                    front = Arrays.copyOf(other.front, 3);
                    left  = Arrays.copyOf(other.down , 3);
                    back  = Arrays.copyOf(other.back , 3);
                    up    = Arrays.copyOf(other.left , 3);
                    down  = Arrays.copyOf(other.right, 3);
                }
                else {
                    right = Arrays.copyOf(other.down , 3);
                    front = Arrays.copyOf(other.front, 3);
                    left  = Arrays.copyOf(other.up   , 3);
                    back  = Arrays.copyOf(other.back , 3);
                    up    = Arrays.copyOf(other.right, 3);
                    down  = Arrays.copyOf(other.left , 3);
                }
                break;
        }
    }
    
    public enum Rotation {
        YAW,
        PITCH,
        ROLL
    }
}
