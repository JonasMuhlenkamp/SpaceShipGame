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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.input.KeyCode;

public class GameBoard extends JPanel implements ActionListener {
	
	private Spaceship spaceShip;
	private List<Missile> missileList;
	private final int DELAY = 10; 
	private JLabel pauseScreen;
			
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
		
		//Here we initialize the sprite we are using
		spaceShip = new Spaceship(60, 40);
		missileList = new ArrayList<Missile> ();
		
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
		
	}

	//Called every DELAY ms; controlled by the Timer
	@Override
	public void actionPerformed(ActionEvent e) {

		//If the mouse is over the component, we step (move and repaint)
		if(mouseOnBoard()) {

			step();
		}		
	
	}
	
	private void step() {

		//Calculate our x and y differentials based on the position relative to the mouse and move accordingly
		spaceShip.calcDxDy(getMouseX(), getMouseY());		
		spaceShip.move();
		
		if(!missileList.isEmpty())
			
			for(int i = 0; i < missileList.size(); i++) {
				
				Missile missile = missileList.get(i);
				
				if(missile.getX() < this.getX() || missile.getX() > this.getX() + this.getWidth() || missile.getY() < this.getY() || missile.getY() > this.getY() + this.getHeight())
					
					missileList.remove(i);
				
				else 
					
					missileList.get(i).move();
			}
		
		//Repaint the entire canvas to erase the previous sprite images
		repaint();    
	}  
	
	private void fire() {
		
		Missile e = new Missile(spaceShip.getX() + spaceShip.getWidth() / 2.0, spaceShip.getY() + spaceShip.getHeight() / 2.0, getMouseX(), getMouseY());
		
		missileList.add(e);
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
	
		if((this.getX() < getMouseX() && getMouseX() < this.getX() + this.getWidth()) && (this.getY() < getMouseY() && getMouseY() < this.getY() + this.getHeight())) {
			
			return true;
			
		} else {
			
			return false;
		}	
	
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
	
	private class KAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				
				fire();
			}
			
		}
	}

}