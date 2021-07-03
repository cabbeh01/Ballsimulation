# Bouncing Balls
The end-assignment in the first Java Programming course was to develop a bouncing ball in a simulated environment.  
The program is divided into four different panels where the user will interact with the
software in specific way.

- Top: A counter of the number of balls in the room
- Ball Editor: In this area is the tools for editing the ball setting. When adding a ball, the
settings on the screen will be applied on that specific ball object
- Room Editor: The room editor affects the environment for all the balls. Here you can apply
collision and change the gravitation-force.
- Simulated Area: This is the panel where the balls will bounce and were the physics, forces will be
applied. It is the viewing area of the simulation.
- Bottom Controller: Here you can turn on and off the gravity, pause and play, and reset the simulation.

![](https://github.com/cabbeh01/Ballsimulation/blob/main/ballsmulator.png)

## What I learned
- Classbuilding in Java
- Implementation of Swing (JFrame)
- Setting up a gameloop

## Known issues
The collision between the balls is working, but in combination with the gravity and
when balls are stacking on each other at the ground is not working correctly. To avoid
this problem, you need to activate gravity and collision then only add balls, so the
floor is not filled.
