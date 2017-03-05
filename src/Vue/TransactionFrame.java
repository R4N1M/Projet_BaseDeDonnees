package Vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controller;

public class TransactionFrame extends JFrame {

	private final int W_WIDTH = 400;
	private final int W_HEIGHT = 800;
	private JLabel title;
	private JLabel content;
	private JPanel footer;
	private JButton next;
	private JLabel time;
	
	private Controller c;
	
	public TransactionFrame(Controller c){
		this.c = c;
		this.setTitle("Real Time DataBase");
		this.setSize(W_WIDTH, W_HEIGHT);
		this.setLocation(600, 0);
		this.setLayout(new BorderLayout());
		
		footer = new JPanel();
		footer.setLayout(new FlowLayout());
		
		next = new JButton("Avancer d'un temps");
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.avancer();
				
			}
		});
		title = new JLabel("Liste des transactions");
		content = new JLabel("");
		time = new JLabel(c.getCurrentTime()+"");
		
		maj();
		
		this.setVisible(true);
	}
	
	public void maj(){
		
		ArrayList<String> transactions = c.getTransaction();
		for (String s : transactions){
			System.out.println(s);
		}
	}
}
