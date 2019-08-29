import javax.swing.*;
import java.awt.*;

final public class Animate {

    JFrame frame;
    DrawPanel drawPanel;
    private int xSizePanel = 1500;
    private int ySizePanel = 800;
    private Color BlueL = new Color(54,155,172);
    private int oneX = 200; // Starting X coordinate
    private int oneY = 200; // Starting Y coordinate
    
    private int rocketX = 100;
    private int rocketY = 500;
    private int dotW = 50;
    private int dotH = 50;

         
    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;
    int[] xArr = new int[]{100, 100, 0};  int[] yArr = new int[]{50, 0, 25};
    public static void main(String[] args) {
        new Animate().go();
    }

    private void go() {
        frame = new JFrame("Moon and Earth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(xSizePanel, ySizePanel);
        frame.setLocation(375, 55);
        moveDot();
    }

    class DrawPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3030379568821478211L;
		
		public void paintComponent(Graphics g) {
        	
            //Inside Color
            g.setColor(Color.BLACK);
            g.fillRect(0,0,this.getWidth(), this.getHeight());
                        
            g.setColor(Color.WHITE);
            g.fillOval(oneX, oneY, dotW, dotH);
            
            //Earth
            g.setColor(BlueL);
            g.fillOval(-350, frame.getHeight()-500, 800, 800);
            //Moon 
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(frame.getWidth()-200, frame.getHeight()-500, 100, 100);
            
            //Stars
            g.setColor(Color.WHITE);
        	g.fillRect(1200,123, 1, 1);
        	g.fillRect(900,200, 1, 1);
        	g.fillRect(800,500, 1, 1);
            g.fillRect(200,100, 1, 1);
            g.fillRect(400,130, 2, 2);
            g.fillRect(850,550, 1, 1);
            g.fillRect(400,290, 2, 3);
            //Rocket
            g.setColor(Color.pink);
            g.fillRoundRect(rocketX, rocketY, 20, 50, 10, 10);
         // Comet
            g.setColor(Color.RED);
            g.fillPolygon(xArr, yArr, 3);
            
            
                      
        }
    }
      

    private void moveDot() {
        while(true){
            checkBounds();
            moveLoc();
            startRocket();
            try{
                Thread.sleep(10);
            } catch (Exception exc){}
            frame.repaint();
        }
    }
    private void startRocket(){
    	for(int i = 0; i < xArr.length; i++) {
    		xArr[i]+= 5;
    	}
    }
    private void moveLoc(){
    	if(up){
    		oneY--;
                      
        }
        if(down){
            oneY++;
        }
        if(left){
            oneX--;
        }
        if(right){
            oneX++;
        }	
    }
    //
    private void checkBounds(){
    	if(oneX+dotW  >= frame.getWidth()){
            right = false;
            left = true;
        }
        if(oneX <= 0){
            right = true;
            left = false;
        }
        if(oneY+dotH >= frame.getHeight()){
            up = true;
            down = false;
        }
        if(oneY <= 0){
            up = false;
            down = true;
        }
        for(int i = 0; i <xArr.length; i++) {
        	if(xArr[i] > frame.getWidth()+ 500) {
        		xArr[0] -= frame.getWidth() + 1000;
        		xArr[1] -= frame.getWidth() + 1000;
        		xArr[2] -= frame.getWidth() + 1000;
        	}
        }
    }
}