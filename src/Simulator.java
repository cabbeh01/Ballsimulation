import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Simulator extends JFrame{

    //Making JFrame components accessible in other classes
    public static JLabel lblBallAmount = new JLabel("0");
    public static JButton btnChosenColor = new JButton();

    public static JSlider sldGravity = new JSlider();
    public static JSlider sldRadius = new JSlider();
    public static JSlider sldVelocity = new JSlider();

    public static void main(String[] args) throws IOException {

        //Creating a JFrame object
        JFrame frame = new JFrame();

        //Assigning a width and height for the frame
        frame.setSize(820,820);

        //***Creating the panels***
        JPanel p1 = new JPanel();
        Room   p2 = new Room();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();


        //***Creating button objects***
        JButton btnSim = new JButton("Gravity : OFF");
        JButton btnReset = new JButton("↺");
        JButton btnStart = new JButton();
        JButton btnAddBall = new JButton("Add ball");
        JButton btnSubtractBall = new JButton("Remove ball");
        JButton btnColor = new JButton("Choose color");
        JButton btnCollision = new JButton("Collision : OFF");

        //Adding a icon to button
        btnStart.setIcon(new ImageIcon("res/pause.png"));



        //***Creating labels***
        JLabel lblGravity = new JLabel("Gravity-Force");
        JLabel lblRadius = new JLabel("Radius");
        JLabel lblVelocity = new JLabel("Velocity");

        JLabel lblMin1 = new JLabel("1");
        JLabel lblMax10 = new JLabel("10");

        JLabel lblMin1_2 = new JLabel("1");
        JLabel lblMax10_2 = new JLabel("10");

        JLabel lblMin10_3 = new JLabel("10");
        JLabel lblMax100 = new JLabel("100");

        JLabel lblBallCIcon  = new JLabel("");
        lblBallCIcon.setIcon(new ImageIcon("res/ball.png"));

        JLabel lblHeader  = new JLabel("");
        lblHeader.setIcon(new ImageIcon("res/title.png"));

        JLabel lblHeader2  = new JLabel("");
        lblHeader2.setIcon(new ImageIcon("res/title2.png"));


        //**Setting layout setting for button and layers**
        labelSetDefaultColor(lblHeader,lblGravity,lblRadius,lblVelocity,lblMax10,lblMin10_3,lblMin1_2,lblMin1,lblMax10_2,lblMax100,lblBallAmount);
        buttonLayout(btnSim,btnAddBall,btnColor,btnSubtractBall,btnReset,btnStart,btnCollision);

        //**Assigning a dimension to each label**
        lblHeader.setPreferredSize(new Dimension(120, 30));
        lblHeader2.setPreferredSize(new Dimension(150, 50));
        lblGravity.setPreferredSize(new Dimension(120, 20));
        lblRadius.setPreferredSize(new Dimension(120, 20));
        lblVelocity.setPreferredSize(new Dimension(120, 20));

        //**Assigning font**
        lblBallAmount.setFont(new Font("Arial Black", Font.PLAIN, 24));
        lblHeader.setFont(new Font("Arial Black", Font.PLAIN, 24));

        //**Assigning a dimension to each button**
        btnColor.setPreferredSize(new Dimension(120, 30));
        btnAddBall.setPreferredSize(new Dimension(120, 30));
        btnSubtractBall.setPreferredSize(new Dimension(120, 30));
        btnCollision.setPreferredSize(new Dimension(120, 30));
        btnChosenColor.setPreferredSize(new Dimension(120, 30));
        btnStart.setPreferredSize(new Dimension(50, 30));
        btnReset.setPreferredSize(new Dimension(50, 30));
        btnSim.setPreferredSize(new Dimension(120, 30));


        //**Assigning a dimension to each slider**
        sldGravity.setPreferredSize(new Dimension(120, 30));
        sldRadius.setPreferredSize(new Dimension(120, 30));
        sldVelocity.setPreferredSize(new Dimension(120, 30));



        //**Slider layout combined with label**
        lblGravity.setPreferredSize(new Dimension(185,30));
        lblGravity.setHorizontalAlignment(SwingConstants.CENTER);
        sldGravity.setBackground(Color.darkGray);
        sldGravity.setUI(new BasicSliderUI(sldGravity));
        sldGravity.setFocusable(false);
        sldGravity.setMaximum(10);
        sldGravity.setMinimum(1);

        lblRadius.setPreferredSize(new Dimension(185,30));
        lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
        sldRadius.setBackground(Color.darkGray);
        sldRadius.setUI(new BasicSliderUI(sldRadius));
        sldRadius.setFocusable(false);
        sldRadius.setMaximum(100);
        sldRadius.setMinimum(10);

        lblVelocity.setPreferredSize(new Dimension(185,30));
        lblVelocity.setHorizontalAlignment(SwingConstants.CENTER);
        sldVelocity.setBackground(Color.darkGray);
        sldVelocity.setUI(new BasicSliderUI(sldVelocity));
        sldVelocity.setFocusable(false);
        sldVelocity.setMaximum(10);
        sldVelocity.setMinimum(1);



        //**ColorPicker settings**
        btnChosenColor.setBackground(Color.white);
        btnChosenColor.setEnabled(false);


        //      ***   Panel 1   ***
        p1.add(lblHeader,BorderLayout.NORTH);
        p1.add(lblRadius,BorderLayout.NORTH);
        p1.add(lblMin10_3,BorderLayout.NORTH);
        p1.add(sldRadius,BorderLayout.NORTH);
        p1.add(lblMax100,BorderLayout.NORTH);

        p1.add(lblVelocity,BorderLayout.NORTH);
        p1.add(lblMin1_2, BorderLayout.NORTH);
        p1.add(sldVelocity,BorderLayout.NORTH);
        p1.add(lblMax10, BorderLayout.NORTH);

        p1.add(btnColor,BorderLayout.NORTH);
        p1.add(btnChosenColor,BorderLayout.NORTH);

        p1.add(btnAddBall, BorderLayout.NORTH);
        p1.add(btnSubtractBall, BorderLayout.NORTH);

        p1.add(lblHeader2,BorderLayout.NORTH);
        p1.add(lblGravity,BorderLayout.NORTH);
        p1.add(lblMin1, BorderLayout.NORTH);
        p1.add(sldGravity,BorderLayout.NORTH);
        p1.add(lblMax10_2, BorderLayout.NORTH);

        p1.add(btnCollision,BorderLayout.NORTH);

        p1.setPreferredSize(new Dimension(200,100));



        //***   Panel 3 - Info panel  ***
        lblBallCIcon.setPreferredSize(new Dimension(50, 40));
        p3.add(lblBallCIcon,BorderLayout.NORTH);
        p3.add(lblBallAmount,BorderLayout.NORTH);

        p3.setPreferredSize(new Dimension(100,50));



        //***   Panel 4  ***
        p4.add(btnSim, BorderLayout.NORTH);
        p4.add(btnStart,BorderLayout.NORTH);
        p4.add(btnReset,BorderLayout.NORTH);

        p4.setPreferredSize(new Dimension(100,40));



        //Setting colors on panels
        p1.setBackground(Color.DARK_GRAY);
        p3.setBackground(Color.DARK_GRAY);
        p4.setBackground(Color.DARK_GRAY);


        //Adding panels to frame
        frame.add(p1,BorderLayout.EAST);
        frame.add(p2);
        frame.add(p3,BorderLayout.NORTH);
        frame.add(p4,BorderLayout.SOUTH);

        //Setting windows settings
        frame.setTitle("Miniproject - Bouncing balls");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(ImageIO.read(new File("res/icon.png")));


        // _______________ ButtonListeners _______________
        btnColor.addActionListener(e -> {
            Color pickColor = JColorChooser.showDialog(
                    new JColorChooser(),
                    "Choose Background Color",Color.BLACK);

            btnChosenColor.setBackground(pickColor);

        });

        btnStart.addActionListener(e -> {
            if(!p2.paused){
                p2.paused = true;
                btnStart.setText("▶");
                btnStart.setIcon(null);
                toggleButtons(btnAddBall,btnColor,btnSim,btnSubtractBall,btnReset,btnCollision);
            }
            else{
                p2.paused = false;
                toggleButtons(btnAddBall,btnColor,btnSim,btnSubtractBall,btnReset,btnCollision);
                btnStart.setIcon(new ImageIcon("res/pause.png"));
                btnStart.setText("");
            }
        });

        btnCollision.addActionListener(e -> {
            if(Ball.collision){
                Ball.collision = false;
                btnCollision.setText("Collision : OFF");
            }
            else{
                Ball.collision = true;
                btnCollision.setText("Collision : ON");
            }
        });

        btnAddBall.addActionListener(e -> p2.addBall());

        btnReset.addActionListener(e -> p2.resetRoom());

        btnSubtractBall.addActionListener(e -> p2.subtractBall());

        btnSim.addActionListener(arg -> {
            if(Ball.gravity){
                Ball.gravity = false;
                btnSim.setText("Gravity : OFF");
            }
            else{
                Ball.gravity = true;
                btnSim.setText("Gravity : ON");
            }

        });


        //Starting the gameloop
        p2.gameloop();
    }

    //Code for shorten down setForeground, setFocusable, setUI, and a ToggleButton on a lot of objects
    private static void labelSetDefaultColor(JLabel ... a) {
        for(JLabel label : a){
            label.setForeground(Color.white);
        }
    }

    private static void buttonLayout(JButton ... a) {
        for(JButton button : a){
            button.setFocusable(false);
            button.setUI(new BasicButtonUI());
        }
    }

    private static void toggleButtons(JButton ... a) {
        for(JButton button : a){
            button.setEnabled(!button.isEnabled());
        }
    }
}