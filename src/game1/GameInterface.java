package game1;

import javax.swing.JFrame;

public class GameInterface extends JFrame {

	public GameInterface() {

		initUI();
	}

	private void initUI() {

		add(new GameBoard());
				
		setTitle("Spaceship Game!!!");
		setSize(750, 750);

		setLocation(100, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		GameInterface ex = new GameInterface();
		ex.setVisible(true);
	}
	
}
