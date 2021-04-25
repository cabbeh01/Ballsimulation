import javax.swing.*;
import java.awt.*;

public class Ball extends JPanel {
    //Creating point for ball
    Point position = new Point();

    //Ball radius
    int r = Simulator.sldRadius.getValue();

    //Velocity
    double vx = Simulator.sldVelocity.getValue() + 0.0;
    double vy = Simulator.sldVelocity.getValue() + 0.0;

    //Gravity force
    double G = 9;

    //Time constant
    int t = 1;

    //Booleans to define the behavior
    public static boolean gravity = false;
    public static boolean collision = false;

    //Checks if the ball has collided
    public boolean hasCollided = false;

    //Color of the ball
    Color col = Simulator.btnChosenColor.getBackground();

    //Access to the room object
    Room rom;

    //Constructor of the ball
    Ball(int x,int y, Room rom){
        this.position.x = x;
        this.position.y = y;
        this.rom = rom;
    }

    //This method moves the ball based on diffrent scenarios in the program
    public void moveBall() {
        //Getting the gravity-force from the slider
        this.G = Simulator.sldGravity.getValue();

        //Calculating the position from formula x = x+t*vx , y = y +t*vy
        this.position.x += (int)(vx * t);
        this.position.y += (int)(vy * t);

        //The walls in the room
        if(this.position.x < this.r){
            this.vx = Math.abs(vx);
            this.position.x = this.r;
        }
        else if(this.position.x > (rom.getSize().width - this.r)){
            this.vx = -Math.abs(vx);
            this.position.x = rom.getSize().width - this.r;
        }

        //Condition if gravity is activated
        if(gravity){
            //Divides the g-constant to neutralise it to a value between 0.1 and 1
            this.vy += this.G/10;

            //If reaches the roof
            if(this.position.y < this.r){
                this.vy = Math.abs(vy);
                this.position.y = this.r;
            }
            //If reaches the ground
            else if(this.position.y >= (rom.getSize().height - this.r)){
                //Zero the y velocity
                if(hasCollided){
                    this.vy = 0;
                }
                else{
                    //Reduces the bounce after each hit with ground
                    this.vy = Math.floor(-this.vy + G*0.5);
                    this.position.y = rom.getSize().height - this.r;
                    if(this.vx > 0){
                        //Friction in x-axis
                        this.vx -= 0.1;
                    }
                }

            }

        }
        else{
            checkFloorRoof(rom);
        }

        this.hasCollided = false;

    }

    //This method returns a value true if the ball is intersecting with another ball
    public boolean isIntersecting(Ball b){

        //The delta x and delta y
        double dx = b.position.x - this.position.x;
        double dy = b.position.y - this.position.y;

        //Delta speed x and y
        double dspeedX = this.vx - b.vx;
        double dspeedY = this.vy - b.vy;
        double dotP = dx * dspeedX + dy * dspeedY;

        //Checks if the ball is mowing towards the intersecting ball
        if(dotP >= 0){
            //Pythagoras theorem
            return ((dx * dx) + (dy * dy)) < (this.r + b.r) * (this.r + b.r);
        }
        else {
            return false;
        }
    }

    //Checks the floor and the roof
    public void checkFloorRoof(Room rom){
        if(this.position.y < this.r){
            this.vy = Math.abs(vy);
            this.position.y = this.r;
        }
        else if(this.position.y > (rom.getSize().height - this.r)){
            this.vy = -Math.abs(vy);
            this.position.y = rom.getSize().height - this.r;
        }
    }

    //The paint component to draw the ballshape of the object
    @Override
    public void paint(Graphics g)
    {
        try{
            //Getting the properties from the original paint class so
            //so we just adding our code to it and don't re writes it
            super.paint(g);

            //Creating a G2D object
            Graphics2D a = (Graphics2D)g;

            //Sets color of the ball
            g.setColor(col);
            //Drawing the contour
            a.fillOval((this.position.x - this.r), (this.position.y-this.r), this.r * 2, this.r * 2);

            //Moving the ball
            moveBall();
        }
        catch (Exception ignored){ }
    }
}
