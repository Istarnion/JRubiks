JRubiks
=======

A simple implementation of the classic Rubiks Cube puzzle in Java and old fashioned OpenGL.

To build, do `gradle :run`

To manipulate the cube, type commands in the text field at the bottom.
The commands are:

- f for front face
- r for right face
- l for left face
- b for back face
- u for upper face
- d for down face

This will rotate the target face in the clockwise direction if you were looking at it directly.
Add 'i' to rotate counter-clockwise, so 'f' will rotate front face clockwise, and 'ui' to rotate upper face counter-clockwise.
