package game1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener {

	private Spaceship spaceShip;
	private List<Missile> missileList;
	private List<Asteroid> asteroidList;
	private final int DELAY = 10; 
	private JLabel pauseScreen;
	private int count = 0;

	public GameBoard() {

		initBoard();
	}

	//This method is only called once, when the GameBoard is constructed over in GameInterface
	private void initBoard() {

		//The MouseListeners allow the program to do things when mouse events occur, like entering or exiting a component
		addMouseListener(new MAdapter());
		addMouseMotionListener(new MAdapter());

		addKeyListener(new KAdapter());

		//These set attributes of the JPanel
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);

		//Constructs a border layout for the JPanel
		setLayout(new BorderLayout());

		//This method configures the 'Game Paused' JLabel
		configPauseScreen();

		//Here we initialize the sprite we are using for the spaceship and the asteroid/missile ArrayLists
		spaceShip = new Spaceship(60, 40);
		missileList = new ArrayList<Missile> ();
		asteroidList = new ArrayList<Asteroid> ();

		//The timer will trigger an action event every DELAY ms
		Timer timer = new Timer(DELAY, this);	
		timer.start();
	}

	//This method constructs the 'Game Paused' JLabel and adds it to the JPanel
	private void configPauseScreen() {

		pauseScreen = new JLabel("Game Paused", JLabel.CENTER);
		pauseScreen.setForeground(Color.RED);
		pauseScreen.setFont(new Font("Serif", Font.PLAIN, 40));
		pauseScreen.setVisible(false);

		add(pauseScreen, BorderLayout.CENTER);
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		super.paintComponent(g2d);

		g2d.drawImage(spaceShip.getImage(), (int) spaceShip.getX(), 
				(int) spaceShip.getY(), this);

		if(!missileList.isEmpty())

			for(int i = 0; i < missileList.size(); i++) {

				Missile missile = missileList.get(i);
				g2d.drawImage(missile.getImage(), (int) missile.getX(), (int) missile.getY(), this);
			}

		if(!asteroidList.isEmpty())

			for(int i = 0; i < asteroidList.size(); i++) {

				Asteroid asteroid = asteroidList.get(i);
				g2d.drawImage(asteroid.getImage(), (int) asteroid.getX(), (int) asteroid.getY(), this);
			}

	}

	//Called every DELAY ms; controlled by the Timer
	@Override
	public void actionPerformed(ActionEvent e) {

		//If the mouse is on the JPanel, we step (move and repaint)
		if(mouseOnBoard()) {

			step();
		}

	}

	private void step() {

		count++;

		//A new asteroid is constructed every second (100 * DELAY = 1000ms = 1s)
		if(count % 100 == 0) {

			newAsteroid();
		}
		
		//Calculate our x and y differentials based on the position relative to the mouse and move accordingly
		spaceShip.calcDxDy(getMouseX(), getMouseY());		
		spaceShip.move();

		//These loops run through any and all missiles/asteroids and moves them accordingly
		if(!missileList.isEmpty()) {

			int i = 0;
			
			while(i < missileList.size()) {

				Missile missile = missileList.get(i);

				if(!onScreen(missile))

					missileList.remove(i);

				else
					
					missile.move();
				
				i++;
				
			}
			
		}
		
		if(!asteroidList.isEmpty()) {

			int i = 0;
			
			while (i < asteroidList.size()) {
				
				Asteroid asteroid = asteroidList.get(i);

				//asteroids bounce off the walls of the board rather than disappear (currently not true)
				if(!onScreen(asteroid)) {

					asteroid.bounceDx();
					asteroid.bounceDy();
				}
								
				int j = 0;
				
				while(j < missileList.size()) {
					
					if(asteroid.hitSprite(missileList.get(j))) {
						
						asteroidList.remove(i);
						missileList.remove(j);
						
					}

					j++;
						
				}
				
				if(asteroid.hitSprite(spaceShip)) {
					
					spaceShip.hit();
					spaceShip.destroy();
					asteroidList.remove(i);
					
					i++;
					
					continue;
				}
				
				asteroid.move();

				i++;
					
			}

		}
		
		//Repaint the entire canvas to erase the previous sprite images
		repaint();    
	}  

	private boolean onScreen(Sprite sprite) {

		return !(sprite.getX() < this.getX() || sprite.getX() + sprite.getWidth() > this.getX() + this.getWidth() || sprite.getY() < this.getY() || sprite.getY() + sprite.getHeight() > this.getY() + this.getHeight());
	}
	
	private void fire() {

		Missile e = new Missile(spaceShip.getX() + spaceShip.getWidth() / 2.0, spaceShip.getY() + spaceShip.getHeight() / 2.0, getMouseX(), getMouseY());

		missileList.add(e);
	}

	//Create a new asteroid sprite in a random location on the game board
	private void newAsteroid() {

		Random rndm = new Random();

		Asteroid a = new Asteroid(rndm.nextInt(this.getWidth()) + this.getX(), rndm.nextInt(this.getHeight()) + this.getY());

		//Error control loop for off-screen locations of asteroids
		while(!onScreen(a))
		
			a = new Asteroid(rndm.nextInt(this.getWidth()) + this.getX(), rndm.nextInt(this.getHeight()) + this.getY());
		
		asteroidList.add(a);
		
	}
	
	//Gets the mouse position for use in sprite movement calculations
	private double getMouseX() {

		PointerInfo mousePointer = MouseInfo.getPointerInfo();
		Point mousePoint = mousePointer.getLocation();

		return mousePoint.getX() - this.getLocationOnScreen().getX();
	}

	private double getMouseY() {

		PointerInfo mousePointer = MouseInfo.getPointerInfo();
		Point mousePoint = mousePointer.getLocation();

		return mousePoint.getY() - this.getLocationOnScreen().getY();
	}

	//Checks if the mouse is currently on the board 
	private boolean mouseOnBoard() {

		return (this.getX() < getMouseX() && getMouseX() < this.getX() + this.getWidth()) && (this.getY() < getMouseY() && getMouseY() < this.getY() + this.getHeight());
	}
	
	//Our MouseAdapter class controls various mouse events
	private class MAdapter extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {

			pauseScreen.setVisible(false);
		}

		@Override
		public void mouseExited(MouseEvent e) {

			pauseScreen.setVisible(true);
		}

	}

	//The KeyAdapter is currently only being used for firing missiles
	private class KAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {

			if(e.getKeyCode() == KeyEvent.VK_SPACE) 

				fire();
		}
		
	}

}