package Vue;

import Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FormFrame extends JFrame {
		
	private FormPanel updateForm;
	private JPanel p;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private TransactionFrame transFrame;
	private Controller c;
	
	public FormFrame(Controller c){
		this.c = c;
		
		this.setTitle("Real Time DataBase");
		
		menuBar = new JMenuBar();
		menu = new JMenu("Fenêtres");
		
		menuItem = new JMenuItem("Transactions");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(c.getBdd() != null){
					transFrame = new TransactionFrame(c);
				}
			}
		});
		this.menu.add(menuItem);
		this.menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		this.updateForm = new FormPanel(transFrame, c);
				
		this.add(updateForm);
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}

