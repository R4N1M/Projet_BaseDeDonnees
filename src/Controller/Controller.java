package Controller;

import Vue.FormFrame;
import Vue.TransactionFrame;

import java.util.ArrayList;

import Modele.BaseDeDonnees;
import Modele.Transaction;

public class Controller {

	private FormFrame principalFrame;
	private TransactionFrame tFrame;
	private BaseDeDonnees bdd;
	
	private final double TAUX_ALEA = 20.0;
	
	private Controller(){
		principalFrame = new FormFrame(this);
	}
	
	public FormFrame getPrincipalFrame() {
		return principalFrame;
	}

	public void setPrincipalFrame(FormFrame principalFrame) {
		this.principalFrame = principalFrame;
	}

	public BaseDeDonnees getBdd() {
		return bdd;
	}

	public void setBdd(BaseDeDonnees bdd) {
		this.bdd = bdd;
	}

	public void createDataBase(	int nbr_dtr, int min_v, int max_v, int nbr_dc, int min_nbr_op, 
								int max_nbr_op, int duree_s, int lp, int tlc, int tec, int tlr, 
								int ter
								){
		bdd = new BaseDeDonnees(nbr_dtr, min_v, max_v, nbr_dc, min_nbr_op, max_nbr_op, duree_s, lp, tlc, tec, tlr, ter, TAUX_ALEA);
		afficherListeTransaction();
	}
	
	public void avancer(){
		bdd.avancerTemps();
	}
	
	public int getCurrentTime(){
		return bdd.getTempsActuel();
	}
	
	public void afficherListeTransaction(){
		tFrame = new TransactionFrame(this);
	}
	
	public ArrayList<String> getTransaction(){
		ArrayList<String> trs = new ArrayList<String>();
		
		for (Transaction t : bdd.getTransactions()){
			System.out.println("Yahahahhahah");
			trs.add(t.toString());
		}
		
		return trs;
	}
	
	public static void main(String[] args){
		Controller c = new Controller();
	}
}
