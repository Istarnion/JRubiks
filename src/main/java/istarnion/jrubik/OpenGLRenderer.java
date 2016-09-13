package istarnion.jrubik;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import com.jogamp.opengl.glu.GLU;

public class OpenGLRenderer extends GLCanvas implements GLEventListener {

    GLU glu = new GLU();
    
    public RubiksCube rubikscube;

    OpenGLRenderer() {
        super();
        addGLEventListener(this);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        System.out.println("init()");
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.18f, 0.18f, 0.18f, 1.0f);

        gl.glEnable(GL2.GL_DEPTH_TEST);
        
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, 1, 0.2, 100.0);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(
                0, 0, 10,   // Eye pos
                0, 0, 0,    // Target pos
                0, 1, 0);   // Upwards direction
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        
        rubikscube.draw(gl);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
}
    
