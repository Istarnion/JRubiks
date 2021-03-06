package istarnion.jrubik;

import com.jogamp.opengl.glu.GLU;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JRubik implements MouseMotionListener {
	
    JFrame window;
    OpenGLRenderer renderer;
    JPanel inputPanel;
    JTextField inputField;

    RubiksCube cube;
    
    public JRubik() {
        window = new JFrame("JRubik");
        renderer = new OpenGLRenderer();
        inputPanel = new JPanel();
        inputField = new JTextField();
        cube = new RubiksCube();
        
        renderer.rubikscube = cube;
        renderer.setPreferredSize(new Dimension(512, 512));
        renderer.addMouseMotionListener(this);
        
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        
        window.add(renderer, BorderLayout.CENTER);
        window.add(inputPanel, BorderLayout.SOUTH);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                if(Pattern.matches("[rRfFlLbBuUdD]{1}[iI]?", input)) {
                    boolean clockwise = input.length() == 1;
                    String side = input.substring(0, 1);
                    
                    if(side.equalsIgnoreCase("r")) {
                        cube.rotate(RubiksCube.Side.RIGHT, clockwise);
                    }
                    else if(side.equalsIgnoreCase("f")) {
                        cube.rotate(RubiksCube.Side.FRONT, clockwise);
                    }
                    else if(side.equalsIgnoreCase("l")) {
                        cube.rotate(RubiksCube.Side.LEFT, clockwise);
                    }
                    else if(side.equalsIgnoreCase("b")) {
                        cube.rotate(RubiksCube.Side.BACK, clockwise);
                    }
                    else if(side.equalsIgnoreCase("u")) {
                        cube.rotate(RubiksCube.Side.UP, clockwise);
                    }
                    else if(side.equalsIgnoreCase("d")) {
                        cube.rotate(RubiksCube.Side.DOWN, clockwise);
                    }
                }
                
                inputField.setText("");
            }
        });
    }

    public void start() {
        window.setVisible(true);
        
        while(window.isVisible()) {
            renderer.display();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        float x = (2.0f * e.getX()) / 512 - 1.0f;
        float y = 1.0f - (2.0f * e.getY()) / 512;
        float z = 10;
        
        cube.mousex = x*0.2f;
        cube.mousey = y*0.2f;
        cube.mousez = z;        
    }
    
    public static void main(String[] args) {
        JRubik rubikscube = new JRubik();
        rubikscube.start();
        
        System.exit(0);
    }
}