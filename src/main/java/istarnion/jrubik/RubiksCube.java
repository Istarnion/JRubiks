package istarnion.jrubik;

import com.jogamp.opengl.GL2;

public class RubiksCube {
    
    public Cubie[] cubies;
    
    public float spacing = 1.1f;
    
    public RubiksCube() {
        cubies = new Cubie[27];
        
        for(int i=0; i<cubies.length; i++) {
            cubies[i] = new Cubie();
        }
    }
    
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        
        for(float x=-1; x<=1; ++x) {
            for(float y=-1; y<=1; ++y) {
                for(float z=-1; z<=1; ++z) {
                    int index = toIndex((int)x+1, (int)y+1, (int)z+1);
                    
                    gl.glPushMatrix();
                    
                    gl.glTranslatef(x*spacing, y*spacing, z*spacing);
                    cubies[index].draw(gl);
                    
                    gl.glPopMatrix();
                }
            }
        }
        
        gl.glPopMatrix();
    }
    
    public int toIndex(int x, int y, int z) {
        return x*9 + y*3 + z;
    }
    
    public void rotate(Side side, boolean clockwise) {
        System.out.println("Rotating "+side+" side "+(clockwise?"clockwise":"counter-clockwise"));
    }
    
    public enum Side {
        RIGHT,
        FRONT,
        BACK,
        LEFT,
        UP,
        DOWN
    }
}
