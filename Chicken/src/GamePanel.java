import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;



public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 400;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 70;
    
    int killCount=0;
    boolean running = false;
    int eggX;
    int eggY;
    int catX;
    int catY;
    int chickenX;
    int chickenY;
    int lazerX;
    int lazerY;
    int LefttargetX;
    int LefttargetY;
    int leftLazerX;
    int leftLazerY;
    int rightLazerX;
    int rightLazerY;
    int rightTargetX;
    int rightTargetY;
    int bigCatX;
    int bigCatY;
    int bigRightTargetX;
    int bigLeftTargetX;
    int bigLeftTargetY;
    int bigRightTargetY;
    
    boolean drawBigCat=false;
    boolean level2=false;
    boolean leftHit = false;
    boolean orange = false;
    boolean hit = false;
    boolean shot = false;
    boolean rightHit=false;
 
    Timer timer;
    Random random;
    
    Image egg;
    Toolkit tk;
	
	
	
	GamePanel(){
    	random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    	
    }
	
	public void startGame() {
		newEgg();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
		newCat();
	   	newChicken();
	   	newTarget();
	   	newRightTarget();
	   	
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		drawCat(g);
		drawEgg(g);
		drawChicken(g);
		drawLazer(g);
		drawTarget(g);
		drawLeftLazer(g);	
		drawRightLazer(g);	
		drawRightTarget(g);
		
		
		
		if(drawBigCat){
		drawBigCat(g);
		}
	}
		
	
	
	
	
	public void drawEgg(Graphics g) {
		
		if(running) {
		g.setColor(Color.yellow);
		g.fillOval(eggX,eggY, UNIT_SIZE, UNIT_SIZE);
		
		Toolkit tk =Toolkit.getDefaultToolkit();
		Image egg = tk.getImage("egg.png");
		
		g.drawImage(egg, eggX, eggY, UNIT_SIZE, UNIT_SIZE, Color.BLACK,this);
		
		
		}
		else {
		g.setColor(Color.black);
			
		}
	    
	  
		
	}
	
	public void drawCat(Graphics g) {
		if(running) {
		g.setColor(Color.white);
	
		g.fillRect(catX, catY, UNIT_SIZE, UNIT_SIZE);
		
		Toolkit tk =Toolkit.getDefaultToolkit();
		Image c = tk.getImage("littlecat.jpg");
		
		g.drawImage(c,catX ,catY, UNIT_SIZE, UNIT_SIZE, Color.BLACK,this);
		
		if(killCount==5&&running) {
			
			printLevel2(g);
			
		}
		
		}
		
		if(running==false) {
			
			gameOver(g);
		}
	   
	}

	public void drawBigCat(Graphics g) {
		
		if(running) {
			g.setColor(Color.white);
			g.fillOval(bigCatX, bigCatY, UNIT_SIZE*3, UNIT_SIZE*3);
			

			Toolkit tk =Toolkit.getDefaultToolkit();
			Image bc = tk.getImage("bigcat.jpg");
			
			g.drawImage(bc,bigCatX ,bigCatY, UNIT_SIZE*3, UNIT_SIZE*3, Color.BLACK,this);
			}
       if(killCount==10&&running) {
			
			printLevel3(g);
			
		}	
		
		
		if(running==false) {
				
				gameOver(g);
			}
		
		
	}
	
	public void drawChicken(Graphics g) {
		
		if(running) {
		g.setColor(Color.red);
		g.fillOval(chickenX, chickenY, UNIT_SIZE, UNIT_SIZE);
		
		Toolkit tk =Toolkit.getDefaultToolkit();
		Image ch = tk.getImage("chicken.jpg");
		
		g.drawImage(ch,chickenX ,chickenY, UNIT_SIZE, UNIT_SIZE, Color.BLACK,this);
            
		    if(orange) {
			
			g.setColor(Color.green);
			g.fillOval(chickenX, chickenY, UNIT_SIZE, UNIT_SIZE);
			
			Toolkit tki =Toolkit.getDefaultToolkit();
			Image cha = tki.getImage("chickenangry.jpg");
			
			g.drawImage(cha,chickenX ,chickenY, UNIT_SIZE, UNIT_SIZE, Color.BLACK,this);
		    
		    }
		
		}

		
		else {
		g.setColor(Color.black);
			
		}
	
		
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+killCount,(SCREEN_WIDTH - metrics1.stringWidth("Score:"+killCount))/2,g.getFont().getSize());
		
	}
	
	public void drawLazer(Graphics g) {
		if(hit&&running) {
		g.setColor(Color.pink);
		g.fillRect(lazerX, lazerY, UNIT_SIZE, UNIT_SIZE);
		
		Toolkit tk =Toolkit.getDefaultToolkit();
		Image l = tk.getImage("chicken_down.png");
		
		g.drawImage(l,lazerX ,lazerY, UNIT_SIZE, UNIT_SIZE, Color.BLACK,this);
		
		}
		else {
			
			g.setColor(Color.black);	
		}
	   
	}
	
	public void drawLeftLazer(Graphics g) {
		
		if(leftHit&&running)	{
			g.setColor(Color.pink);
			g.fillRect(leftLazerX, leftLazerY, UNIT_SIZE, UNIT_SIZE);
		
			Toolkit tk =Toolkit.getDefaultToolkit();
			Image ll = tk.getImage("chicken_left.png");
			
			g.drawImage(ll,leftLazerX ,leftLazerY, UNIT_SIZE, UNIT_SIZE, Color.BLACK,this);
		}
		else {
			
			g.setColor(Color.black);
			
		}
	
	    
	}
	
	public void drawRightLazer(Graphics g) {
		
		if(rightHit&&running)	{
			g.setColor(Color.pink);
			g.fillRect(rightLazerX, rightLazerY, UNIT_SIZE, UNIT_SIZE);
		
			Toolkit tk =Toolkit.getDefaultToolkit();
			Image rl = tk.getImage("chicken_right.png");
			
			g.drawImage(rl,rightLazerX ,rightLazerY, UNIT_SIZE, UNIT_SIZE, Color.BLACK,this);
		}
		else {
			
			g.setColor(Color.black);
			
		}
		
	}
	
	public void drawRightTarget(Graphics g) {
		
		if(running) {
			g.setColor(Color.magenta);
			g.fillRect(rightTargetX, rightTargetY, UNIT_SIZE/4, UNIT_SIZE);
			}
			else {
				g.setColor(Color.black);}
		
	}
	
	public void drawTarget(Graphics g) {
		
		if(running) {
		g.setColor(Color.magenta);
		g.fillRect(LefttargetX, LefttargetY, UNIT_SIZE/4, UNIT_SIZE);
		}
		else {
			g.setColor(Color.black);}	
	
	}
	
		
	
	
	
	public void newRightLazer() {
		
		rightLazerX=chickenX;
		rightLazerY=chickenY;
		
	}
	
	public void newLazer() {
     
    	   
    	   lazerX=chickenX;
    	   lazerY=chickenY;
    	   
       
	}
	
	public void moveLazer() {
		
		
			
			lazerY= lazerY + UNIT_SIZE;
	}		
	public void moveLeftLazer() {
		
		
		    leftLazerX = leftLazerX - UNIT_SIZE;
	}
	
	public void moveRightLazer() {
		
		
		rightLazerX = rightLazerX + UNIT_SIZE;
	}
	
	public void checkEqual() {
		
		while(catX==eggX&&catY==eggY) {
			newEgg();
			newCat();
		}
		
	}
	
	
	
	public void newChicken() {
		
		chickenX=SCREEN_WIDTH/2;
		chickenY=0;
		

	}
	
	public void newTarget() {
		
		LefttargetX = 0;
		LefttargetY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
	}
	
	public void newRightTarget() {
		
		rightTargetX = SCREEN_WIDTH-UNIT_SIZE/4;
		rightTargetY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
  
    
    
	
	public void newEgg() {
		eggX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		eggY = 775;
		
		
	}
	
	public void newCat() {
		catX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		catY = 775;
		
	
	}
	
	public void newBigCat() {
		
		bigCatX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		bigCatY = 725;
		
	}
	
	public void newLeftLazer() {
		leftLazerX=chickenX;
		leftLazerY=chickenY;
		
		
	}
	
	public void moveEgg() {
		
		
		
		eggY = eggY - UNIT_SIZE;
		
	
	}
	
	public void moveCat() {
		
		catY = catY - UNIT_SIZE;
	}
	
	public void checkEgg() {
		
		if(eggX==chickenX && eggY == chickenY) {

	        orange = true;
	        newEgg();
	        
				
	       
			
		}
		
	}
	
	public void moveBigCat() {
		
		bigCatY = bigCatY - UNIT_SIZE;
		
	}

	public void checkCat() {
		
		if(catX==chickenX && catY == chickenY) {
        
	      	running = false;
			
		}
	}
	

	public void checkBigCat() {
		
		if(((chickenX==bigCatX) || (chickenX==bigCatX+UNIT_SIZE) || (chickenX==bigCatX+UNIT_SIZE*2)) && 
				((chickenY==bigCatY) || (chickenY==bigCatY+UNIT_SIZE) || (chickenY==bigCatY+UNIT_SIZE*2))) 
		{
			
			running = false;
			
		}
		
	}
	
	
	public void checkHitCat() {
		if(lazerX==catX && (lazerY==catY||lazerY==catY+UNIT_SIZE))
		
	    {		
				                                                      
			
			shot = true;
			
		}
 		
		}
	
	
	
	
    public void checkLeftTarget() {
		
		if(leftLazerX==LefttargetX && leftLazerY ==LefttargetY ) {
			
			killCount++;
			newTarget();
		    
		}
		
	}
	
public void checkRightTarget() {
		
		if(rightLazerX==rightTargetX-UNIT_SIZE+UNIT_SIZE/4 && rightLazerY ==rightTargetY ) {
			
			killCount++;
			newRightTarget();
		    
		}
		
	}


	
	
	public void gameOver(Graphics g) {
		//score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+killCount,(SCREEN_WIDTH - metrics1.stringWidth("Score:"+killCount))/2,g.getFont().getSize());
		//gameover
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over",(SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
		
		
	}
	
	public void printLevel2(Graphics g){
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Level 2",(SCREEN_WIDTH - metrics2.stringWidth("Level 2"))/2,SCREEN_HEIGHT/2);
		
	}
	
public void printLevel3(Graphics g){
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Level 3",(SCREEN_WIDTH - metrics2.stringWidth("Level 3"))/2,SCREEN_HEIGHT/2);}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		//level1
		if(running&&killCount<5) {
			
			checkEqual();
			checkEgg();
			checkCat();
			moveEgg();
			moveCat();
			moveLazer();
			checkHitCat();
			moveLeftLazer();
			checkLeftTarget();
			moveRightLazer();
			checkRightTarget();
			
			
		if(eggY==0) {
		    	
		    	newEgg();
		    	
		    	
		    }
			
		if(catY==0) 
            {
		    	
		    	newCat();
		    }
		if(shot)
		{
			
			killCount++;
			shot=false;
			newCat();
			
			
        }
		
		level2=true;
		
		}
		//level2
		if(running&&killCount>=5) {
			   
			    drawBigCat=true;
			
				moveCat();
				checkEqual();
				checkEgg();
				checkCat();
				moveEgg();
				moveBigCat();
				moveLazer();
				checkHitCat();
				moveLeftLazer();
				checkLeftTarget();
				moveRightLazer();
				checkRightTarget();
				checkBigCat();
				
			if(eggY==0) {
			    	
			    	newEgg();
			    	
			    	
			    }
				
			if(bigCatY==0||level2==true) 
	            {
			    	
			    	newBigCat();
                    level2=false;			    
	            }
			
			if(catY==0) 
            {
		    	
		    	newCat();
		    }
			
			
			if(shot)
			{
				killCount++;
				shot=false;
				newCat();
			}
		}
		//level3	
		if(running&&killCount>=10) {
				   
			    drawBigCat=true;
			
				moveCat();
				checkEqual();
				checkEgg();
				checkCat();
				moveEgg();
				moveBigCat();
				moveLazer();
				checkHitCat();
				moveLeftLazer();
				checkLeftTarget();
				moveRightLazer();
				checkRightTarget();
				checkBigCat();
				
			if(eggY==0) {
			    	
			    	newEgg();
			    	
			    	
			    }
				
			if(bigCatY==0||level2==true) 
	            {
			    	
			    	newBigCat();
                    level2=false;			    
	            }
			
			if(catY==0) 
            {
		    	
		    	newCat();
		    }
			
			
			if(shot)
			{
				killCount++;
				shot=false;
				newCat();
			}
				
			
			
			
			
		}
		    	
		    
			
			
		
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e) {
		
			switch(e.getKeyChar()) {
			
			case 'd':
				chickenX = chickenX + UNIT_SIZE;
				break;
			case 'a':
				chickenX = chickenX - UNIT_SIZE;
				break;
			case 'w':
				chickenY = chickenY - UNIT_SIZE;
				break;
			case 's':
			    chickenY = chickenY + UNIT_SIZE;
			    break;
			
			
			case '2': 
				if(orange) {
			  
				orange=false;
				newLazer();
				hit = true;
				}
				break;
			
			case '1':
				
				if(orange) {
					  
					orange=false;
					newLeftLazer();
					leftHit = true;
					}
					break;
			
			
            case '3':
				
				if(orange) {
					  
					orange=false;
					newRightLazer();
					rightHit = true;
					}
					break;		
			}
		}
		
		
	}
}	
