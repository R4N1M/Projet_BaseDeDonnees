package Vue;

import javax.swing.JFrame;

import Controller.Controller;

public class TransactionFrame extends JFrame {

	private final int W_WIDTH = 400;
	private final int W_HEIGHT = 800;

	private Controller c;
	
	public TransactionFrame(Controller c){
		this.c = c;
		this.setTitle("Real Time DataBase");
		this.setSize(W_WIDTH, W_HEIGHT);
		this.setLocation(600, 0);
				
		this.setVisible(true);
	}
}
