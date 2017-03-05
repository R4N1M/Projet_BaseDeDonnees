package Vue;

import java.awt.GridLayout;
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
	
	public FormFrame(){
		this.setTitle("Real Time DataBase");
		
		menuBar = new JMenuBar();
		menu = new JMenu("Fenêtres");
		
		menuItem = new JMenuItem("Transactions");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				transFrame = new TransactionFrame();	
			}
		});
		this.menu.add(menuItem);
		this.menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		this.updateForm = new FormPanel(transFrame);
				
		this.add(updateForm);
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}

