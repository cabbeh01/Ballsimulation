import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Room extends JPanel {
    //Creating random object
    Random r = new Random();

    //Position for the "new" ball object
    int x,y;

    //Radius for the "new" ball object
    int radius = Simulator.sldRadius.getValue();

    //Variable to see if the game is paused
    public boolean paused = false;

    //Ball list to store the ball objects
    ArrayList<Ball> ballList = new ArrayList<>();

    //This function is used when a ball is added in to the simulation
    public void addBall(){

        //Checking if the maximum area is reached
        if(!maximumAreaReached(new Ball(0,0,this))){
            int c = 0;

            //Randomizing a position in the room
            x = r.nextInt(this.getWidth());
            y = r.nextInt(this.getHeight());

            //Loops until it finds a empty space to insert the ball on
            while(c != ballList.size()){
                c = 0;
                for(Ball bll : ballList){

                    //Checks if the new position is intersection with the other balls
                    if(isIntersecting(x,y,bll)){
                        x = r.nextInt(this.getWidth());
                        y = r.nextInt(this.getHeight());
                        c=-1;
                    }
                    else{
                        c++;
                    }

                }
            }
            //Then it adds the ball when it found a space
            ballList.add(new Ball(r.nextInt(this.getWidth()),r.nextInt(this.getHeight()),this));
        }
        else {
            //Dialog message to tell the user if there is no space left
            JOptionPane.showMessageDialog(new Frame(), "Det får inte plats fler bollar");
        }
    }

    //This function is calculating the area for the balls
    public boolean maximumAreaReached(Ball b){
        //Area of balls (rectangles)
        int area = 0;

        //Calculating the room area
        int panelArea = this.getHeight() * this.getWidth();

        //Sums upp the balls area
        for(Ball a : ballList){
            area += (a.r*2) *  (a.r*2);
        }

        //Adding the new balls area into the total area of the balls
        area += (b.r*2) * (b.r*2);

        //Return the statement true or false based if there is space left or not
        return !(panelArea - area / Math.PI > area);
    }

    //This function is removing one ball at the time
    //It removes the latest added ball
    //Based on (LIFO) Last In First Out
    public void subtractBall(){
        if(ballList.size() > 0){
            ballList.remove(ballList.size()-1);
            this.repaint();
            updateGUI();
        }
    }

    //This function resets the room
    public void resetRoom(){
        ballList.clear();
        paused = false;
    }

    //This method is the heart of the program.
    //It loops through each frame to update simulation and calls the redraw function
    public void gameloop(){
        //Constants
        final int TICKSPERSECONDS = 64;
        final int SKIPTICK = 1000 / TICKSPERSECONDS;
        final int MAXFRAMESKIP = 10;

        //Getting the current time in milliseconds
        double ngt = System.currentTimeMillis();

        //Amount of iterations
        int iterations;

        //Infinity loop
        while (true) {
            iterations = 0;
            //Checks so the time is running forward and checks so the iteration is lower than the frameskip
            while (System.currentTimeMillis() > ngt && iterations < MAXFRAMESKIP) {
                //Skips to redraw if the simulation is paused
                if(paused){
                    continue;
                }
                else{
                    //Checking collision
                    getCollision(ballList);

                    //Repaints
                    this.repaint();

                    updateGUI();
                }
                //Adding the SkipTicks
                ngt += SKIPTICK;

                //Count +1 on iteration
                iterations++;
            }
        }
    }

    //This method checks if a position + radius and a ball is intersecting
    public boolean isIntersecting(int x, int y,Ball b){
        double dx = b.position.x - x;
        double dy = b.position.y - y;

        //Returns true if the ball intersect with the temp coordinates and radius
        return ((dx * dx) + (dy * dy)) < (this.radius + b.r) * (this.radius + b.r);
    }

    //This function calculate the collision and adding the forces to respective balls
    //The collision is an elastic shock where the momentum and the kinetic energy are preserved.
    public void getCollision(ArrayList<Ball> bList){

        //Checks if the collision in the simulation is activated
        if(Ball.collision){

            //Looping each of the balls in the ball list
            for(int i = 0; i < bList.size();i++){

                //Looping the balls again but this
                //time offset with one ball
                for(int j = i+1; j < bList.size();j++){

                    //If the two balls intersecting then begin to calculate forces
                    if (bList.get(i).isIntersecting(bList.get(j))) {

                        //Adding a boolean to remove double collision so the forces
                        //don't apply twice
                        bList.get(i).hasCollided = true;
                        bList.get(j).hasCollided = true;

                        double vx1Rel = bList.get(i).vx - bList.get(j).vx;
                        double vy1Rel = bList.get(i).vy - bList.get(j).vy;

                        double dx = (bList.get(j).position.x - bList.get(i).position.x);
                        double dy = (bList.get(j).position.y - bList.get(i).position.y);

                        //Distance formula
                        double dist = Math.sqrt(dx * dx + dy * dy);

                        double riktb2x = dx / dist;
                        double riktb2y = dy / dist;

                        double vx2Rel = riktb2x * (riktb2x * vx1Rel + riktb2y * vy1Rel);
                        double vy2Rel = riktb2y * (riktb2x * vx1Rel + riktb2y * vy1Rel);

                        vx1Rel = vx1Rel - vx2Rel;
                        vy1Rel = vy1Rel - vy2Rel;

                        //Set the balls velocity to the new once
                        bList.get(i).vx = (vx1Rel + bList.get(j).vx);
                        bList.get(i).vy = (vy1Rel + bList.get(j).vy);
                        bList.get(j).vx = (vx2Rel + bList.get(j).vx);
                        bList.get(j).vy = (vy2Rel + bList.get(j).vy);

                    }
                }
            }
        }
    }

    //This method is updating the GUI
    public void updateGUI(){
        Simulator.lblBallAmount.setText(""+ballList.size());
    }

    //The paint component to draw the panel and calling the paint function on the balls
    @Override
    public void paint(Graphics g) {

        //Getting the properties from the original paint class so
        //so we just adding our code to it and don't re writes it
        super.paint(g);

        //Setting background color
        this.setBackground(Color.gray);

        Graphics2D g2d = (Graphics2D)g;

        //Enabling antialias to get a smoother experience
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Calling the paint method in each of the balls
        try{
            for (Ball ball : ballList) {
                ball.paint(g2d);
            }
        }
        catch (Exception ignored){ }
    }
}
