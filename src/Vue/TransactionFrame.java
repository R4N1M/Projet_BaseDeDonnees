package Vue;

import java.awt.BorderLayout;
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

	private static final long serialVersionUID = 1L;
	private final int W_WIDTH = 800;
	private final int W_HEIGHT = 800;
	private JLabel title;
	private JLabel content;
	private JPanel footer;
	private JButton next;
	private JLabel time;
	private JLabel reussi;
	private JLabel echec;
	
	private Controller c;
	
	public TransactionFrame(Controller c){
		this.c = c;
		this.setTitle("Real Time DataBase");
		this.setSize(W_WIDTH, W_HEIGHT);
		this.setLocation(600, 0);
		this.setLayout(new BorderLayout());
		
		footer = new JPanel();
		footer.setLayout(new GridLayout(2,2));
		
		next = new JButton("Avancer d'un temps");
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.avancer();
				maj();
			}
		});
		time = new JLabel(c.getCurrentTime()+"");
		reussi = new JLabel("<html><center><p style='color:green'>Transactions reussies : "+c.getSucces()+"</p><c/enter></html>");
		echec = new JLabel("<html><center><p style='color:red'>Transactions echouées : "+c.getEchec()+"</p></center></html>");
		
		footer.add(reussi);
		footer.add(echec);
		footer.add(time);
		footer.add(next);
		
		title = new JLabel("Liste des transactions");
		content = new JLabel("");
		
		this.add(title, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
		
		maj();
		
		this.setVisible(true);
	}
	
	public void maj(){
		
		ArrayList<String> transactions = c.getTransactions();
		String str = "";
		if(transactions.size() == 0){
			next.setEnabled(false);
			str = "Simulation terminée";
		} else {
			str = "<html>";
			for (String s : transactions){
				str += s + "<br>";
			}
			str += "</html>";
		}
		time.setText("<html><center><p> Temps : "+c.getCurrentTime()+"</p></center></html>");
		reussi.setText("<html><center><p style='color:green'>Transactions reussies : "+c.getSucces()+"</p></center></html>");
		echec.setText("<html><center><p style='color:red'>Transactions echouées : "+c.getEchec()+"</p></center></html>");
		
		content.setText(str);
	}
}
