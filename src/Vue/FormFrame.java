package Vue;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FormFrame extends JFrame {
	
	private final int W_WIDTH = 800;
	private final int W_HEIGHT = 300;
	private final int ROWS = 1;
	private final int COLS = 1;
	
	private FormPanel updateForm;
	private JPanel p;
	
	public FormFrame(){
		this.setTitle("Real Time DataBase");
		//this.setSize(W_WIDTH, W_HEIGHT);
		this.setLayout(new GridLayout(ROWS, COLS));
		
		this.updateForm = new FormPanel();
				
		this.add(updateForm);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String [] args){
		FormFrame f = new FormFrame();
	}
}

