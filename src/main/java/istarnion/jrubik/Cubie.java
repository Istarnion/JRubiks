package istarnion.jrubik;

import com.jogamp.opengl.GL2;

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
}
