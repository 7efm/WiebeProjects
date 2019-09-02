import javax.swing.*;
import java.awt.*;

final public class Animate {

    JFrame frame;
    DrawPanel drawPanel;
    private int sleepTime = 0;
    private int xSizePanel = 1500;
    private int ySizePanel = 800;
    private Color BlueL = new Color(54,155,172);
    private Color whiteTrans = new Color(255, 255, 255, 100);
    private Color BrownL = new Color(242, 233, 228);
    private Color orangeRedTrans = new Color(250, 121, 33);
    private Color green2 = new Color(155, 197, 61);
    private int fire = 0;
    private int cometX = 0;
    private int cometY = 0;
    private int dotW = 0;
        		
    
    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;
    
    int[] xCont = new int[] {-20, 50, 30, 300, 350, 300, 200}; int[] yCont = new int[] {320, 400, 600, 800, 600, 420, 340};
    int[] xArr = new int[]{100, 115, 117, 102};  int[] yArr = new int[]{400, 395, 400, 405};//rocket
    int[] xArrR = new int[] {101, 102, 100, 98}; int[] yArrR = new int[] {401, 403, 405, 401};//rocket thrusts
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
		
		private static final long serialVersionUID = -3030379568821478211L;
		
		public void paintComponent(Graphics g) {
        	
            //Inside Color
            g.setColor(Color.BLACK);
            g.fillRect(0,0,this.getWidth(), this.getHeight());
            //Comet
            g.setColor(whiteTrans);
            g.fillArc(cometX, cometY, 100, 100, 325, 10 );
            g.setColor(new Color(255, 255, 255, 200));
            g.fillOval(cometX+90, cometY+71, 13, 13);
                        
            
            
            //Earth
            g.setColor(BlueL);
            g.fillOval(-350, frame.getHeight()-500, 800, 800);
            g.setColor(green2);
            g.fillPolygon(xCont, yCont, 7);
            g.fillOval(20, 400, 20, 20);
            g.setColor(BlueL);
            g.fillOval(20, 600, 70, 50);
            //Moon 
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(frame.getWidth()-200, frame.getHeight()-500, 100, 100);
            g.setColor(new Color(0,0,0, 100));
            g.fillOval(1360, 350, 20, 17);
            g.fillOval(1320, 310, 30, 27);
            g.fillOval(1315, 360, 10, 17);
            //Stars
            g.setColor(Color.WHITE);
        	g.fillRect(1200,123, 1, 1);
        	g.fillRect(900,200, 1, 1);
        	g.fillRect(800,500, 1, 1);
            g.fillRect(200,100, 1, 1);
            g.fillRect(400,130, 2, 2);
            g.fillRect(850,550, 1, 1);
            g.fillRect(400,290, 2, 3);
            // Rocket
            g.setColor(Color.GRAY);
            g.fillOval(xArr[1]-3, yArr[1]-1, 8, 6);
            g.setColor(orangeRedTrans);
            g.fillArc(xArr[0]-25, yArr[0]-5, 25, 25, 10, fire);
            g.setColor(Color.GRAY); 
            g.fillPolygon(xArrR, yArrR, 4);
            g.setColor(BrownL);
            g.fillPolygon(xArr, yArr,4);
            //Explosion
            g.setColor(Color.RED);
            g.fillArc(1312, 300, 25, 25, 10, dotW);
            
            
            
            
                      
        }
    }
      
   
    private void moveDot() {
        while(true){
            checkBounds();
            startComet();
            try{
                Thread.sleep(10);
            } catch (Exception exc){}
            startRocket();

            frame.repaint();
        }
    }
    private void startComet() {
    	cometX+=2;
    	cometY+= 1;
    }
    private void startRocket(){
    	if(sleepTime > 100) {
    	for(int i = 0; i < xArr.length; i++) {
    		xArr[i] += 1;
    		xArrR[i] += 1;
    		if(dotW > 0) {
    			dotW -= 20;
        		
    		}
    		
    		int x = xArr[i]; 
    	    double yVal = .00065*(x*x) -x +500;
    	    yArr[0] = (int)yVal;	yArrR[0] = (int)yVal +1;
    	    yArr[1] = (int)yVal -5; yArrR[1] = (int)yVal+3;
    	    yArr[2] = (int)yVal;	yArrR[2] = (int)yVal+6;
    	    yArr[3] = (int)yVal+5;	yArrR[3] = (int)yVal+1;
    	    if(xArr[0] > frame.getWidth() -800 && fire > 0) {
    	    	fire --;
    	    }else if(xArr[0] < frame.getWidth() - 800) {
    	    	fire = 23;
    	    	}
    	    }
    	}
    	sleepTime++;
    	}   
    
    private void checkBounds(){    	      
        if( (cometX > frame.getWidth() +500) && (cometY >frame.getHeight()+ 500) ) {
        	cometX = -1000;
        	cometY = -300;
        }
        
        if(xArr[1] > frame.getWidth() -175 ){
        	dotW += 360;
    		
            xArr[0] = 100;
        	yArr[0] = 400;
        	xArr[1] = 115;
        	yArr[1] = 395;
        	xArr[2] = 117;
        	yArr[2] = 400;
        	xArr[3] = 102;
        	yArr[3] = 405;
        	xArrR = new int[] {101, 102, 100, 98}; yArrR = new int[] {401, 403, 405, 401};
        	}
        }
    }
    
