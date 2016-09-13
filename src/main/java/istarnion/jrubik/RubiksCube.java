package istarnion.jrubik;

import com.jogamp.opengl.GL2;

public class RubiksCube {
    
    public Cubie[] cubies;
    
    public float spacing = 1.1f;
    
    public float mousex, mousey, mousez;
    
    public RubiksCube() {
        cubies = new Cubie[27];
        
        for(int i=0; i<cubies.length; i++) {
            cubies[i] = new Cubie();
        }
    }
    
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        
        gl.glRotated(1024*Math.atan(mousex/10),  0, 1, 0);
        gl.glRotated(1024*Math.atan(mousey/10), -1, 0, 0);
        
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
    
    // We rotate 2D arrays in place here, using the algorith described in the
    // answer here: http://stackoverflow.com/questions/3488691/how-to-rotate-a-matrix-90-degrees-without-using-any-extra-space
    public void rotate(Side side, boolean clockwise) {
        System.out.println("Rotating "+side+" side "+(clockwise?"clockwise":"counter-clockwise"));
        
        Cubie.Rotation rot;
        boolean localClockwise;
        
        switch(side) {
            case RIGHT: case LEFT:
            {
                rot = Cubie.Rotation.PITCH;
                localClockwise = clockwise && side == Side.RIGHT;
                
                int xindex = side == Side.RIGHT? 2 : 0;
                
                Cubie temp = new Cubie();
                for(int i=0; i<1; i++) {
                    for(int j=0; j<2; j++) {
                        temp.copy(cubies[toIndex(xindex, i, j)]);
                        
                        cubies[toIndex(xindex, i, j)].swapAndRotate(
                                cubies[toIndex(xindex, j, 2-i)], rot, localClockwise);
                        
                        cubies[toIndex(xindex, j, 2-i)].swapAndRotate(
                                cubies[toIndex(xindex, 2-i, 2-j)], rot, localClockwise);
                        
                        cubies[toIndex(xindex, 2-i, 2-j)].swapAndRotate(
                                cubies[toIndex(xindex, 2-j, i)], rot, localClockwise);
                        
                        cubies[toIndex(xindex, 2-j, i)].swapAndRotate(
                                temp, rot, localClockwise);
                    }
                }
            } break;
            case FRONT: case BACK:
            {
                rot = Cubie.Rotation.ROLL;
                localClockwise = clockwise && side == Side.FRONT;
                
                int zindex = side == Side.FRONT? 2 : 0; // Maybe the other way around...
                
                Cubie temp = new Cubie();
                for(int i=0; i<1; i++) {
                    for(int j=0; j<2; j++) {
                        temp.copy(cubies[toIndex(i, j, zindex)]);
                        
                        cubies[toIndex(i, j, zindex)].swapAndRotate(
                                cubies[toIndex(j, 2-i, zindex)], rot, localClockwise);
                        
                        cubies[toIndex(j, 2-i, zindex)].swapAndRotate(
                                cubies[toIndex(2-i, 2-j, zindex)], rot, localClockwise);
                        
                        cubies[toIndex(2-i, 2-j, zindex)].swapAndRotate(
                                cubies[toIndex(2-j, i, zindex)], rot, localClockwise);
                        
                        cubies[toIndex(2-j, i, zindex)].swapAndRotate(
                                temp, rot, localClockwise);
                    }
                }
            } break;
            case UP: case DOWN:
            {
                rot = Cubie.Rotation.YAW;
                localClockwise = clockwise && side == Side.UP;
                
                int yindex = side == Side.UP? 2 : 0;
                
                Cubie temp = new Cubie();
                for(int i=0; i<1; i++) {
                    for(int j=0; j<2; j++) {
                        temp.copy(cubies[toIndex(i, yindex, j)]);
                        
                        cubies[toIndex(i, yindex, j)].swapAndRotate(
                                cubies[toIndex(j, yindex, 2-i)], rot, localClockwise);
                        
                        cubies[toIndex(j, yindex, 2-i)].swapAndRotate(
                                cubies[toIndex(2-i, yindex, 2-j)], rot, localClockwise);
                        
                        cubies[toIndex(2-i, yindex, 2-j)].swapAndRotate(
                                cubies[toIndex(2-j, yindex, i)], rot, localClockwise);
                        
                        cubies[toIndex(2-j, yindex, i)].swapAndRotate(
                                temp, rot, localClockwise);
                    }
                }
            } break;
        }
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
