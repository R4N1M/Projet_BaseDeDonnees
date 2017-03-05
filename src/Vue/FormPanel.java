package Vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import Controller.Controller;

import javax.swing.JButton;
import javax.swing.JLabel;

public class FormPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final int ROWS = 12;
	private final int COLS = 1;
	
	private JPanel pg;
	private JPanel pd;
	private JPanel pb;
	
	private JLabel realDataNumberLabel;
	private JSpinner realDataNumberField;

	private JLabel classicDataNumberLabel;
	private JSpinner classicDataNumberField;
	
	private JLabel startIntervalValidityLabel;
	private JSpinner startIntervalValidityField;
	

	private JLabel endIntervalValidityLabel;
	private JSpinner endIntervalValidityField;
	
	private JLabel startIntervalNbOpLabel;
	private JSpinner startIntervalNbOpField;
	
	private JLabel endIntervalNbOpLabel;
	private JSpinner endIntervalNbOpField;
	
	private JLabel realDataReadTimeLabel;
	private JSpinner realDataReadTimeField;
	
	private JLabel classicDataReadTimeLabel;
	private JSpinner classicDataReadTimeField;
	
	private JLabel classicDataWriteTimeLabel;
	private JSpinner classicDataWriteTimeField;
	
	private JLabel simualtionTimeLabel;
	private JSpinner simulationTimeField;
	
	private JLabel lambdaLabel;
	private JSpinner lambdaField;
	
	private JButton cancel;
	private JButton submit;
	
	private Component newFrame;
	private Controller c;
	
	public FormPanel(Component newFrame, Controller c) {
		
		this.c = c;
		this.newFrame = newFrame;
		
		this.setLayout(new BorderLayout());
		
		pg = new JPanel();
		pg.setLayout(new GridLayout(ROWS, COLS));
		pd = new JPanel();
		pd.setLayout(new GridLayout(ROWS, COLS));
		pb = new JPanel();
		
		realDataNumberField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		realDataNumberLabel = new JLabel("Nombre de données temps réel :");
		
		classicDataNumberField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		classicDataNumberLabel = new JLabel("<html>Nombre de données classiques :");
		
		startIntervalValidityLabel = new JLabel("Début de l'interval pour la durée de validité :");
		startIntervalValidityField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		

		endIntervalValidityLabel = new JLabel("Fin de l'interval pour la durée de validité :");
		endIntervalValidityField = new JSpinner(new SpinnerNumberModel(1,0,10000,1));
		
		startIntervalNbOpLabel = new JLabel("Début de l'interval pour le nombre d'opération :");
		startIntervalNbOpField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		
		endIntervalNbOpLabel = new JLabel("Fin de l'interval pour le nombre d'opération :");
		endIntervalNbOpField = new JSpinner(new SpinnerNumberModel(1,0,10000,1));
		
		realDataReadTimeLabel = new JLabel("Durée d'une opération de lecture sur une données temps réel :");
		realDataReadTimeField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		
		classicDataReadTimeLabel = new JLabel("Durée d'une opération de lecture sur une données classique :");
		classicDataReadTimeField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		
		classicDataWriteTimeLabel = new JLabel("Durée d'une opération en écriture sur une données classique :");
		classicDataWriteTimeField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		
		simualtionTimeLabel = new JLabel("Durée de la simulation :");
		simulationTimeField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		
		lambdaLabel = new JLabel("Lambda de la loi de poisson :");
		lambdaField = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		
		cancel = new JButton("Effacer");
		cancel.addActionListener(this);
		submit = new JButton("Commencer");
		submit.addActionListener(this);
		
		pg.add(realDataNumberLabel);
		pd.add(realDataNumberField);
		pg.add(classicDataNumberLabel);
		pd.add(classicDataNumberField);
		pg.add(startIntervalValidityLabel);
		pd.add(startIntervalValidityField);
		pg.add(endIntervalValidityLabel);
		pd.add(endIntervalValidityField);
		pg.add(startIntervalNbOpLabel);
		pd.add(startIntervalNbOpField);
		pg.add(endIntervalNbOpLabel);
		pd.add(endIntervalNbOpField);
		pg.add(realDataReadTimeLabel);
		pd.add(realDataReadTimeField);
		pg.add(classicDataReadTimeLabel);
		pd.add(classicDataReadTimeField);
		pg.add(classicDataWriteTimeLabel);
		pd.add(classicDataWriteTimeField);
		pg.add(simualtionTimeLabel);
		pd.add(simulationTimeField);
		pg.add(lambdaLabel);
		pd.add(lambdaField);
				
		pb.add(cancel);
		pb.add(submit);
		this.add(pg, BorderLayout.WEST);
		this.add(pd, BorderLayout.EAST);
		this.add(pb, BorderLayout.SOUTH);
				
		this.setVisible(true);
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == submit){
			
			System.out.println("START !!!!!!");
						
			c.createDataBase(	(int)realDataNumberField.getValue(), (int)startIntervalValidityField.getValue(), (int)endIntervalValidityField.getValue(), 
								(int)classicDataNumberField.getValue(), (int)startIntervalNbOpField.getValue(), (int)endIntervalNbOpField.getValue(), 
								(int)simulationTimeField.getValue(), (int)lambdaField.getValue(), (int)classicDataReadTimeField.getValue(), 
								(int)classicDataWriteTimeField.getValue(), (int)realDataReadTimeField.getValue(), 3);
			
			newFrame = new TransactionFrame(c);
			
		} else if (e.getSource() == cancel){
			
			System.out.println("CANCEL !!!!!");
				
			for ( Component c : pd.getComponents()){
				((JSpinner)c).setValue(0);
			}
		}
	}
	
}
